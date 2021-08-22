package uz.pdp.appduonotarypraktikaserver.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appduonotarypraktikaserver.entity.*;
import uz.pdp.appduonotarypraktikaserver.entity.enums.*;
import uz.pdp.appduonotarypraktikaserver.repository.*;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName.ROLE_SUPER_ADMIN;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    PermissionRoleRepository permissionRoleRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    ZipCodeRepository zipCodeRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    MainServiceRepository mainServiceRepository;

    @Autowired
    ServicePriceRepository servicePriceRepository;

    @Autowired
    UserZipCodeRepository userZipCodeRepository;

    @Autowired
    DocAmountPricingRepository docAmountPricingRepository;

    @Autowired
    AdditionalServiceRepository additionalServiceRepository;

    @Autowired
    AdditionalServicePriceRepository additionalServicePriceRepository;

    @Autowired
    WeekDayRepository weekDayRepository;

    @Autowired
    HourRepository hourRepository;

    @Autowired
    AgentScheduleRepository agentScheduleRepository;

    @Autowired
    TimeDurationRepository timeDurationRepository;

    @Autowired
    PayTypeRepository payTypeRepository;

    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            TimeDuration timeDuration = new TimeDuration();
            timeDuration.setDurationTime(30);
            timeDurationRepository.save(timeDuration);

            List<PermissionRole> permissionRoles = new ArrayList<>();
            List<Role> roles = roleRepository.findAll();
            for (PermissionName permissionName : PermissionName.values()) {
                Permission savedPermission = permissionRepository.save(
                        new Permission(permissionName));
                for (RoleName roleName : permissionName.roleNames) {
                    permissionRoles.add(new PermissionRole(
                            getRoleByRoleName(roles, roleName), savedPermission));
                }
            }
            permissionRoleRepository.saveAll(permissionRoles);
            userRepository.save(new User(
                    "Abbos",
                    "Namazov",
                    "+9989917765252",
                    "30-46",
                    passwordEncoder.encode("Jordan18"),
                    "abbosnamazov17@gmail.com",
                    roleRepository.findAllByNameIn(
                            Arrays.asList(RoleName.ROLE_ADMIN, ROLE_SUPER_ADMIN)), new HashSet<>(permissionRepository.findAllByRoleName(ROLE_SUPER_ADMIN.name()))));

//            SAVING STATE
            State state = stateRepository.save(new State("Virginia", "it's a state Virginia", true));
//            SAVING COUNTIES AND ZIPCODES
            County county1 = countyRepository.save(new County("Loudoun", "it's a county Loudoun", true, state));

            List<ZipCode> zipCodeListLoudoun = zipCodeRepository.saveAll(Arrays
                    .asList(new ZipCode("20101", county1),
                            new ZipCode("20102", county1),
                            new ZipCode("20103", county1),
                            new ZipCode("20104", county1)));


            County county2 = countyRepository.save(new County("Culpeper", "it's a county Culpeper", true, state));

            List<ZipCode> zipCodeListCulpeper = zipCodeRepository.saveAll(Arrays
                    .asList(new ZipCode("20106", county2),
                            new ZipCode("22746", county2),
                            new ZipCode("22741", county2),
                            new ZipCode("22737", county2)));

            County county3 = countyRepository.save(new County("Clarke", "it's a county Clarke", true, state));

            List<ZipCode> zipCodeListClarke = zipCodeRepository.saveAll(Arrays
                    .asList(new ZipCode("20130", county3),
                            new ZipCode("20135", county3),
                            new ZipCode("22611", county3),
                            new ZipCode("22620", county3)));

            County county4 = countyRepository.save(new County("Fauquier", "it's a county Fauquier", true, state));

            List<ZipCode> zipCodeListFauquier = zipCodeRepository.saveAll(Arrays
                    .asList(new ZipCode("20115", county4),
                            new ZipCode("20116", county4),
                            new ZipCode("20119", county4),
                            new ZipCode("20128", county4)));


            //            SAVING MAINSERVICES
            MainService mainServiceOnline = mainServiceRepository.save(new MainService("Online", "online services", true));
            MainService mainServiceInPerson = mainServiceRepository.save(new MainService("In Person", "in person services", true));


            //            SAVING IN-PERSON SERVICES
            Services service1 = serviceRepository.save(new Services("Real Estate", "Services clients across property types and industry sectors.", true, 5, 15, 1, 3, mainServiceInPerson));
            Services service2 = serviceRepository.save(new Services("International", "International services", true, 10, 40, 1, 4, mainServiceInPerson));
            Services service3 = serviceRepository.save(new Services("Employment Verification", "Verify your employees", true, 3, 20, 1, 2, mainServiceInPerson));
            Services service4 = serviceRepository.save(new Services("Marriage Registration", "Register your marriage with us", true, 2, 25, 1, 8, mainServiceInPerson));

            //            SAVING ONLINE SERVICES
            Services service5 = serviceRepository.save(new Services("Child Adoption", "Adopt new child to your family", true, 3, 15, 1, 3, mainServiceOnline));
            Services service6 = serviceRepository.save(new Services("Business License", "Get a business license", true, 7, 60, 1, 12, mainServiceOnline));
            Services service7 = serviceRepository.save(new Services("One Year Visa", "Get a visa for one year", true, 1, 15, 1, 15, mainServiceOnline));
            Services service8 = serviceRepository.save(new Services("Marriage registration", "Register your marriage with us", true, 2, 40, 1, 25, mainServiceOnline));

            //                SAVING Services PRICES
            ServicePrice servicePrice1 = servicePriceRepository.save(new ServicePrice(49, true, 0, 0, zipCodeListLoudoun.get(0), service1));
            ServicePrice servicePrice2 = servicePriceRepository.save(new ServicePrice(79, true, 0, 0, zipCodeListLoudoun.get(0), service2));
            servicePriceRepository.save(new ServicePrice(39, true, 0, 0, zipCodeListLoudoun.get(0), service3));
            servicePriceRepository.save(new ServicePrice(29, true, 0, 0, zipCodeListLoudoun.get(0), service4));
            servicePriceRepository.save(new ServicePrice(49, true, 0, 0, zipCodeListLoudoun.get(1), service1));
            servicePriceRepository.save(new ServicePrice(79, true, 0, 0, zipCodeListLoudoun.get(1), service2));
            servicePriceRepository.save(new ServicePrice(39, true, 0, 0, zipCodeListLoudoun.get(1), service3));
            servicePriceRepository.save(new ServicePrice(29, true, 0, 0, zipCodeListLoudoun.get(1), service4));
            servicePriceRepository.save(new ServicePrice(49, true, 0, 0, zipCodeListLoudoun.get(2), service1));
            servicePriceRepository.save(new ServicePrice(79, true, 0, 0, zipCodeListLoudoun.get(2), service2));
            servicePriceRepository.save(new ServicePrice(39, true, 0, 0, zipCodeListLoudoun.get(2), service3));
            servicePriceRepository.save(new ServicePrice(29, true, 0, 0, zipCodeListLoudoun.get(2), service4));
            servicePriceRepository.save(new ServicePrice(49, true, 0, 0, zipCodeListLoudoun.get(3), service1));
            servicePriceRepository.save(new ServicePrice(79, true, 0, 0, zipCodeListLoudoun.get(3), service2));
            servicePriceRepository.save(new ServicePrice(39, true, 0, 0, zipCodeListLoudoun.get(3), service3));
            servicePriceRepository.save(new ServicePrice(29, true, 0, 0, zipCodeListLoudoun.get(3), service4));

            servicePriceRepository.save(new ServicePrice(42, true, 0, 0, zipCodeListCulpeper.get(0), service1));
            servicePriceRepository.save(new ServicePrice(72, true, 0, 0, zipCodeListCulpeper.get(0), service2));
            servicePriceRepository.save(new ServicePrice(33, true, 0, 0, zipCodeListCulpeper.get(0), service3));
            servicePriceRepository.save(new ServicePrice(25, true, 0, 0, zipCodeListCulpeper.get(0), service4));
            servicePriceRepository.save(new ServicePrice(42, true, 0, 0, zipCodeListCulpeper.get(1), service1));
            servicePriceRepository.save(new ServicePrice(72, true, 0, 0, zipCodeListCulpeper.get(1), service2));
            servicePriceRepository.save(new ServicePrice(33, true, 0, 0, zipCodeListCulpeper.get(1), service3));
            servicePriceRepository.save(new ServicePrice(25, true, 0, 0, zipCodeListCulpeper.get(1), service4));
            servicePriceRepository.save(new ServicePrice(42, true, 0, 0, zipCodeListCulpeper.get(2), service1));
            servicePriceRepository.save(new ServicePrice(72, true, 0, 0, zipCodeListCulpeper.get(2), service2));
            servicePriceRepository.save(new ServicePrice(33, true, 0, 0, zipCodeListCulpeper.get(2), service3));
            servicePriceRepository.save(new ServicePrice(25, true, 0, 0, zipCodeListCulpeper.get(2), service4));
            servicePriceRepository.save(new ServicePrice(42, true, 0, 0, zipCodeListCulpeper.get(3), service1));
            servicePriceRepository.save(new ServicePrice(72, true, 0, 0, zipCodeListCulpeper.get(3), service2));
            servicePriceRepository.save(new ServicePrice(33, true, 0, 0, zipCodeListCulpeper.get(3), service3));
            servicePriceRepository.save(new ServicePrice(25, true, 0, 0, zipCodeListCulpeper.get(3), service4));

            servicePriceRepository.save(new ServicePrice(41, true, 0, 0, zipCodeListClarke.get(0), service1));
            servicePriceRepository.save(new ServicePrice(71, true, 0, 0, zipCodeListClarke.get(0), service2));
            servicePriceRepository.save(new ServicePrice(32, true, 0, 0, zipCodeListClarke.get(0), service3));
            servicePriceRepository.save(new ServicePrice(24, true, 0, 0, zipCodeListClarke.get(0), service4));
            servicePriceRepository.save(new ServicePrice(41, true, 0, 0, zipCodeListClarke.get(1), service1));
            servicePriceRepository.save(new ServicePrice(71, true, 0, 0, zipCodeListClarke.get(1), service2));
            servicePriceRepository.save(new ServicePrice(32, true, 0, 0, zipCodeListClarke.get(1), service3));
            servicePriceRepository.save(new ServicePrice(23, true, 0, 0, zipCodeListClarke.get(1), service4));
            servicePriceRepository.save(new ServicePrice(41, true, 0, 0, zipCodeListClarke.get(2), service1));
            servicePriceRepository.save(new ServicePrice(71, true, 0, 0, zipCodeListClarke.get(2), service2));
            servicePriceRepository.save(new ServicePrice(32, true, 0, 0, zipCodeListClarke.get(2), service3));
            servicePriceRepository.save(new ServicePrice(24, true, 0, 0, zipCodeListClarke.get(2), service4));
            servicePriceRepository.save(new ServicePrice(41, true, 0, 0, zipCodeListClarke.get(3), service1));
            servicePriceRepository.save(new ServicePrice(71, true, 0, 0, zipCodeListClarke.get(3), service2));
            servicePriceRepository.save(new ServicePrice(32, true, 0, 0, zipCodeListClarke.get(3), service3));
            servicePriceRepository.save(new ServicePrice(24, true, 0, 0, zipCodeListClarke.get(3), service4));

            servicePriceRepository.save(new ServicePrice(45, true, 0, 0, zipCodeListFauquier.get(0), service1));
            servicePriceRepository.save(new ServicePrice(75, true, 0, 0, zipCodeListFauquier.get(0), service2));
            servicePriceRepository.save(new ServicePrice(36, true, 0, 0, zipCodeListFauquier.get(0), service3));
            servicePriceRepository.save(new ServicePrice(28, true, 0, 0, zipCodeListFauquier.get(0), service4));
            servicePriceRepository.save(new ServicePrice(45, true, 0, 0, zipCodeListFauquier.get(1), service1));
            servicePriceRepository.save(new ServicePrice(75, true, 0, 0, zipCodeListFauquier.get(1), service2));
            servicePriceRepository.save(new ServicePrice(36, true, 0, 0, zipCodeListFauquier.get(1), service3));
            servicePriceRepository.save(new ServicePrice(28, true, 0, 0, zipCodeListFauquier.get(1), service4));
            servicePriceRepository.save(new ServicePrice(45, true, 0, 0, zipCodeListFauquier.get(2), service1));
            servicePriceRepository.save(new ServicePrice(75, true, 0, 0, zipCodeListFauquier.get(2), service2));
            servicePriceRepository.save(new ServicePrice(36, true, 0, 0, zipCodeListFauquier.get(2), service3));
            servicePriceRepository.save(new ServicePrice(28, true, 0, 0, zipCodeListFauquier.get(2), service4));
            servicePriceRepository.save(new ServicePrice(45, true, 0, 0, zipCodeListFauquier.get(3), service1));
            servicePriceRepository.save(new ServicePrice(75, true, 0, 0, zipCodeListFauquier.get(3), service2));
            servicePriceRepository.save(new ServicePrice(36, true, 0, 0, zipCodeListFauquier.get(3), service3));
            servicePriceRepository.save(new ServicePrice(28, true, 0, 0, zipCodeListFauquier.get(3), service4));
//                ONLINE Services PRICES
            servicePriceRepository.save(new ServicePrice(19, true, 0, 0, zipCodeListLoudoun.get(0), service5));
            servicePriceRepository.save(new ServicePrice(49, true, 0, 0, zipCodeListLoudoun.get(0), service6));
            servicePriceRepository.save(new ServicePrice(9, true, 0, 0, zipCodeListLoudoun.get(0), service7));
            servicePriceRepository.save(new ServicePrice(5, true, 0, 0, zipCodeListLoudoun.get(0), service8));
            servicePriceRepository.save(new ServicePrice(19, true, 0, 0, zipCodeListLoudoun.get(1), service5));
            servicePriceRepository.save(new ServicePrice(49, true, 0, 0, zipCodeListLoudoun.get(1), service6));
            servicePriceRepository.save(new ServicePrice(9, true, 0, 0, zipCodeListLoudoun.get(1), service7));
            servicePriceRepository.save(new ServicePrice(5, true, 0, 0, zipCodeListLoudoun.get(1), service8));
            servicePriceRepository.save(new ServicePrice(19, true, 0, 0, zipCodeListLoudoun.get(2), service5));
            servicePriceRepository.save(new ServicePrice(49, true, 0, 0, zipCodeListLoudoun.get(2), service6));
            servicePriceRepository.save(new ServicePrice(9, true, 0, 0, zipCodeListLoudoun.get(2), service7));
            servicePriceRepository.save(new ServicePrice(5, true, 0, 0, zipCodeListLoudoun.get(2), service8));
            servicePriceRepository.save(new ServicePrice(19, true, 0, 0, zipCodeListLoudoun.get(3), service5));
            servicePriceRepository.save(new ServicePrice(49, true, 0, 0, zipCodeListLoudoun.get(3), service6));
            servicePriceRepository.save(new ServicePrice(9, true, 0, 0, zipCodeListLoudoun.get(3), service7));
            servicePriceRepository.save(new ServicePrice(5, true, 0, 0, zipCodeListLoudoun.get(3), service8));

            servicePriceRepository.save(new ServicePrice(12, true, 0, 0, zipCodeListCulpeper.get(0), service5));
            servicePriceRepository.save(new ServicePrice(42, true, 0, 0, zipCodeListCulpeper.get(0), service6));
            servicePriceRepository.save(new ServicePrice(3, true, 0, 0, zipCodeListCulpeper.get(0), service7));
            servicePriceRepository.save(new ServicePrice(5, true, 0, 0, zipCodeListCulpeper.get(0), service8));
            servicePriceRepository.save(new ServicePrice(12, true, 0, 0, zipCodeListCulpeper.get(1), service5));
            servicePriceRepository.save(new ServicePrice(42, true, 0, 0, zipCodeListCulpeper.get(1), service6));
            servicePriceRepository.save(new ServicePrice(3, true, 0, 0, zipCodeListCulpeper.get(1), service7));
            servicePriceRepository.save(new ServicePrice(5, true, 0, 0, zipCodeListCulpeper.get(1), service8));
            servicePriceRepository.save(new ServicePrice(12, true, 0, 0, zipCodeListCulpeper.get(2), service5));
            servicePriceRepository.save(new ServicePrice(42, true, 0, 0, zipCodeListCulpeper.get(2), service6));
            servicePriceRepository.save(new ServicePrice(3, true, 0, 0, zipCodeListCulpeper.get(2), service7));
            servicePriceRepository.save(new ServicePrice(5, true, 0, 0, zipCodeListCulpeper.get(2), service8));
            servicePriceRepository.save(new ServicePrice(12, true, 0, 0, zipCodeListCulpeper.get(3), service5));
            servicePriceRepository.save(new ServicePrice(42, true, 0, 0, zipCodeListCulpeper.get(3), service6));
            servicePriceRepository.save(new ServicePrice(3, true, 0, 0, zipCodeListCulpeper.get(3), service7));
            servicePriceRepository.save(new ServicePrice(5, true, 0, 0, zipCodeListCulpeper.get(3), service8));

            servicePriceRepository.save(new ServicePrice(11, true, 0, 0, zipCodeListClarke.get(0), service5));
            servicePriceRepository.save(new ServicePrice(41, true, 0, 0, zipCodeListClarke.get(0), service6));
            servicePriceRepository.save(new ServicePrice(2, true, 0, 0, zipCodeListClarke.get(0), service7));
            servicePriceRepository.save(new ServicePrice(4, true, 0, 0, zipCodeListClarke.get(0), service8));
            servicePriceRepository.save(new ServicePrice(11, true, 0, 0, zipCodeListClarke.get(1), service5));
            servicePriceRepository.save(new ServicePrice(41, true, 0, 0, zipCodeListClarke.get(1), service6));
            servicePriceRepository.save(new ServicePrice(2, true, 0, 0, zipCodeListClarke.get(1), service7));
            servicePriceRepository.save(new ServicePrice(4, true, 0, 0, zipCodeListClarke.get(1), service8));
            servicePriceRepository.save(new ServicePrice(11, true, 0, 0, zipCodeListClarke.get(2), service5));
            servicePriceRepository.save(new ServicePrice(41, true, 0, 0, zipCodeListClarke.get(2), service6));
            servicePriceRepository.save(new ServicePrice(2, true, 0, 0, zipCodeListClarke.get(2), service7));
            servicePriceRepository.save(new ServicePrice(4, true, 0, 0, zipCodeListClarke.get(2), service8));
            servicePriceRepository.save(new ServicePrice(11, true, 0, 0, zipCodeListClarke.get(3), service5));
            servicePriceRepository.save(new ServicePrice(41, true, 0, 0, zipCodeListClarke.get(3), service6));
            servicePriceRepository.save(new ServicePrice(2, true, 0, 0, zipCodeListClarke.get(3), service7));
            servicePriceRepository.save(new ServicePrice(4, true, 0, 0, zipCodeListClarke.get(3), service8));

            servicePriceRepository.save(new ServicePrice(15, true, 0, 0, zipCodeListFauquier.get(0), service5));
            servicePriceRepository.save(new ServicePrice(45, true, 0, 0, zipCodeListFauquier.get(0), service6));
            servicePriceRepository.save(new ServicePrice(6, true, 0, 0, zipCodeListFauquier.get(0), service7));
            servicePriceRepository.save(new ServicePrice(8, true, 0, 0, zipCodeListFauquier.get(0), service8));
            servicePriceRepository.save(new ServicePrice(15, true, 0, 0, zipCodeListFauquier.get(1), service5));
            servicePriceRepository.save(new ServicePrice(45, true, 0, 0, zipCodeListFauquier.get(1), service6));
            servicePriceRepository.save(new ServicePrice(6, true, 0, 0, zipCodeListFauquier.get(1), service7));
            servicePriceRepository.save(new ServicePrice(8, true, 0, 0, zipCodeListFauquier.get(1), service8));
            servicePriceRepository.save(new ServicePrice(15, true, 0, 0, zipCodeListFauquier.get(2), service5));
            servicePriceRepository.save(new ServicePrice(45, true, 0, 0, zipCodeListFauquier.get(2), service6));
            servicePriceRepository.save(new ServicePrice(6, true, 0, 0, zipCodeListFauquier.get(2), service7));
            servicePriceRepository.save(new ServicePrice(8, true, 0, 0, zipCodeListFauquier.get(2), service8));
            servicePriceRepository.save(new ServicePrice(15, true, 0, 0, zipCodeListFauquier.get(3), service5));
            servicePriceRepository.save(new ServicePrice(45, true, 0, 0, zipCodeListFauquier.get(3), service6));
            servicePriceRepository.save(new ServicePrice(6, true, 0, 0, zipCodeListFauquier.get(3), service7));
            servicePriceRepository.save(new ServicePrice(8, true, 0, 0, zipCodeListFauquier.get(3), service8));

//            SAVING ADDITIONAL SERVICES
            AdditionalService copy_document = additionalServiceRepository.save(new AdditionalService("Copy document", "Get a lot of copies your documents", true));
            AdditionalService scan = additionalServiceRepository.save(new AdditionalService("Scan document", "Scanning your documents", true));
            AdditionalService printing = additionalServiceRepository.save(new AdditionalService("Black & White printing ", "Printing", true));
            AdditionalService color_printing = additionalServiceRepository.save(new AdditionalService("Multi-color printing", "Color printing", true));
            AdditionalService witness = additionalServiceRepository.save(new AdditionalService("Witnesses", "Call witnesses", true));

//            SAVING ADDITIONAL Services PRICE
            zipCodeListLoudoun.forEach(zipCode -> {
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.25, true, zipCode, copy_document, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.25, true, zipCode, copy_document, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.25, true, zipCode, copy_document, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.25, true, zipCode, copy_document, service4));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.40, true, zipCode, scan, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.40, true, zipCode, scan, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.40, true, zipCode, scan, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.40, true, zipCode, scan, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(0.5, true, zipCode, printing, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.5, true, zipCode, printing, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.5, true, zipCode, printing, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.5, true, zipCode, printing, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(0.7, true, zipCode, color_printing, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.7, true, zipCode, color_printing, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.7, true, zipCode, color_printing, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.7, true, zipCode, color_printing, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(47, true, zipCode, witness, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(47, true, zipCode, witness, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(47, true, zipCode, witness, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(47, true, zipCode, witness, service4));

            });

            zipCodeListCulpeper.forEach(zipCode -> {
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.30, true, zipCode, copy_document, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.30, true, zipCode, copy_document, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.30, true, zipCode, copy_document, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.30, true, zipCode, copy_document, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(0.55, true, zipCode, printing, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.55, true, zipCode, printing, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.55, true, zipCode, printing, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.55, true, zipCode, printing, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(0.75, true, zipCode, color_printing, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.75, true, zipCode, color_printing, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.75, true, zipCode, color_printing, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.75, true, zipCode, color_printing, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(42, true, zipCode, witness, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(42, true, zipCode, witness, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(42, true, zipCode, witness, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(42, true, zipCode, witness, service4));

            });

            zipCodeListClarke.forEach(zipCode -> {
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.35, true, zipCode, copy_document, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.35, true, zipCode, copy_document, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.35, true, zipCode, copy_document, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.35, true, zipCode, copy_document, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(0.6, true, zipCode, printing, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.6, true, zipCode, printing, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.6, true, zipCode, printing, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.6, true, zipCode, printing, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(0.8, true, zipCode, color_printing, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.8, true, zipCode, color_printing, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.8, true, zipCode, color_printing, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.8, true, zipCode, color_printing, service4));

            });

            zipCodeListFauquier.forEach(zipCode -> {
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.3, true, zipCode, scan, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.3, true, zipCode, scan, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.3, true, zipCode, scan, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(0.4, true, zipCode, printing, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.4, true, zipCode, printing, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.4, true, zipCode, printing, service3));
                additionalServicePriceRepository.save(new AdditionalServicePrice(0.4, true, zipCode, printing, service4));

                additionalServicePriceRepository.save(new AdditionalServicePrice(30, true, zipCode, witness, service1));
                additionalServicePriceRepository.save(new AdditionalServicePrice(30, true, zipCode, witness, service2));
                additionalServicePriceRepository.save(new AdditionalServicePrice(30, true, zipCode, witness, service3));

            });

//            SAVING ADMINS
            User admin1 = userRepository.save(new User(
                    "Admin1",
                    "Admineral",
                    "+15125550156",
                    "5033 Transit Road, Clarence NY 14031",
                    passwordEncoder.encode("123"),
                    "admin1@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_ADMIN)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_ADMIN.name()))));

            User admin2 = userRepository.save(new User(
                    "Admin2",
                    "Admineral",
                    "+15125550179",
                    "3949 Route 31, Clay NY 13041",
                    passwordEncoder.encode("123"),
                    "admin2@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_ADMIN)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_ADMIN.name()))));


//            SAVING AGENTS
            User agentInPerson1 = userRepository.save(new User(
                    "Bruce",
                    "Lee",
                    "+12025550198",
                    "777 Brockton Avenue, Abington MA 2351",
                    passwordEncoder.encode("123"),
                    "bruce_lee@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name()))));

            User agentInPerson2 = userRepository.save(new User(
                    "Chuck",
                    "Norris",
                    "+12025550139",
                    "30 Memorial Drive, Avon MA 2322",
                    passwordEncoder.encode("123"),
                    "chuck_norris@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name()))));

            User agentInPerson3 = userRepository.save(new User(
                    "Jean-Claude",
                    "Van Damme",
                    "+12025550120",
                    "250 Hartford Avenue, Bellingham MA 2019",
                    passwordEncoder.encode("123"),
                    "van_damme@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name()))));

            User agentInPerson4 = userRepository.save(new User(
                    "Jackie",
                    "Chan",
                    "+12025550106",
                    "200 Dutch Meadows Ln, Glenville NY 12302",
                    passwordEncoder.encode("123"),
                    "jackie_chan@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name()))));

            User agentInPerson5 = userRepository.save(new User(
                    "Steven",
                    "Seagal",
                    "+15125550116",
                    "161 Centereach Mall, Centereach NY 11720",
                    passwordEncoder.encode("123"),
                    "steven@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name()))));

            User agentInPerson6 = userRepository.save(new User(
                    "Sylvester",
                    "Stallone",
                    "+15125550144",
                    "3018 East Ave, Central Square NY 13036",
                    passwordEncoder.encode("123"),
                    "sylvester@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name()))));

            User agentInPerson7 = userRepository.save(new User(
                    "Dwayne",
                    "Johnson",
                    "+15125550146",
                    "100 Thruway Plaza, Cheektowaga NY 14225",
                    passwordEncoder.encode("123"),
                    "dwayne@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name()))));

            User agentInPerson8 = userRepository.save(new User(
                    "Mel",
                    "Gibson",
                    "+15125550173",
                    "8064 Brewerton Rd, Cicero NY 13039",
                    passwordEncoder.encode("123"),
                    "mel_gibson@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name()))));

//            ONLINE AGENTS
            User agentOnline1 = userRepository.save(new User(
                    "Ozodbek",
                    "Nazarbekov",
                    "+12025550176",
                    "5360 Southwestern Blvd, Hamburg NY 14075",
                    passwordEncoder.encode("123"),
                    "ozodbek@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name())), true));

            User agentOnline2 = userRepository.save(new User(
                    "Botir",
                    "Qodirov",
                    "+12025550117",
                    "80 Town Line Rd, Rocky Hill CT 6067",
                    passwordEncoder.encode("123"),
                    "qodirov@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_AGENT)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name())), true));

//            SAVING CUSTOMERS
            User customer1 = userRepository.save(new User(
                    "Custom",
                    "Customerov",
                    "+15125585319",
                    "234 Manhattan Rd, Briton NY 13039",
                    passwordEncoder.encode("123"),
                    "customer@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_CUSTOMER)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_CUSTOMER.name()))));

            User customer2 = userRepository.save(new User(
                    "Custom2",
                    "Customerov2",
                    "+19125910319",
                    "",
                    passwordEncoder.encode("123"),
                    "customer2@gmail.com",
                    roleRepository.findAllByNameIn(
                            Collections.singletonList(RoleName.ROLE_CUSTOMER)), new HashSet<>(permissionRepository.findAllByRoleName(RoleName.ROLE_CUSTOMER.name()))));

//            SAVE PAY TYPES
            PayType card = new PayType();
            card.setActive(true);
            PayType payTypeCard = payTypeRepository.save(card);

//            SAVE ORDERS
            Order order1 = orderRepository.save(new Order(1, 7, 73, OrderStatus.UP_COMING, "Mustaqillik 45", null, null,
                    PayStatus.PAID, 0, false, "34f3revfsgfre", servicePrice1, customer1, agentInPerson1, payTypeCard));

            Order order2 = orderRepository.save(new Order(2, 3, 34, OrderStatus.UP_COMING, "Istiqlol 45", null, null,
                    PayStatus.PAID, 0, false, "etbretbetrb", servicePrice2, customer2, agentInPerson1, payTypeCard));

//            SAVE TIME TABLE
            TimeTable timeTable = new TimeTable(LocalDateTime.of(2020, 9, 14, 9, 15),
                    LocalDateTime.of(2020, 9, 14, 9, 41), false, true, agentInPerson1, order1);
            timeTableRepository.save(timeTable);
            TimeTable timeTable2 = new TimeTable(LocalDateTime.of(2020, 9, 14, 14, 0),
                    LocalDateTime.of(2020, 9, 14, 14, 40), false, true, agentInPerson1, order2);

            timeTableRepository.save(timeTable2);

//            ASSIGN AGENTS TO ZIPCODES
            zipCodeListLoudoun.forEach(zipCode -> {
                userZipCodeRepository.saveAll(Arrays.asList(
                        new UserZipCode(agentInPerson1, zipCode),
                        new UserZipCode(agentInPerson5, zipCode),
                        new UserZipCode(agentInPerson6, zipCode)));

            });


            zipCodeListCulpeper.forEach(zipCode -> {
                userZipCodeRepository.saveAll(Arrays.asList(
                        new UserZipCode(agentInPerson2, zipCode),
                        new UserZipCode(agentInPerson7, zipCode)));
            });


            zipCodeListClarke.forEach(zipCode -> {
                userZipCodeRepository.saveAll(Arrays.asList(
                        new UserZipCode(agentInPerson3, zipCode),
                        new UserZipCode(agentInPerson8, zipCode)));
            });


            zipCodeListFauquier.forEach(zipCode -> {
                userZipCodeRepository.save(new UserZipCode(agentInPerson4, zipCode));
            });

//           SAVE DOCAMOUNTPRICING

            zipCodeListLoudoun.forEach(zipCode -> {
                docAmountPricingRepository.save(new DocAmountPricing(3, 1, 5, 1, service1, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(4, 6, 10, 1, service1, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(5, 11, 20, 1, service1, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(1, 1, 5, 1, service2, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2, 6, 10, 1, service2, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(3, 11, 20, 1, service2, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(0.5, 1, 5, 1, service3, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(1, 6, 10, 1, service3, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(1.5, 11, 20, 1, service3, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2.5, 1, 5, 1, service4, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(3, 6, 10, 1, service4, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(4.5, 11, 20, 1, service4, zipCode));

            });

            zipCodeListCulpeper.forEach(zipCode -> {
                docAmountPricingRepository.save(new DocAmountPricing(2, 1, 0, 1, service1, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2, 1, 0, 1, service2, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2, 1, 0, 1, service3, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2, 1, 0, 1, service4, zipCode));

            });

            zipCodeListClarke.forEach(zipCode -> {
                docAmountPricingRepository.save(new DocAmountPricing(0.75, 1, 5, 1, service1, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(1, 6, 10, 1, service1, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(1.25, 11, 20, 1, service1, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(1.25, 1, 5, 1, service2, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2, 6, 10, 1, service2, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2.5, 11, 20, 1, service2, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(0.70, 1, 5, 1, service3, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(1.4, 6, 10, 1, service3, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2.2, 11, 20, 1, service3, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2.3, 1, 5, 1, service4, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(2.8, 6, 10, 1, service4, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(3.2, 11, 20, 1, service4, zipCode));

            });

            zipCodeListFauquier.forEach(zipCode -> {
                docAmountPricingRepository.save(new DocAmountPricing(3, 1, 0, 1, service1, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(3, 1, 0, 1, service2, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(3, 1, 0, 1, service3, zipCode));
                docAmountPricingRepository.save(new DocAmountPricing(3, 1, 0, 1, service4, zipCode));

            });
//            SAVING WEEKDAYS

            WeekDay sunday = weekDayRepository.save(new WeekDay(ActionHistory.WeekDayName.SUNDAY));
            WeekDay monday = weekDayRepository.save(new WeekDay(ActionHistory.WeekDayName.MONDAY));
            WeekDay tuesday = weekDayRepository.save(new WeekDay(ActionHistory.WeekDayName.TUESDAY));
            WeekDay wednesday = weekDayRepository.save(new WeekDay(ActionHistory.WeekDayName.WEDNESDAY));
            WeekDay thursday = weekDayRepository.save(new WeekDay(ActionHistory.WeekDayName.THURSDAY));
            WeekDay friday = weekDayRepository.save(new WeekDay(ActionHistory.WeekDayName.FRIDAY));
            WeekDay saturday = weekDayRepository.save(new WeekDay(ActionHistory.WeekDayName.SATURDAY));

            List<WeekDay> workDays = Arrays.asList(monday, tuesday, wednesday, thursday, friday);
            List<WeekDay> weekend = Arrays.asList(saturday, sunday);
            List<WeekDay> allDays = Arrays.asList(sunday, monday, tuesday, wednesday, thursday, friday, saturday);


//            SAVING AGENT SCHEDULE

            saveAgentSchedule(workDays, agentInPerson1, Arrays.asList(
                    new Hour(LocalTime.of(9, 0), LocalTime.of(12, 0)),
                    new Hour(LocalTime.of(13, 0), LocalTime.of(17, 0))));

            saveAgentSchedule(workDays, agentInPerson2, Arrays.asList(
                    new Hour(LocalTime.of(11, 0), LocalTime.of(16, 0)),
                    new Hour(LocalTime.of(18, 0), LocalTime.of(22, 0))));

            saveAgentSchedule(workDays, agentInPerson3, Collections.singletonList(new Hour(LocalTime.of(14, 0), LocalTime.of(20, 0))));

            saveAgentSchedule(workDays, agentInPerson4, Arrays.asList(
                    new Hour(LocalTime.of(19, 0), LocalTime.of(23, 59)),
                    new Hour(LocalTime.of(0, 0), LocalTime.of(5, 0))
            ));

            saveAgentSchedule(workDays, agentInPerson5, Arrays.asList(
                    new Hour(LocalTime.of(12, 0), LocalTime.of(15, 0)),
                    new Hour(LocalTime.of(21, 0), LocalTime.of(23, 59))));

            saveAgentSchedule(weekend, agentInPerson6, Arrays.asList(
                    new Hour(LocalTime.of(9, 0), LocalTime.of(12, 0)),
                    new Hour(LocalTime.of(13, 0), LocalTime.of(17, 0))));

            saveAgentSchedule(allDays, agentInPerson7, Arrays.asList(
                    new Hour(LocalTime.of(10, 0), LocalTime.of(13, 0)),
                    new Hour(LocalTime.of(14, 0), LocalTime.of(19, 0))));

            saveAgentSchedule(allDays, agentInPerson8, Collections.singletonList(
                    new Hour(LocalTime.of(14, 0), LocalTime.of(18, 0))));

        } else if (initialMode.equals("never")) {
            List<Permission> permissionList = permissionRepository.findAll();
            List<PermissionName> permissionNames = Arrays.asList(PermissionName.values());
            List<PermissionName> notSavedPermission = permissionNames.stream().filter(permissionName -> !isDbSaved(permissionName, permissionList)).collect(Collectors.toList());
            List<Role> roles = roleRepository.findAll();

            List<PermissionRole> permissionRoles = new ArrayList<>();

            for (PermissionName permissionName : notSavedPermission) {

                Permission savedPermission = permissionRepository.save(
                        new Permission(permissionName));

                for (RoleName roleName : permissionName.roleNames) {
                    permissionRoles.add(new PermissionRole(
                            getRoleByRoleName(roles, roleName), savedPermission));
                }
            }
            permissionRoleRepository.saveAll(permissionRoles);
        }
    }

    private Role getRoleByRoleName(List<Role> roles, RoleName roleName) {
        for (Role role : roles) {
            if (role.getName().equals(roleName))
                return role;
        }
        return null;
    }

    private boolean isDbSaved(PermissionName permissionName, List<Permission> permissions) {
        return permissions.stream().anyMatch(permission -> permission.getPermissionName().equals(permissionName));
    }

    private void saveAgentSchedule(List<WeekDay> weekdays, User agent, List<Hour> hours) {
        weekdays.forEach(weekDay -> {
            AgentSchedule agentSchedule = agentScheduleRepository.save(new AgentSchedule(agent, weekDay, false));
            hours.forEach(hour -> {
                hourRepository.save(new Hour(hour.getFromHour(), hour.getTillHour(), agentSchedule));
            });
        });
    }
}

package uz.pdp.appduonotarypraktikaserver.service.team1service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.*;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponseModel;
import uz.pdp.appduonotarypraktikaserver.repository.*;
import uz.pdp.appduonotarypraktikaserver.resModels.team1.CheckedTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderService {


    final ZipCodeRepository zipCodeRepository;
    final MainServiceRepository mainServiceRepository;
    final ServicePriceRepository servicePriceRepository;
    final ServiceRepository serviceRepository;
    final AdditionalServicePriceRepository additionalServicePriceRepository;
    final AdditionalServiceRepository additionalServiceRepository;
    final OutOfServiceRepository outOfServiceRepository;
    final OrderAdditionalServicePriceRepository orderAdditionalServicePriceRepository;
    final UserRepository userRepository;
    final CountryRepository countryRepository;
    final OrderRepository orderRepository;
    final DocAmountPricingRepository docAmountPricingRepository;
    final TimeDurationRepository durationRepository;
    final AgentScheduleRepository agentScheduleRepository;
    final UserZipCodeRepository userZipCodeRepository;
    final TimeTableRepository timeTableRepository;
    final AgentHourOffRepository agentHourOffRepository;

    public OrderService(ZipCodeRepository zipCodeRepository, MainServiceRepository mainServiceRepository, ServicePriceRepository servicePriceRepository, ServiceRepository serviceRepository, AdditionalServicePriceRepository additionalServicePriceRepository, AdditionalServiceRepository additionalServiceRepository, OutOfServiceRepository outOfServiceRepository, OrderAdditionalServicePriceRepository orderAdditionalServicePriceRepository, UserRepository userRepository, CountryRepository countryRepository, OrderRepository orderRepository, DocAmountPricingRepository docAmountPricingRepository, TimeDurationRepository durationRepository, AgentScheduleRepository agentScheduleRepository, UserZipCodeRepository userZipCodeRepository, TimeTableRepository timeTableRepository, AgentHourOffRepository agentHourOffRepository) {
        this.zipCodeRepository = zipCodeRepository;
        this.mainServiceRepository = mainServiceRepository;
        this.servicePriceRepository = servicePriceRepository;
        this.serviceRepository = serviceRepository;
        this.additionalServicePriceRepository = additionalServicePriceRepository;
        this.additionalServiceRepository = additionalServiceRepository;
        this.outOfServiceRepository = outOfServiceRepository;
        this.orderAdditionalServicePriceRepository = orderAdditionalServicePriceRepository;
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
        this.orderRepository = orderRepository;
        this.docAmountPricingRepository = docAmountPricingRepository;
        this.durationRepository = durationRepository;
        this.agentScheduleRepository = agentScheduleRepository;
        this.userZipCodeRepository = userZipCodeRepository;
        this.timeTableRepository = timeTableRepository;
        this.agentHourOffRepository = agentHourOffRepository;
    }


    public ApiResponseModel getServicePriceByZipCode(String zipCode) {
        return new ApiResponseModel(true, "success", servicePriceRepository.findAllByZipCodeZipCode(zipCode));
    }


    public ApiResponseModel getAdditionalServicePriceByServicePrice(UUID servicePriceId) {
        ServicePrice servicePrice = servicePriceRepository.findById(servicePriceId).orElseThrow(() -> new ResourceNotFound("ServicePrice", "id", servicePriceId));
        return new ApiResponseModel(true, "success", additionalServicePriceRepository.findAllByService(servicePrice.getService()));
    }

    public ApiResponseModel getDocAmountPricingByService(UUID servicePriceId, int docAmount) {
        ServicePrice servicePrice = servicePriceRepository.findById(servicePriceId).orElseThrow(() -> new ResourceNotFound("ServicePrice", "id", servicePriceId));
        List<DocAmountPricing> allByServiceAndZipCode = docAmountPricingRepository.findAllByServiceAndZipCode(servicePrice.getService(), servicePrice.getZipCode());
        for (DocAmountPricing docAmountPricing : allByServiceAndZipCode) {
            if (docAmountPricing.getFromCount() <= docAmount && docAmountPricing.getTillCount() == 0) {
                int x = docAmount / docAmountPricing.getEveryCount();
                if (docAmount % docAmountPricing.getEveryCount() != 0) {
                    x++;
                }
                double price = x * docAmountPricing.getPrice() + servicePrice.getPrice();
                return new ApiResponseModel(true, "success", price);
            }

            if (docAmountPricing.getFromCount() <= docAmount && docAmountPricing.getTillCount() >= docAmount) {
                int x = docAmount / docAmountPricing.getEveryCount();
                if (docAmount % docAmountPricing.getEveryCount() != 0) {
                    x++;
                }
                double price = x * docAmountPricing.getPrice() + servicePrice.getPrice();
                return new ApiResponseModel(true, "success", price);
            }


        }
        return null;
    }

    public List<LocalTime> splitTime() {
        int intervalTime = durationRepository.findAll().get(0).getDurationTime();
        List<LocalTime> timeList = new ArrayList<>();
        LocalTime time = LocalTime.of(0, 0);
        for (int i = 0; i < 24 * 60 / intervalTime; i++) {
            timeList.add(time);
            time = time.plusMinutes(intervalTime);
        }
        return timeList;
    }

    public int getTotalSpendingTime(ServicePrice servicePrice, int docAmount) {

        if (docAmount <= servicePrice.getService().getInitialCount()) {
            return servicePrice.getService().getInitialSpendingTime();
        }

        return servicePrice.getService().getInitialSpendingTime() + ((docAmount - servicePrice.getService().getInitialCount()) * servicePrice.getService().getEverySpendingTime());
    }

    public List<CheckedTime> getAvailableTimes(LocalDate date, ServicePrice servicePrice, int docAmount) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("hh:mm a");
        int totalSpendingTime = getTotalSpendingTime(servicePrice, docAmount);
        List<LocalTime> times = splitTime();
        List<CheckedTime> checkedTimes = new ArrayList<>();

        for (LocalTime time : times) {
            if (date.getDayOfMonth() == LocalDate.now().getDayOfMonth() && time.isBefore(LocalTime.now())) continue;
            CheckedTime checkedTime = new CheckedTime((time.format(pattern)), false);
            List<AgentSchedule> agentSchedules = agentScheduleRepository.findByZipCodeAndWeekdayAndTime(servicePrice.getZipCode().getId(), date.getDayOfWeek().name(), time, time.plusMinutes(totalSpendingTime));

            agentSchedules.forEach(agentSchedule -> {
                LocalDateTime from = LocalDateTime.of(date, time);
                LocalDateTime till = LocalDateTime.of(date, time.plusMinutes(totalSpendingTime));
                Optional<TimeTable> agentTimeTable = timeTableRepository.getTimeTableByAgent(from, till, agentSchedule.getUser().getId());
                Optional<AgentHourOff> agentHourOff = agentHourOffRepository.getAgentOffById(from, till, agentSchedule.getUser().getId());

                if (!agentHourOff.isPresent() && !agentTimeTable.isPresent())
                    checkedTime.setAvalaible(true);


            });
            checkedTimes.add(checkedTime);

        }
        return checkedTimes;
    }


    public User getLessOrderAgentByDate(LocalDate date, List<User> agents) {
        User lessOrderAgent = null;
        int agentOrderCount = 100;
        LocalDate date1 = date.plusDays(1);
        for (User agent : agents) {
            int i = timeTableRepository.countAllByAgentAndFromTimeBetween(agent, date.atStartOfDay(), date1.atStartOfDay());
            if (i < agentOrderCount) {
                agentOrderCount = i;
                lessOrderAgent = agent;
            }
        }
        return lessOrderAgent;
    }

    public ApiResponse getEmptyAgentsByTime(LocalDate date, LocalTime time, ServicePrice servicePrice, int docAmount) {
        try {
            int totalSpendingTime = getTotalSpendingTime(servicePrice, docAmount);
            List<User> users = new ArrayList<>();

            if (date.getDayOfMonth() == LocalDate.now().getDayOfMonth() && time.isBefore(time.now())) {
                return new ApiResponse("error date and time", false, null);
            }
            List<AgentSchedule> agentSchedules = agentScheduleRepository.findByZipCodeAndWeekdayAndTime(servicePrice.getZipCode().getId(), date.getDayOfWeek().name(), time, time.plusMinutes(totalSpendingTime));

            LocalDateTime from = LocalDateTime.of(date, time);
            LocalDateTime till = LocalDateTime.of(date, time.plusMinutes(totalSpendingTime));

            agentSchedules.forEach(agentSchedule -> {
                Optional<TimeTable> agentTimeTable = timeTableRepository.getTimeTableByAgent(from, till, agentSchedule.getUser().getId());
                Optional<AgentHourOff> agentHourOff = agentHourOffRepository.getAgentOffById(from, till, agentSchedule.getUser().getId());
                if (!agentHourOff.isPresent() && !agentTimeTable.isPresent()) {
                    users.add(agentSchedule.getUser());
                }
            });
            User lessOrderAgentByDate = getLessOrderAgentByDate(date, users);
            TimeTable timeTable = new TimeTable();
            timeTable.setAgent(lessOrderAgentByDate);
            timeTable.setFromTime(from);
            timeTable.setTillTime(till);
            timeTable.setTempBooked(true);
            timeTableRepository.save(timeTable);
            return new ApiResponse("success", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("error", false);
    }







}

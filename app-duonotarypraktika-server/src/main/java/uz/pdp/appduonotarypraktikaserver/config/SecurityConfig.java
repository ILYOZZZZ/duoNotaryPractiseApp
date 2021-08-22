package uz.pdp.appduonotarypraktikaserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import uz.pdp.appduonotarypraktikaserver.security.AuthService;
import uz.pdp.appduonotarypraktikaserver.security.JwtAuthenticationEntryPoint;
import uz.pdp.appduonotarypraktikaserver.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthService authService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/csrf",
                        "/webjars/**")
                .permitAll()
                .antMatchers( "/api/publicHoliday/**","/api/additionalService/**","/api/additionalService","api/mainServices/changeActive/**","/api/services/**","api/mainServices/changeOnline/**","/api/companyWorkTime/**","/api/additionalService/**","/api/services","/api/OutOfService/**","/api/Services/**","/api/servicePrice/**","/api/additionalServicePrice","/api/docAmountPricing/**","/api/additionalServicePrice/**","/api/mainServiceWorkTime/**","/api/mainServices/**","/api/mainServices","/api/mail/**","/api/auth/**","/api/user/**","/api/docverify", "/api/docverify/**", "/api/stripe", "/api/file", "/api/file/**", "/api/category", "/api/kurs", "/api/brend", "/api/auth/login", "/api/user/checkPhone", "/api/user/checkUser", "/api/user/setPassword", "/api/sms")
                .permitAll()
                .antMatchers( HttpMethod.GET,"/api/publicHoliday","/api/additionalService","/api/additionalService","/api/companyWorkTime/**","/api/zipcode","/api/mainServiceWorkTime/**","/api/servicePrice/**","/api/docAmountPricing","/api/user/**","/api/user/getAdmins","/api/user/changeEnable","/api/user/changeActive","/api/mail/**","/api/mail","/api/docverify","/api/docverify/**","/api/stripe","/api/file/**","/api/file","/api/category","/api/kurs","/api/auth/login","/api/kurs/**","/api/brend","/api/user/me","/api/file/**","/api/sms","/api/services/**")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**")
                .permitAll()
                .antMatchers("/api/**")
                .permitAll();

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //@formatter:off

        super.configure(web);
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean factoryBean() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/templates");
        return bean;
    }
}

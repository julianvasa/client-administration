package com.client.administration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

import static com.client.administration.security.SecurityConstants.SIGN_UP_URL;

/**
 * Spring Security configurations
 *
 * @author julianvasa
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /* Inject an implementation of UserDetailsService: ApplicationUserDetailsService used for authentication */
    @Resource(name = "userDetailService")
    private UserDetailsService applicationUserDetailsService;

    /* Used for password encryption during user sign-up also for password decryption during user login */
    @Bean
    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* A method where specifies a custom implementation of UserDetailsService (ApplicationUserDetailsService)
    to load user-specific data in the security framework.
    I have also used this method to set the encrypt method used by the application (BCryptPasswordEncoder)  */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applicationUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /*
     * A method where we can define which resources are public and which are secured
     * I have set the SIGN_UP_URL endpoint as being public and everything else as being secured
     * I also configure CORS (Cross-Origin Resource Sharing) support through http.cors()
     * and we add a custom security filter in the Spring Security filter chain
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            // disables session creation on Spring Security
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /*
    Allow/restrict our CORS support. In our case I left it wide open by permitting requests from any source (/**).
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}

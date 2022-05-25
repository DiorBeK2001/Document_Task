package com.example.taskdoc.config;

import com.example.taskdoc.security.JwtAuthenticationFilter;
import com.example.taskdoc.security.JwtErrors;
import com.example.taskdoc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthService authService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    JwtErrors jwtError;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( authService ).passwordEncoder( passwordEncoder() );
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash( true );
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
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint( jwtError )
                .and()
                .sessionManagement()
                .sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                .and()
                .authorizeRequests()
                .antMatchers( "/",
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
                        "/webjars/**",
                        "/koinot/auth/register",
                        "/koinot/attachment/**",
                        "/koinot/auth/login",
                        "/koinot/auth/sendCode/**",
                        "/koinot/auth/verify/**",
                        "/koinot/news" ,
                        "/koinot/newsChild" ,
                        "/koinot/category/**" ,
                        "/koinot/tag/**" ,
                        "/koinot/feedback/**" ,
                        "/koinot/feedback" ,
                        "/koinot/news/**"  )
                .permitAll()
//                .antMatchers( HttpMethod.GET,"/koinot/stadium/**","/koinot/order/**" )
//                .permitAll( )
                //                .antMatchers( "/koinot/offer/**")
                //                .permitAll()
                //                .antMatchers("/koinot/admin").hasAuthority("ROLE_SUPER_ADMIN")
                .antMatchers( "/koinot/**" )
                .authenticated()
                .and()
                .formLogin();
        http.addFilterBefore( jwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class );
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        super.configure( web );
        web.httpFirewall( allowUrlEncodedSlashHttpFirewall() );
    }
}

package com.spring.backproject.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring.backproject.Service.UtilisateurdetaiSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecuConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    UtilisateurdetaiSer utilisateurdetaiSer;

@Override
  public void configure (HttpSecurity http) throws Exception {
      http.csrf().disable()
              .logout()
              .logoutUrl("/api/auth/logout")
             // URL de déconnexion
              .logoutSuccessUrl("/api/auth/login") // URL de redirection après la déconnexion
              .invalidateHttpSession(true) // Invalider la session HTTP
              .deleteCookies("JSESSIONID") // Supprimer les cookies nécessaires
              .permitAll()
              .and()
              .authorizeRequests()
              .antMatchers("/api/auth/**").permitAll()
              .antMatchers("/*").permitAll()
              .antMatchers("/account/**").hasAnyAuthority("user")
              .anyRequest().authenticated()


      ;}
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Ajoutez ici les origines autorisées
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Ajoutez ici les méthodes autorisées
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Ajoutez ici les en-têtes autorisés
        configuration.setAllowCredentials(true); // Autorisez les cookies et les en-têtes d'authentification

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/auth/logout", configuration); // Spécifiez l'URL de déconnexion
        return source;
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth){
    auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
@Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
   DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
   authenticationProvider.setPasswordEncoder(passwordEncoder());
   authenticationProvider.setUserDetailsService(utilisateurdetaiSer);
   return authenticationProvider;
}
   @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }




}


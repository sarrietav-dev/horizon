package co.com.isoft.horizon.config;

import co.com.isoft.horizon.filters.CustomAuthenticationFilter;
import co.com.isoft.horizon.filters.CustomAuthorizationFilter;
import co.com.isoft.horizon.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CustomAuthenticationFilter filter =
        new CustomAuthenticationFilter(authenticationManagerBean(), new TokenUtils());
    filter.setFilterProcessesUrl("/api/auth/login");

    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests().antMatchers("/api/login", "/api/auth/token").permitAll();
    http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/api/pqrs")
        .hasAnyAuthority("ROLE_RESIDENT", "ROLE_ADMIN", "ROLE_PROPRIETARY");
    http.authorizeRequests()
        .antMatchers(HttpMethod.PATCH, "/api/user/**/role")
        .hasAnyAuthority("ROLE_ADMIN");
    http.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/user")
        .hasAnyAuthority("ROLE_ADMIN");
    http.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/user/**")
        .hasAnyAuthority("ROLE_ADMIN");
    http.authorizeRequests().anyRequest().authenticated();

    http.addFilter(filter);
    http.addFilterBefore(
        new CustomAuthorizationFilter(new TokenUtils()),
        UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}

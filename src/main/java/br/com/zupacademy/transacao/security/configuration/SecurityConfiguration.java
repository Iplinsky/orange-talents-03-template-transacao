package br.com.zupacademy.transacao.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> 
			authorizeRequests
				.antMatchers("/transacoes/**").hasAuthority("SCOPE_propostas:read")
				.antMatchers(HttpMethod.GET, "/transacoes/**").hasAuthority("SCOPE_transacao:read")
				.antMatchers(HttpMethod.POST, "/transacoes/**").hasAuthority("SCOPE_transacao:write")
				.anyRequest().authenticated())
		        .csrf().disable()
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        .and()
		        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}
}

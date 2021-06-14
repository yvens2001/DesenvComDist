package opet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SegurancaWeb extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {

		return new DetalheServico();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncorder() { 
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provedor = new DaoAuthenticationProvider();
		provedor.setUserDetailsService(userDetailsService());
		provedor.setPasswordEncoder(passwordEncorder());
		
		return provedor;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/usuarios").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.usernameParameter("email")
				.defaultSuccessUrl("/usuarios")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
	}
	
	
}

package com.wallacegomes.automacaoedificacoes.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.wallacegomes.automacaoedificacoes.security.JWTAuthenticationFilter;
import com.wallacegomes.automacaoedificacoes.security.JWTAuthorizationFilter;
import com.wallacegomes.automacaoedificacoes.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //anotacao para usar a autorizacao nos endpoints
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	//Vetor que indica quais os caminhos nao precisam de autenticacao para acesso 
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};

	//vetor com caminhos livres para consulta
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/dispositivos/**",
			"/ambientes/**",
			"/equipamentos/**"
	};

	private static final String[] PUBLIC_MATCHERS_POST = {
			"/usuarios/**",
			"/dispositivos/**",
			"/ambientes/**",
			"/equipamentos/**"
			//"/auth/forgot/**"
	};

	@Override
	//sobre-escrita do metodo WebSecurityConfigurerAdapter 
	protected void configure(HttpSecurity http) throws Exception {
		
		//verifica se nos profile ativos existe o test e desabilita a autenticacao, para acessar o banco h2 
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
		
		//chama o bean corsConfigurationSource para liberar multiplus acesso e desabilita a configuracao de csrf (ataque por secao)
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll() //libera o metodo POST somente para o vetor
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //libera o metodo GET somente para o vetor 
			.antMatchers(PUBLIC_MATCHERS).permitAll() //permite todos os caminhos deste vetor
			.anyRequest().authenticated(); //exige autenticacao para todo o resto
		//add os filtros de autenticacao e autorizacao de tipo usuario
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		//garante que o backand nao cria sessao do usuario
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	//configuracao responsavel para informar qual o userDetailService e o encriptador da senha
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	//libera o acesso de multiplus caminhos
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean //responsavel por criptografar a senha do usuario
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}


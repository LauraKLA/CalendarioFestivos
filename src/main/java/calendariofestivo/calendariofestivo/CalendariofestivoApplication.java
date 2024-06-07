package calendariofestivo.calendariofestivo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



@SpringBootApplication
public class CalendariofestivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendariofestivoApplication.class, args);
	}
	@Bean
public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:4200");
    config.addAllowedMethod("*");
    config.addAllowedHeader("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return new CorsFilter(source);
}
} 


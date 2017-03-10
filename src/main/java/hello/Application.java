package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * pcf-demo REST consumer
 * Many Spring Boot developers always have their main class annotated with
 * @Configuration, @EnableAutoConfiguration and @ComponentScan. 
 * Since these annotations are so frequently used together
 * (especially if you follow the best practices above), 
 * Spring Boot provides a convenient @SpringBootApplication alternative.
 * 
 * for more #goto https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-using-springbootapplication-annotation.html
 * 
 * @author Jakub_Wierzchoslawsk
 *
 */
@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		
		/**
		 * IMPORTANT NOTICE
		 * 
		 * Download cert first and import it to your 
		 * Java trustcert repository.
		 * 
		 * for Java 8 cacerts file can be used and its
		 * located under %JAVA_HOME%/jre/lib/security/cacerts
		 * 
		 * To import downloaded cert to your cert repo use:
		 * <JAVA_HOME/bin>#keytool.exe -import -file <path_to_cert> -keystore <cacerts_location> -alias "pcf dev certificate"
		 * 
		 * Also, when starting PCF DEV use -k flag to import cert to repo
		 * #cf dev start -k
		 * s
		 */
        System.setProperty("javax.net.ssl.trustStore","c:/Program Files/Java/jdk1.8.0_121/jre/lib/security/cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	
	/**
	 * command line runner
	 * uncomment this & #mvn spring-boot:run
	 */
/*	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			
			
			List<Album> albumsResponse =
			        restTemplate.exchange("http://pcf-demo.local.pcfdev.io/albums",
			                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {
			            }).getBody();
			
			
			for (Album a: albumsResponse){
				System.out.println(a.getArtist());
				log.info(a.getArtist());
			}

		};
	}*/
}
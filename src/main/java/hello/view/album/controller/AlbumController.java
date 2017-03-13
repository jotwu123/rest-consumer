package hello.view.album.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import hello.domain.Album;

@Controller
public class AlbumController {
	private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	private static final String PCF_DEMO_SERVICE = "pcf-demo";
	

	@RequestMapping(value = "/showAlbums")
	public String showAlbums(Model model) {
		//String URL = "http://pcf-demo.local.pcfdev.io/albums";
		String URL = "http://localhost:8802/albums";

		ResponseEntity<List<Album>> rateResponse = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				});

		List<Album> albums = rateResponse.getBody();
		model.addAttribute("albums", albums);

		log.info("showAlbums go to view now");
		return "showAlbums";
	}
	
	
	@RequestMapping(value = "/discoverAndShowAlbums")
	public String discoverServiceAndShowAlbums(Model model) {
		ResponseEntity<List<Album>> rateResponse = restTemplate.exchange(findServiceByName(PCF_DEMO_SERVICE), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				});
		List<Album> albums = rateResponse.getBody();
		model.addAttribute("albums", albums);

		log.info("discoverAndShowAlbums go to view now");
		return "showAlbums";
	}
	
	
	
	private String findServiceByName(String serviceName) {
	    List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
	    String uri = "";
	    if (list != null && list.size() > 0 ) {
	    	
	        uri = list.get(0).getUri().toString();
	        return uri + "/albums";
	    }
	    return null;
	}

}

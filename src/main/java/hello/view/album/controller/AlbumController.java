package hello.view.album.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/showAlbums")
	public String showAlbums(Model model) {
		String URL = "http://pcf-demo.local.pcfdev.io/albums";

		ResponseEntity<List<Album>> rateResponse = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				});
		List<Album> albums = rateResponse.getBody();
		model.addAttribute("albums", albums);

		return "showAlbums";
	}

}

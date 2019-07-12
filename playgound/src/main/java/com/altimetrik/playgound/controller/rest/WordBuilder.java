package com.altimetrik.playgound.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/wordbuilder")
public class WordBuilder {
	
	@Autowired
	private RestTemplate restTemplate;
	
	// choose HashSet to remove removeDuplicates during permutations
	private Set<String> strList;
	
	@GetMapping
	public List<String> build(@RequestParam("phrase") String phrase, @RequestParam("length") int length){
		if("".equals(phrase))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phrase");
		String phraseTrimmed = phrase.replaceAll(" ", "");
		if(phraseTrimmed.length() < length)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input");
		strList = new HashSet<>();
		permutation(phraseTrimmed, length, "");
		System.out.println(strList.size());
		return englishWords();
	}
	
	private List<String> englishWords() {	
		List<String> result = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		String baseUrl = "https://owlbot.info/api/v3/dictionary/";
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));	
	    headers.add("Authorization", "Token b96dac5b4f51efb94661b2e7a9cef761adb8b0ca");
	    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	    HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
		strList.parallelStream().forEach(word -> {
			try {
				 ResponseEntity<String> responseBodey = restTemplate.exchange(baseUrl+word, HttpMethod.GET, requestEntity, String.class);
				 if(responseBodey.getStatusCodeValue() == 200) result.add(word);
			} catch (Exception e) {
				return;
			}
		});
		System.out.println("done");
		return result;
	}
	
	private void permutation(String string, int position, String prefix) {
		if (position == prefix.length()) {
			strList.add(prefix);
		}else {
			for (int i = 0; i < string.length(); i++) {
				String rem = string.substring(0, i) + string.substring(i + 1);
				permutation(rem, position, prefix + string.charAt(i));
			}
		}
	}
}

package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.FeedDto;
import com.example.model.Feed;
import com.example.service.IFeedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/feed")
@Api(value="Feed Controller REST Endpoint",description="Feed Controller API")
public class FeedControllerV2 {

	@Autowired
	private IFeedService feedService;


	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="returns the list of all feeds",response=FeedDto.class)
	public ResponseEntity<?> viewAll() {
		
			List<Feed> feed = feedService.viewAll();
			HttpHeaders responseHeader = new HttpHeaders();
			return new ResponseEntity<>(feed, responseHeader, HttpStatus.OK);
	}

	
	@PostMapping
	@ApiOperation(value="method to create feeds",response=FeedDto.class)
	public ResponseEntity<?> create(@Valid @RequestBody FeedDto feedDto) {
		
			Feed feed = feedService.create(feedDto);
			HttpHeaders responseHeader = new HttpHeaders();
			return new ResponseEntity<>(feed, responseHeader, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value="Updates Feed whose ID is provided in the URL",response=FeedDto.class)
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody FeedDto feedDto) {
			Feed feed = feedService.update(feedDto, id);
			HttpHeaders responseHeader = new HttpHeaders();
			return new ResponseEntity<>(feed, responseHeader, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value="Deletes Feed whose ID is provided in the URL",response=FeedDto.class)
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
			feedService.delete(id);
			return ResponseEntity.ok().build();
	}
}

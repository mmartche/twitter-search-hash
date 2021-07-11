package com.marcelo.api.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcelo.api.controller.dto.DetailsTopicDto;
import com.marcelo.api.controller.dto.TopicDto;
import com.marcelo.api.controller.form.UpdateTopicForm;
import com.marcelo.api.controller.form.TopicForm;
import com.marcelo.api.modelo.Topic;
import com.marcelo.api.repository.TopicsRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {

	@Autowired
	private TopicsRepository topicsRepository;

	@GetMapping
	@Cacheable(value = "listTopics")
	public Page<TopicDto> list(@RequestParam(required = false) String title, @PageableDefault(page = 0, size = 2) Pageable pagination) {

//		Pageable paginacao = PageRequest.of(page, size, Direction.ASC, sort);
 
		if (title== null) {
			Page<Topic> topics = topicsRepository.findAll(pagination);
			return TopicDto.converter(topics);
		} else {
			Page<Topic> topics = topicsRepository.findByTitle(title, pagination);
			return TopicDto.converter(topics);
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value="listTopics", allEntries = true)
	public ResponseEntity<TopicDto> cadastrar(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.converter();
		topicsRepository.save(topic);

		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailsTopicDto> detalhar(@PathVariable Long id) {
		Optional<Topic> topic = topicsRepository.findById(id);
		if (topic.isPresent()) {
			return ResponseEntity.ok(new DetailsTopicDto(topic.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="listTopics", allEntries = true)
	public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
		Optional<Topic> optional = topicsRepository.findById(id);
		if (optional.isPresent()) {
			Topic topic = form.update(id, topicsRepository);
			return ResponseEntity.ok(new TopicDto(topic));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="listTopics", allEntries = true)
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Topic> topic = topicsRepository.findById(id);
		if (topic.isPresent()) {
			topicsRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}

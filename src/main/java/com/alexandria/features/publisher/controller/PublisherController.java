package com.alexandria.features.publisher.controller;

import com.alexandria.exception.ObjectNotFoundException;
import com.alexandria.features.publisher.dto.PublisherCreationDto;
import com.alexandria.features.publisher.dto.PublisherDto;
import com.alexandria.features.publisher.entity.Publisher;
import com.alexandria.features.publisher.service.PublisherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/publishers")
public class PublisherController {

  private final PublisherService publisherService;

  @Autowired
  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<PublisherDto> getPublisherById(@PathVariable Long id)
      throws ObjectNotFoundException {
    Publisher publisher = publisherService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(PublisherDto.fromEntity(publisher));
  }

  @GetMapping
  public ResponseEntity<List<PublisherDto>> getAllPublishers() {
    List<PublisherDto> publishers = publisherService.findAll().stream()
        .map(PublisherDto::fromEntity)
        .toList();
    return ResponseEntity.status(HttpStatus.OK).body(publishers);
  }

  @PostMapping
  public ResponseEntity<PublisherDto> createPublisher(
      @RequestBody PublisherCreationDto publisherCreationDto) {
    Publisher publisher = publisherService.create(publisherCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(PublisherDto.fromEntity(publisher));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PublisherDto> updatePublisher(@PathVariable Long id,
      @RequestBody PublisherCreationDto publisherCreationDto) throws ObjectNotFoundException {
    Publisher publisher = publisherService.update(id, publisherCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.OK).body(PublisherDto.fromEntity(publisher));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PublisherDto> deletePublisherById(@PathVariable Long id)
      throws ObjectNotFoundException {
    Publisher publisher = publisherService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(PublisherDto.fromEntity(publisher));
  }
}

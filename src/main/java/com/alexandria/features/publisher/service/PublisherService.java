package com.alexandria.features.publisher.service;

import com.alexandria.exception.ObjectNotFoundException;
import com.alexandria.features.publisher.entity.Publisher;
import com.alexandria.features.publisher.repository.PublisherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

  private final PublisherRepository publisherRepository;

  @Autowired
  public PublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  public Publisher findById(Long id) throws ObjectNotFoundException {
    return publisherRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("publisher not found."));
  }

  public List<Publisher> findAll() {
    return publisherRepository.findAll();
  }

  public Publisher create(Publisher publisher) {
    return publisherRepository.save(publisher);
  }

  public Publisher update(Long id, Publisher publisher) throws ObjectNotFoundException {
    Publisher publisherFromDb = findById(id);

    publisherFromDb.setName(publisher.getName());
    publisherFromDb.setAddress(publisher.getAddress());

    return publisherRepository.save(publisherFromDb);
  }

  public Publisher deleteById(Long id) throws ObjectNotFoundException {
    Publisher publisher = findById(id);

    publisherRepository.deleteById(id);

    return publisher;
  }
}

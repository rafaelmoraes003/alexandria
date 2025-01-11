package com.alexandria.features.publisher.dto;

import com.alexandria.features.publisher.entity.Publisher;

public record PublisherCreationDto(String name, String address) {

  public Publisher toEntity() {
    return new Publisher(name, address);
  }
}

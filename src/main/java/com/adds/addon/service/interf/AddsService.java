package com.adds.addon.service.interf;

import com.adds.addon.entities.Adds;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface AddsService {

    ResponseEntity<Collection<Adds>> getAddsAnyCategory();
    ResponseEntity<Collection<Adds>> getAddsWithCategory(String category);
}

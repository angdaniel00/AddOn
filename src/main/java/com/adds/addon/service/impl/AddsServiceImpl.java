package com.adds.addon.service.impl;

import com.adds.addon.entities.Adds;
import com.adds.addon.repository.AddsRepo;
import com.adds.addon.service.interf.AddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AddsServiceImpl implements AddsService {

    @Autowired
    @Qualifier("addsRepo")
    private AddsRepo addsRepo;

    @Override
    public ResponseEntity<Collection<Adds>> getAddsAnyCategory() {
        return null;
    }

    @Override
    public ResponseEntity<Collection<Adds>> getAddsWithCategory(String category) {
        return null;
    }
}

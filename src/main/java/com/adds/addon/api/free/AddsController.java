package com.adds.addon.api.free;

import com.adds.addon.entities.Adds;
import com.adds.addon.service.interf.AddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("public/adds/")
public class AddsController {

    @Autowired
    private AddsService addsService;

    @GetMapping
    public ResponseEntity<Collection<Adds>> getAdds(){
        return addsService.getAddsAnyCategory();
    }

    @GetMapping("category/{categoryAdds}")
    public ResponseEntity<Collection<Adds>> getAddsCategory(@PathVariable("categoryAdds") String category){
        return addsService.getAddsWithCategory(category);
    }

}

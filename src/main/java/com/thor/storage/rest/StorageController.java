package com.thor.storage.rest;

import com.thor.storage.dto.StorageResponse;
import com.thor.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/storage")
public class StorageController {

    private final StorageService service;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public StorageResponse getById(@PathVariable String id) {
        return this.service.getById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return this.service.insert(file);
    }
}

package com.turkcell.spring_starter.controller;

import com.turkcell.spring_starter.dto.*;
import com.turkcell.spring_starter.service.TagServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagsController {
    private final TagServiceImpl tagService;

    public TagsController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public CreatedTagResponse create(@RequestBody CreateTagRequest request) {
        return tagService.create(request);
    }

    @GetMapping
    public List<ListTagResponse> getAll() {
        return tagService.getAll();
    }

    @GetMapping("/{id}")
    public GetTagResponse getById(@PathVariable UUID id) {
        return tagService.getById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody UpdateTagRequest request) {
        tagService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        tagService.delete(id);
    }
}

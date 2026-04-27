package com.turkcell.library_application.controller;

import com.turkcell.library_application.dto.CreateReturnRequest;
import com.turkcell.library_application.dto.CreatedReturnResponse;
import com.turkcell.library_application.dto.ListReturnResponse;
import com.turkcell.library_application.service.ReturnLogServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnsController {
    private final ReturnLogServiceImpl returnLogService;

    public ReturnsController(ReturnLogServiceImpl returnLogService) {
        this.returnLogService = returnLogService;
    }

    @PostMapping
    public CreatedReturnResponse create(@RequestBody CreateReturnRequest request) {
        return returnLogService.create(request);
    }

    @GetMapping
    public List<ListReturnResponse> getAll() {
        return returnLogService.getAll();
    }
}

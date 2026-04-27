package com.turkcell.library_application.controller;

import com.turkcell.library_application.dto.CreateStaffRequest;
import com.turkcell.library_application.dto.CreatedStaffResponse;
import com.turkcell.library_application.dto.ListStaffResponse;
import com.turkcell.library_application.dto.UpdateStaffRequest;
import com.turkcell.library_application.service.StaffServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffServiceImpl staffService;

    public StaffController(StaffServiceImpl staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public CreatedStaffResponse create(@RequestBody CreateStaffRequest request) {
        return staffService.create(request);
    }

    @GetMapping
    public List<ListStaffResponse> getAll() {
        return staffService.getAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UpdateStaffRequest request) {
        staffService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        staffService.delete(id);
    }
}

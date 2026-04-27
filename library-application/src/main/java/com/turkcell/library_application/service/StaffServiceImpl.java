package com.turkcell.library_application.service;

import com.turkcell.library_application.dto.CreateStaffRequest;
import com.turkcell.library_application.dto.CreatedStaffResponse;
import com.turkcell.library_application.dto.ListStaffResponse;
import com.turkcell.library_application.dto.UpdateStaffRequest;
import com.turkcell.library_application.entity.Staff;
import com.turkcell.library_application.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImpl {
    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public CreatedStaffResponse create(CreateStaffRequest request) {
        Staff staff = new Staff();
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setRole(request.getRole());
        staff.setPasswordHash(request.getPassword()); // In real app, hash it!

        staff = staffRepository.save(staff);
        return new CreatedStaffResponse(staff.getId(), staff.getFirstName() + " " + staff.getLastName());
    }

    public List<ListStaffResponse> getAll() {
        List<Staff> staffList = staffRepository.findAll();
        List<ListStaffResponse> responseList = new ArrayList<>();
        for (Staff staff : staffList) {
            responseList.add(new ListStaffResponse(staff.getId(), staff.getFirstName(), staff.getLastName(), staff.getRole()));
        }
        return responseList;
    }

    public void update(int id, UpdateStaffRequest request) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setRole(request.getRole());
        staffRepository.save(staff);
    }

    public void delete(int id) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        staffRepository.delete(staff);
    }
}

package com.turkcell.library_application.service;

import com.turkcell.library_application.dto.CreateReturnRequest;
import com.turkcell.library_application.dto.CreatedReturnResponse;
import com.turkcell.library_application.dto.ListReturnResponse;
import com.turkcell.library_application.entity.Book;
import com.turkcell.library_application.entity.Borrow;
import com.turkcell.library_application.entity.ReturnLog;
import com.turkcell.library_application.entity.Staff;
import com.turkcell.library_application.repository.BookRepository;
import com.turkcell.library_application.repository.BorrowRepository;
import com.turkcell.library_application.repository.ReturnLogRepository;
import com.turkcell.library_application.repository.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnLogServiceImpl {
    private final ReturnLogRepository returnLogRepository;
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final StaffRepository staffRepository;

    public ReturnLogServiceImpl(ReturnLogRepository returnLogRepository, 
                                BorrowRepository borrowRepository, 
                                BookRepository bookRepository, 
                                StaffRepository staffRepository) {
        this.returnLogRepository = returnLogRepository;
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.staffRepository = staffRepository;
    }

    @Transactional
    public CreatedReturnResponse create(CreateReturnRequest request) {
        Borrow borrow = borrowRepository.findById(request.getBorrowId()).orElseThrow();
        
        if ("Returned".equals(borrow.getStatus())) {
            throw new RuntimeException("This book has already been returned!");
        }

        Staff staff = staffRepository.findById(request.getStaffId()).orElseThrow();

        ReturnLog returnLog = new ReturnLog();
        returnLog.setBorrow(borrow);
        returnLog.setStaff(staff);
        returnLog.setLateFee(request.getLateFee());
        returnLog.setNotes(request.getNotes());

        // Update Borrow Status
        borrow.setStatus("Returned");
        borrowRepository.save(borrow);

        // Increment Book Copies
        Book book = borrow.getBook();
        book.setCopies(book.getCopies() + 1);
        bookRepository.save(book);

        returnLog = returnLogRepository.save(returnLog);

        return new CreatedReturnResponse(returnLog.getId(), book.getTitle(), borrow.getStatus());
    }

    public List<ListReturnResponse> getAll() {
        List<ReturnLog> logs = returnLogRepository.findAll();
        List<ListReturnResponse> responseList = new ArrayList<>();
        for (ReturnLog log : logs) {
            responseList.add(new ListReturnResponse(
                log.getId(), 
                log.getBorrow().getBook().getTitle(), 
                log.getBorrow().getStudent().getName() + " " + log.getBorrow().getStudent().getSurname(),
                log.getReturnDate(),
                log.getLateFee()
            ));
        }
        return responseList;
    }
}

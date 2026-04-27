package com.turkcell.library_application.service;

import com.turkcell.library_application.dto.CreateBorrowRequest;
import com.turkcell.library_application.dto.CreatedBorrowResponse;
import com.turkcell.library_application.dto.ListBorrowResponse;
import com.turkcell.library_application.entity.Book;
import com.turkcell.library_application.entity.Borrow;
import com.turkcell.library_application.entity.Staff;
import com.turkcell.library_application.entity.Student;
import com.turkcell.library_application.repository.BookRepository;
import com.turkcell.library_application.repository.BorrowRepository;
import com.turkcell.library_application.repository.StaffRepository;
import com.turkcell.library_application.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowServiceImpl {
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;

    public BorrowServiceImpl(BorrowRepository borrowRepository, 
                             BookRepository bookRepository, 
                             StudentRepository studentRepository, 
                             StaffRepository staffRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
    }

    @Transactional
    public CreatedBorrowResponse create(CreateBorrowRequest request) {
        Book book = bookRepository.findById(request.getBookId()).orElseThrow();
        
        if (book.getCopies() <= 0) {
            throw new RuntimeException("No copies available for this book!");
        }

        Student student = studentRepository.findById(request.getStudentId()).orElseThrow();
        Staff staff = staffRepository.findById(request.getStaffId()).orElseThrow();

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setStudent(student);
        borrow.setStaff(staff);
        borrow.setDueDate(request.getDueDate());

        // Decrement copies
        book.setCopies(book.getCopies() - 1);
        bookRepository.save(book);

        borrow = borrowRepository.save(borrow);

        return new CreatedBorrowResponse(borrow.getId(), book.getTitle(), student.getName() + " " + student.getSurname(), borrow.getDueDate());
    }

    public List<ListBorrowResponse> getAll() {
        List<Borrow> borrows = borrowRepository.findAll();
        List<ListBorrowResponse> responseList = new ArrayList<>();
        for (Borrow borrow : borrows) {
            responseList.add(new ListBorrowResponse(
                borrow.getId(), 
                borrow.getBook().getTitle(), 
                borrow.getStudent().getName() + " " + borrow.getStudent().getSurname(),
                borrow.getBorrowDate(),
                borrow.getDueDate(),
                borrow.getStatus()
            ));
        }
        return responseList;
    }
}

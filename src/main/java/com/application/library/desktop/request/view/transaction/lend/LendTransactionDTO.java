package com.application.library.desktop.request.view.transaction.lend;


import com.application.library.desktop.request.view.UserListDTO;
import com.application.library.desktop.request.view.book.BaseBookDTO;
import com.application.library.desktop.request.view.core.UUIDEntityDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LendTransactionDTO extends UUIDEntityDTO {
    private LocalDate deadlineDate;
    private LocalDateTime returnDate;
    private Double lateFeePaid;
    private boolean returned;
    private BaseBookDTO book;
    private UserListDTO user;
    private UserListDTO lender;

    public LendTransactionDTO() {
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Double getLateFeePaid() {
        return lateFeePaid;
    }

    public void setLateFeePaid(Double lateFeePaid) {
        this.lateFeePaid = lateFeePaid;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public BaseBookDTO getBook() {
        return book;
    }

    public void setBook(BaseBookDTO book) {
        this.book = book;
    }

    public UserListDTO getUser() {
        return user;
    }

    public void setUser(UserListDTO user) {
        this.user = user;
    }

    public UserListDTO getLender() {
        return lender;
    }

    public void setLender(UserListDTO lender) {
        this.lender = lender;
    }
}

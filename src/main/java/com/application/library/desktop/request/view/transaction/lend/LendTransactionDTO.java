package com.application.library.desktop.request.view.transaction.lend;


import com.application.library.desktop.request.view.UserDTO;
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
    private UserDTO user;
    private UserDTO lender;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getLender() {
        return lender;
    }

    public void setLender(UserDTO lender) {
        this.lender = lender;
    }
}

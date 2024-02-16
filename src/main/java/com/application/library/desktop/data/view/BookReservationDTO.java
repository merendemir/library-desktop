package com.application.library.desktop.data.view;


import com.application.library.desktop.data.view.book.BookDTO;
import com.application.library.desktop.data.view.core.IntegerEntityDTO;

import java.time.LocalDate;

public class BookReservationDTO extends IntegerEntityDTO {
    private UserDTO user;
    private BookDTO book;
    private LocalDate reservationDate;
    private boolean completed;

    public BookReservationDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

package com.application.library.desktop.request.view;


import com.application.library.desktop.request.view.book.BookDTO;
import com.application.library.desktop.request.view.core.IntegerEntityDTO;

import java.util.Set;

public class ReadingListDTO extends IntegerEntityDTO {
    private UserListDTO user;
    private Set<BookDTO> books;

    public ReadingListDTO() {
    }

    public UserListDTO getUser() {
        return user;
    }

    public void setUser(UserListDTO user) {
        this.user = user;
    }

    public Set<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDTO> books) {
        this.books = books;
    }
}

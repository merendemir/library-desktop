package com.application.library.desktop.data.view;


import com.application.library.desktop.data.view.book.BookDTO;
import com.application.library.desktop.data.view.core.IntegerEntityDTO;

import java.util.Set;

public class ReadingListDTO extends IntegerEntityDTO {
    private UserDTO user;
    private Set<BookDTO> books;

    public ReadingListDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Set<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDTO> books) {
        this.books = books;
    }
}

package com.application.library.desktop.gui.home.impl.book.menu;

import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.constants.TitleConstants;
import com.application.library.desktop.enumerations.NotificationType;

import com.application.library.desktop.gui.home.impl.book.BookOperationsPanel;
import com.application.library.desktop.gui.home.impl.book.menu.dialog.MoveBookDialog;
import com.application.library.desktop.gui.home.impl.book.menu.dialog.ShowBookShelfDialog;
import com.application.library.desktop.gui.home.impl.book.menu.dialog.UpdateBookDialog;
import com.application.library.desktop.gui.home.impl.book.menu.item.DeleteBookMenuItem;
import com.application.library.desktop.gui.home.impl.book.menu.item.MoveBookMenuItem;
import com.application.library.desktop.gui.home.impl.book.menu.item.ShowBookShelfMenuItem;
import com.application.library.desktop.gui.home.impl.book.menu.item.UpdateBookMenuItem;

import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.listener.event.UpdatePanelDataEvent;
import com.application.library.desktop.request.view.book.BookDTO;
import com.application.library.desktop.service.HttpRequestService;
import com.application.library.desktop.utils.ApplicationContextHelper;
import com.application.library.desktop.utils.access.AccessControlUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

@Service
public class BooksPopupMenu extends JPopupMenu {

    private BookDTO selectedBook;

    private final UpdateBookMenuItem updateBookMenuItem = new UpdateBookMenuItem();
    private final DeleteBookMenuItem deleteBookMenuItem = new DeleteBookMenuItem();
    private final ShowBookShelfMenuItem showBookShelfMenuItem = new ShowBookShelfMenuItem();
    private final MoveBookMenuItem moveBookMenuItem = new MoveBookMenuItem();

    private final HttpRequestService httpRequestService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ApplicationContextHelper applicationContextHelper;
    private final UpdateBookDialog updateBookDialog;
    private final ShowBookShelfDialog showBookShelfDialog;
    private final MoveBookDialog moveBookDialog;

    public BooksPopupMenu(HttpRequestService httpRequestService, ApplicationEventPublisher applicationEventPublisher, ApplicationContextHelper applicationContextHelper, UpdateBookDialog updateBookDialog, ShowBookShelfDialog showBookShelfDialog, MoveBookDialog moveBookDialog) {
        this.httpRequestService = httpRequestService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.applicationContextHelper = applicationContextHelper;
        this.updateBookDialog = updateBookDialog;
        this.showBookShelfDialog = showBookShelfDialog;
        this.moveBookDialog = moveBookDialog;

        setComponentActions();
    }

    public void showPopupMenu(Component c, int x, int y) {
        removeAll();
        addMenuItemByRole();
        show(c, x, y);
    }

    private void addMenuItemByRole() {
        if (AccessControlUtils.hasMatchingAuthority(updateBookMenuItem)) {
            add(updateBookMenuItem);
        }

        if (AccessControlUtils.hasMatchingAuthority(deleteBookMenuItem)) {
            add(deleteBookMenuItem);
        }

        add(showBookShelfMenuItem);

        if (AccessControlUtils.hasMatchingAuthority(moveBookMenuItem)) {
            add(moveBookMenuItem);
        }
    }

    private void setComponentActions() {
        updateBookMenuItem.addActionListener(e -> {
            updateBookDialog.showDialog(selectedBook);
        });

        deleteBookMenuItem.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(applicationContextHelper.getCurrentFrame(),
                    MessageConstants.DELETE_BOOK_CONFIRMATION, TitleConstants.DELETE_BOOK,
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                Long id = httpRequestService.deleteBookById(selectedBook.getId());
                if (id != null) {
                    applicationEventPublisher.publishEvent(new UpdatePanelDataEvent(this, BookOperationsPanel.class));
                    applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.SHELF_DELETE_SUCCESS, NotificationType.SUCCESS));
                }
            }
        });

        showBookShelfMenuItem.addActionListener(e -> showBookShelfDialog.showDialog(selectedBook.getShelf()));

        moveBookMenuItem.addActionListener(e -> {
            moveBookDialog.showDialog(selectedBook);
        });
    }

    public void setSelectedBook(BookDTO selectedBook) {
        this.selectedBook = selectedBook;
    }
}


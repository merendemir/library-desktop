package com.application.library.desktop.gui.home.impl.shelf.menu;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.constants.TitleConstants;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.gui.home.impl.shelf.ShelvesOperationPanel;
import com.application.library.desktop.gui.home.impl.shelf.menu.dialog.ShowBooksOnShelfDialog;
import com.application.library.desktop.gui.home.impl.shelf.menu.dialog.UpdateShelfDialog;
import com.application.library.desktop.gui.home.impl.shelf.menu.item.UpdateShelfMenuItem;
import com.application.library.desktop.gui.home.impl.shelf.menu.item.DeleteShelfMenuItem;
import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.listener.event.UpdatePanelDataEvent;
import com.application.library.desktop.request.view.shelf.ShelfDTO;
import com.application.library.desktop.service.HttpRequestService;
import com.application.library.desktop.utils.ApplicationContextHelper;
import com.application.library.desktop.utils.access.AccessControlUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.swing.*;

import java.awt.*;

@Service
public class ShelvesPopupMenu extends JPopupMenu {

    private ShelfDTO selectedShelf;

    private final UpdateShelfMenuItem updateShelfMenuItem = new UpdateShelfMenuItem();
    private final DeleteShelfMenuItem deleteShelfMenuItem = new DeleteShelfMenuItem();
    private final JMenuItem showBooksOnShelfMenuItem = new JMenuItem(TitleConstants.SHOW_BOOKS_ON_SHELF, IconConstants.MENU_BOOK_ICON);

    private final HttpRequestService httpRequestService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UpdateShelfDialog updateShelfDialog;
    private final ApplicationContextHelper applicationContextHelper;
    private final ShowBooksOnShelfDialog showBooksOnShelfDialog;

    public ShelvesPopupMenu(HttpRequestService httpRequestService, ApplicationEventPublisher applicationEventPublisher, UpdateShelfDialog updateShelfDialog, ApplicationContextHelper applicationContextHelper, ShowBooksOnShelfDialog showBooksOnShelfDialog) {
        this.httpRequestService = httpRequestService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.updateShelfDialog = updateShelfDialog;
        this.applicationContextHelper = applicationContextHelper;
        this.showBooksOnShelfDialog = showBooksOnShelfDialog;

        add(updateShelfMenuItem);
        add(deleteShelfMenuItem);

        setComponentActions();
    }

    public void showPopupMenu(Component c, int x, int y) {
        removeAll();
        addMenuItemByRole();
        show(c, x, y);
    }

    private void addMenuItemByRole() {
        if (AccessControlUtils.hasMatchingAuthority(updateShelfMenuItem)) {
            add(updateShelfMenuItem);
        }

        if (AccessControlUtils.hasMatchingAuthority(deleteShelfMenuItem)) {
            add(deleteShelfMenuItem);
        }

        add(showBooksOnShelfMenuItem);
    }

    private void setComponentActions() {
        updateShelfMenuItem.addActionListener(e -> {
            updateShelfDialog.showDialog(selectedShelf.getId(), selectedShelf.getName(), selectedShelf.getCapacity());
        });

        deleteShelfMenuItem.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(applicationContextHelper.getCurrentFrame(),
                    MessageConstants.DELETE_SHELF_CONFIRMATION, TitleConstants.DELETE_SHELF,
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                Long id = httpRequestService.deleteShelfById(selectedShelf.getId());
                if (id != null) {
                    applicationEventPublisher.publishEvent(new UpdatePanelDataEvent(this, ShelvesOperationPanel.class));                    applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.SHELF_DELETE_SUCCESS, NotificationType.SUCCESS));
                }
            }
        });

        showBooksOnShelfMenuItem.addActionListener(e -> showBooksOnShelfDialog.showDialog(selectedShelf.getId()));


    }

    public void setSelectedShelfId(ShelfDTO selectedShelf) {
        this.selectedShelf = selectedShelf;
    }
}


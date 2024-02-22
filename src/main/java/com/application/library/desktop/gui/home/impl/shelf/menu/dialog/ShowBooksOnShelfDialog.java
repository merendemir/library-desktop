package com.application.library.desktop.gui.home.impl.shelf.menu.dialog;

import com.application.library.desktop.constants.TitleConstants;
import com.application.library.desktop.core.BaseDialog;
import com.application.library.desktop.gui.home.impl.book.ShowBooksPanel;
import com.application.library.desktop.gui.home.impl.book.BooksPanelCreateService;
import com.application.library.desktop.utils.ApplicationContextHelper;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class ShowBooksOnShelfDialog extends BaseDialog {
    private JPanel contentPane;
    private final ShowBooksPanel showBooksPanel;
    private final ApplicationContextHelper applicationContextHelper;

    public ShowBooksOnShelfDialog(BooksPanelCreateService booksPanelCreateService, ApplicationContextHelper applicationContextHelper) {
        this.showBooksPanel = booksPanelCreateService.createNewBooksPanel();
        this.applicationContextHelper = applicationContextHelper;
        $$$setupUI$$$();
        setContentPane(contentPane);
        setTitle(TitleConstants.SHOW_BOOKS_ON_SHELF);
        setModal(true);
    }

    public void showDialog(Long shelfId) {
        pack();
        setLocationRelativeTo(applicationContextHelper.getCurrentFrame());
        showBooksPanel.setShelfId(shelfId);
        showBooksPanel.selected();
        setVisible(true);
    }

    private void createUIComponents() {
        contentPane = showBooksPanel;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}

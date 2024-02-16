package com.application.library.desktop.gui.home.tree.book;

import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.home.models.MutableTreeNode;
import com.application.library.desktop.listener.event.MenuSelectedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Set;

@Component
public class BooksTreeNode extends DefaultMutableTreeNode implements MutableTreeNode {
    private final ApplicationEventPublisher applicationEventPublisher;

    private static final MenuOptions menuOptions = MenuOptions.BOOKS;

    public BooksTreeNode(ApplicationEventPublisher applicationEventPublisher) {
        super(menuOptions.getTitle());
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void treeSelected() {
        applicationEventPublisher.publishEvent(new MenuSelectedEvent(this, MenuOptions.BOOKS));
    }


    @Override
    public String getImageIconPath() {
        return "/icons/menu-books.png";
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_LIBRARIAN, UserRole.ROLE_USER, UserRole.ROLE_ADMIN);
    }
}

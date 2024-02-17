package com.application.library.desktop.gui.menu.book;

import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.menu.IMenu.SelectableTreeNode;
import com.application.library.desktop.listener.event.MenuSelectedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Set;

@Component
public class AddBookTreeNode extends DefaultMutableTreeNode implements SelectableTreeNode {
    private final ApplicationEventPublisher applicationEventPublisher;

    public AddBookTreeNode(ApplicationEventPublisher applicationEventPublisher) {
        super(MenuOptions.ADD_BOOK.getTitle());
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void treeSelected() {
        applicationEventPublisher.publishEvent(new MenuSelectedEvent(this, MenuOptions.ADD_BOOK));
    }

    @Override
    public String getImageIconPath() {
        return "/icons/menu-add-books.png";
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_LIBRARIAN, UserRole.ROLE_ADMIN);
    }
}

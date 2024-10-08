package com.application.library.desktop.gui.menu;

import com.application.library.desktop.constants.SystemVariables;
import com.application.library.desktop.gui.menu.book.AddBookTreeNode;
import com.application.library.desktop.gui.menu.book.BookManagementTreeNode;
import com.application.library.desktop.gui.menu.book.BooksTreeNode;
import com.application.library.desktop.gui.menu.settings.SettingsTreeNode;
import com.application.library.desktop.gui.menu.shelf.AddShelfTreeNode;
import com.application.library.desktop.gui.menu.shelf.ShelfManagementTreeNode;
import com.application.library.desktop.gui.menu.shelf.ShelvesTreeNode;
import com.application.library.desktop.gui.menu.user.AddUserTreeNode;
import com.application.library.desktop.gui.menu.user.UserManagementTreeNode;
import com.application.library.desktop.gui.menu.user.UsersTreeNode;
import com.application.library.desktop.gui.menu.IMenu.ImageIconTreeNode;
import com.application.library.desktop.utils.access.AccessRestricted;
import com.application.library.desktop.gui.menu.IMenu.SelectableTreeNode;
import com.application.library.desktop.utils.access.AccessControlUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;

@Component
public class MenuTree extends JTree {

    private static final Color MENU_COLOR = SystemVariables.SYSTEM_DEFAULT_COLOR;

    private final MenuTreeNode menuTreeNode = new MenuTreeNode();
    private final BookManagementTreeNode bookManagementTreeNode = new BookManagementTreeNode();
    private final UserManagementTreeNode userManagementTreeNode = new UserManagementTreeNode();
    private final ShelfManagementTreeNode shelfManagementTreeNode = new ShelfManagementTreeNode();
    private final BooksTreeNode booksTreeNode;
    private final AddBookTreeNode addBookTreeNode;
    private final UsersTreeNode usersTreeNode;
    private final AddUserTreeNode addUserTreeNode;
    private final SettingsTreeNode settingsTreeNode;
    private final ShelvesTreeNode shelvesTreeNode;
    private final AddShelfTreeNode addShelfTreeNode;

    public MenuTree(BooksTreeNode booksTreeNode, AddBookTreeNode addBookTreeNode, UsersTreeNode usersTreeNode, AddUserTreeNode addUserTreeNode, SettingsTreeNode settingsTreeNode, ShelvesTreeNode shelvesTreeNode, AddShelfTreeNode addShelfTreeNode) {
        this.booksTreeNode = booksTreeNode;
        this.addBookTreeNode = addBookTreeNode;
        this.usersTreeNode = usersTreeNode;
        this.addUserTreeNode = addUserTreeNode;
        this.settingsTreeNode = settingsTreeNode;
        this.shelvesTreeNode = shelvesTreeNode;
        this.addShelfTreeNode = addShelfTreeNode;

        setForeground(MENU_COLOR);
        setBackground(MENU_COLOR);

        setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                if (value instanceof ImageIconTreeNode node) {
                    setIcon(node.getImageIconPath());
                }

                setBackgroundNonSelectionColor(MENU_COLOR);
                return this;
            }
        });

        addTreeSelectionListener(e -> {
            if (getLastSelectedPathComponent() instanceof SelectableTreeNode node) {
                node.treeSelected();
            }
        });
    }

    public void updateMenu() {
        DefaultTreeModel treeModel = new DefaultTreeModel(getMenu());
        setModel(treeModel);
        expendAllNodes();
    }

    private DefaultMutableTreeNode getMenu() {
        List<DefaultMutableTreeNode> children = List.of(
                getAccessRestrictedRootMenuByChildrenList(bookManagementTreeNode, List.of(booksTreeNode, addBookTreeNode)),
                getAccessRestrictedRootMenuByChildrenList(userManagementTreeNode, List.of(usersTreeNode, addUserTreeNode)),
                getAccessRestrictedRootMenuByChildrenList(shelfManagementTreeNode, List.of(shelvesTreeNode, addShelfTreeNode)),
                settingsTreeNode
        );

        return getAccessRestrictedRootMenuByChildrenList(menuTreeNode, children);

    }

    private DefaultMutableTreeNode getAccessRestrictedRootMenuByChildrenList(DefaultMutableTreeNode rootNode, List<DefaultMutableTreeNode> children) {
        rootNode.removeAllChildren();
        for (DefaultMutableTreeNode child : children) {
            if (child instanceof AccessRestricted menu) {
                if (AccessControlUtils.hasMatchingAuthority(menu)) {
                    rootNode.add(child);
                }
            } else {
                rootNode.add(child);
            }
        }

        return rootNode;
    }

    private void expendAllNodes() {
        for (int i = 0; i < getRowCount(); i++) {
            expandRow(i);
        }
    }

}

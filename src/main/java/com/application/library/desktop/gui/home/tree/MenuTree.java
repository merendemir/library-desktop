package com.application.library.desktop.gui.home.tree;

import com.application.library.desktop.gui.home.models.AccessRestricted;
import com.application.library.desktop.gui.home.models.ImageIconTreeNode;
import com.application.library.desktop.gui.home.models.MutableTreeNode;
import com.application.library.desktop.gui.home.tree.book.AddBookTreeNode;
import com.application.library.desktop.gui.home.tree.book.BookManagementTreeNode;
import com.application.library.desktop.gui.home.tree.book.BooksTreeNode;
import com.application.library.desktop.utils.AccessControlUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;

@Component
public class MenuTree extends JTree {

    private static final Color menuColor = Color.CYAN;

    private final MenuTreeNode menuTreeNode = new MenuTreeNode();
    private final BookManagementTreeNode bookManagementTreeNode = new BookManagementTreeNode();
    private final BooksTreeNode booksTreeNode;
    private final AddBookTreeNode addBookTreeNode;

    public MenuTree(BooksTreeNode booksTreeNode, AddBookTreeNode addBookTreeNode) {
        this.booksTreeNode = booksTreeNode;
        this.addBookTreeNode = addBookTreeNode;

        setForeground(menuColor);
        setBackground(menuColor);

        setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                if (value instanceof ImageIconTreeNode node) {
                    setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(node.getImageIconPath()))));
                }

                setBackgroundNonSelectionColor(menuColor);
                return this;
            }
        });

        DefaultTreeModel treeModel = new DefaultTreeModel(getMenu());
        setModel(treeModel);

        addTreeSelectionListener(e -> {
            if (getLastSelectedPathComponent() instanceof MutableTreeNode node) {
                node.treeSelected();
            }
        });

        expendAllNodes();
    }

    private DefaultMutableTreeNode getMenu() {
        DefaultMutableTreeNode rootNode = menuTreeNode;
        List<DefaultMutableTreeNode> children = List.of(
                getBookManagementMenu()
        );

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

    private DefaultMutableTreeNode getBookManagementMenu() {
        DefaultMutableTreeNode bookManagementMenu = bookManagementTreeNode;

        List<DefaultMutableTreeNode> children = List.of(
                booksTreeNode,
                addBookTreeNode);

        for (DefaultMutableTreeNode child : children) {
            if (child instanceof AccessRestricted menu) {
                if (AccessControlUtils.hasMatchingAuthority(menu)) {
                    bookManagementMenu.add(child);
                }
            } else {
                bookManagementMenu.add(child);
            }

        }

        return bookManagementMenu;
    }

    private void expendAllNodes() {
        for (int i = 0; i < getRowCount(); i++) {
            expandRow(i);
        }
    }

}

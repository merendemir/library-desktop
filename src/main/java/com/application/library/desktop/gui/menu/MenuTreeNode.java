package com.application.library.desktop.gui.menu;

import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.menu.IMenu.ImageIconTreeNode;
import org.springframework.stereotype.Component;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Set;

@Component
public class MenuTreeNode extends DefaultMutableTreeNode implements ImageIconTreeNode {

    public MenuTreeNode() {
        super(MenuOptions.HOME.getTitle());
    }

    @Override
    public String getImageIconPath() {
        return "/icons/menu.png";
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return null;
    }
}

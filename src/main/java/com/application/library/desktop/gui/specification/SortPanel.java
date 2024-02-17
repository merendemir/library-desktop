package com.application.library.desktop.gui.specification;

import com.application.library.desktop.enumerations.SortDirection;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;


public class SortPanel extends JPanel {
    private JComboBox<String> sortDirectionComboBox;
    private JComboBox<String> sortParameterComboBox;
    private JPanel contentPane;

    public SortPanel(String[] sortParameters) {
        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);
        String[] directionNames = Arrays.stream(SortDirection.values()).map(SortDirection::getDescription).toArray(String[]::new);

        sortDirectionComboBox.setModel(new DefaultComboBoxModel<>(directionNames));
        sortParameterComboBox.setModel(new DefaultComboBoxModel<>(sortParameters));

        clearSortPanel();
    }

    public JComboBox<String> getSortDirectionComboBox() {
        return sortDirectionComboBox;
    }

    public JComboBox<String> getSortParameterComboBox() {
        return sortParameterComboBox;
    }

    public void clearSortPanel() {
        sortDirectionComboBox.setSelectedIndex(-1);
        sortParameterComboBox.setSelectedIndex(-1);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Sort Parameter", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, null));
        sortParameterComboBox = new JComboBox();
        panel1.add(sortParameterComboBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Sort Direction", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, null));
        sortDirectionComboBox = new JComboBox();
        panel2.add(sortDirectionComboBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}

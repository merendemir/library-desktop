package com.application.library.desktop.gui.home.impl.user;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.enumerations.SortDirection;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.home.impl.panel.main.IMainPanel;
import com.application.library.desktop.gui.home.impl.user.menu.UserPopupMenu;
import com.application.library.desktop.gui.specification.PaginationPanel;
import com.application.library.desktop.gui.specification.SortPanel;
import com.application.library.desktop.gui.table.CustomDataTable;
import com.application.library.desktop.request.view.UserListDTO;
import com.application.library.desktop.service.HttpRequestService;
import com.application.library.desktop.utils.TimeUtil;
import com.application.library.desktop.utils.access.AccessControlUtils;
import com.application.library.desktop.utils.pagination.PaginationResponseDto;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsersPanel extends JPanel implements IMainPanel {
    String[] columnNames = {"id", "X", "First Name", "Last Name", "Email", "Creation Date", "Updated Date"};
    private final Map<String, String> sortMap = new HashMap<>(Map.of(
            "Fist Name", "firstName",
            "Last Name", "lastName",
            "Email", "email",
            "Creation Date", "createdAt",
            "Updated Date", "updatedAt"
    ));
    private final CustomDataTable userDataTable = new CustomDataTable(columnNames, 0);
    private final SortPanel sortPanel = new SortPanel(sortMap.keySet().toArray(new String[0]));

    private final PaginationPanel paginationPanel = new PaginationPanel(this::sendRequestAndUpdateUsersTable);

    //SERVICE
    private final HttpRequestService httpRequestService;
    private final UserPopupMenu userPopupMenu;

    public UsersPanel(HttpRequestService httpRequestService, UserPopupMenu userPopupMenu) {
        this.httpRequestService = httpRequestService;
        this.userPopupMenu = userPopupMenu;

        $$$setupUI$$$();
        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);

        userTypeFilterComboBox.setModel(new DefaultComboBoxModel<>(UserRole.values()));
        userTypeFilterComboBox.setSelectedIndex(-1);

        setComponentActions();
        setComponentDefaults();
        updateClearAndSearchButtonStatus();
        updateClearAndSearchButtonStatus();
        userDataTable.getColumnModel().getColumn(1).setMaxWidth(35);
    }

    private void setComponentDefaults() {
        searchButton.setIcon(IconConstants.SEARCH_ICON);
        clearButton.setIcon(IconConstants.CLEAR_ICON);
    }

    private void setComponentActions() {
        searchButton.addActionListener(e -> {
            sendRequestAndUpdateUsersTable();
        });

        clearButton.addActionListener(e -> {
            userTypeFilterComboBox.setSelectedIndex(-1);
            sortPanel.clearSortPanel();
            paginationPanel.clearPanel();
            searchButton.setEnabled(false);

            sendRequestAndUpdateUsersTable();
        });

        userTypeFilterComboBox.addActionListener(e -> updateClearAndSearchButtonStatus());
        sortPanel.getSortParameterComboBox().addActionListener(e -> updateClearAndSearchButtonStatus());
        sortPanel.getSortDirectionComboBox().addActionListener(e -> updateClearAndSearchButtonStatus());

        userDataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = userDataTable.rowAtPoint(e.getPoint());
                    userDataTable.setRowSelectionInterval(row, row);

                    userPopupMenu.setSelectedUserId(Long.parseLong(userDataTable.getSelectedRowId(row)));
                    userPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public void sendRequestAndUpdateUsersTable() {
        userDataTable.removeAllData();
        PaginationResponseDto<List<UserListDTO>> users = httpRequestService.getAllUser(getParams());
        paginationPanel.fillPageDetails(users);
        users.getContent().forEach(this::addRow);
    }

    private Map<String, String> getParams() {
        Map<String, String> params = paginationPanel.getPaginationData();

        if (userTypeFilterComboBox.getSelectedIndex() != -1) {
            params.put("userType", Objects.requireNonNull(userTypeFilterComboBox.getSelectedItem()).toString());
        }

        if (sortPanel.getSortParameterComboBox().getSelectedIndex() != -1 && sortPanel.getSortDirectionComboBox().getSelectedIndex() != -1) {
            String sortKey = Objects.requireNonNull(sortPanel.getSortParameterComboBox().getSelectedItem()).toString();
            params.put("sortParam", sortMap.get(sortKey));

            SortDirection sortDirection = SortDirection.getByDescription(String.valueOf(sortPanel.getSortDirectionComboBox().getSelectedItem()));
            if (sortDirection != null) params.put("direction", sortDirection.name());
        }

        return params;
    }

    public void addRow(UserListDTO userListDTO) {
        userDataTable.addRow(convertUserDto(userListDTO));
    }

    private String[] convertUserDto(UserListDTO userListDTO) {
        return new String[]{
                String.valueOf(userListDTO.getId()),
                String.valueOf(userDataTable.getRowCount() + 1),
                userListDTO.getFirstName(),
                userListDTO.getLastName(),
                userListDTO.getEmail(),
                TimeUtil.formatDateString(userListDTO.getCreatedAt()),
                TimeUtil.formatDateString(userListDTO.getUpdatedAt())
        };
    }

    private void updateClearAndSearchButtonStatus() {
        boolean status = userTypeFilterComboBox.getSelectedIndex() != -1 || (sortPanel.getSortParameterComboBox().getSelectedIndex() != -1 && sortPanel.getSortDirectionComboBox().getSelectedIndex() != -1);
        searchButton.setEnabled(status);
        clearButton.setEnabled(status);
    }

    @Override
    public void selected() {
        paginationPanel.clearPanel();
        filterByRolePanel.setVisible(AccessControlUtils.hasAdminAuthority());
        sendRequestAndUpdateUsersTable();
    }

    private void createUIComponents() {
        usersTableGUI = userDataTable;
        sortPanelGUI = sortPanel;
        paginationPanelGUI = paginationPanel;
    }

    //COMPONENTS
    private JPanel contentPane;

    private JTable usersTableGUI;
    private JPanel sortPanelGUI;
    private JComboBox<UserRole> userTypeFilterComboBox;
    private JPanel filterByRolePanel;
    private JButton searchButton;
    private JButton clearButton;
    private JPanel paginationPanelGUI;

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 5), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(usersTableGUI);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.add(sortPanelGUI, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        filterByRolePanel = new JPanel();
        filterByRolePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        filterByRolePanel.setVisible(true);
        panel2.add(filterByRolePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        filterByRolePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Filter By Role", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, null));
        userTypeFilterComboBox = new JComboBox();
        filterByRolePanel.add(userTypeFilterComboBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("");
        searchButton.setToolTipText("APPLY SELECTIONS");
        panel3.add(searchButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearButton = new JButton();
        clearButton.setText("");
        clearButton.setToolTipText("CLEAR SELECTIONS");
        panel3.add(clearButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        contentPane.add(spacer1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.add(paginationPanelGUI, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel4.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}

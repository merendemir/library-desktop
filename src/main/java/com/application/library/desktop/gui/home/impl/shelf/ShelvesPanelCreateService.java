package com.application.library.desktop.gui.home.impl.shelf;

import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.gui.specification.PaginationPanel;
import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.request.dto.SaveShelfRequestDto;
import com.application.library.desktop.request.view.shelf.ShelfDTO;
import com.application.library.desktop.service.HttpRequestService;
import com.application.library.desktop.supplier.TaskSupplier;
import com.application.library.desktop.utils.pagination.PaginationResponseDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelvesPanelCreateService {

    private final HttpRequestService httpRequestService;
    private final ApplicationEventPublisher applicationEventPublisher;

    ShelvesPanelCreateService(HttpRequestService httpRequestService, ApplicationEventPublisher applicationEventPublisher) {
        this.httpRequestService = httpRequestService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public ShowShelvesPanel createNewShelvesPanel() {
        ShowShelvesPanel showShelvesPanel = new ShowShelvesPanel();
        showShelvesPanel.setUpdateShelvesDataTableTaskSupplier(() -> updateShelvesDataTable(showShelvesPanel));
        return showShelvesPanel;
    }

    public ShowShelvesPanel createNewShelvesPanel(TaskSupplier taskSupplier) {
        ShowShelvesPanel showShelvesPanel = new ShowShelvesPanel();
        showShelvesPanel.setUpdateShelvesDataTableTaskSupplier(taskSupplier);
        return showShelvesPanel;
    }

    public SaveShelfPanel createNewSaveShelfPanel(TaskSupplier taskSupplier) {
        SaveShelfPanel saveShelfPanel = new SaveShelfPanel();
        saveShelfPanel.setSaveShelfTaskSupplier(taskSupplier);
        return saveShelfPanel;
    }

    public SaveShelfPanel createNewSaveShelfPanel() {
        SaveShelfPanel saveShelfPanel = new SaveShelfPanel();
        saveShelfPanel.setSaveShelfTaskSupplier(() -> saveShelfByPanel(saveShelfPanel));
        return saveShelfPanel;
    }

    private void updateShelvesDataTable(ShowShelvesPanel showShelvesPanel) {
        PaginationPanel paginationPanel = showShelvesPanel.getPaginationPanel();
        PaginationResponseDto<List<ShelfDTO>> allShelf = httpRequestService.getAllShelf(paginationPanel.getPaginationData());
        showShelvesPanel.removeAllRows();
        allShelf.getContent().forEach(showShelvesPanel::addRow);
        paginationPanel.fillPageDetails(allShelf);
    }

    private void saveShelfByPanel(SaveShelfPanel saveShelfPanel) {
        SaveShelfRequestDto saveShelfRequestDto = new SaveShelfRequestDto();
        saveShelfRequestDto.setName(saveShelfPanel.getShelfName());
        saveShelfRequestDto.setCapacity(saveShelfPanel.getCapacity());
        Long id = httpRequestService.saveShelf(saveShelfRequestDto);

        if (id != null) {
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.SHELF_SAVE_SUCCESS, NotificationType.SUCCESS));
            saveShelfPanel.selected();
        }
    }
}

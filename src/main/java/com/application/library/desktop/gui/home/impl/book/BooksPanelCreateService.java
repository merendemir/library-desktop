package com.application.library.desktop.gui.home.impl.book;

import com.application.library.desktop.gui.home.impl.shelf.ShelvesPanelCreateService;
import com.application.library.desktop.gui.specification.PaginationPanel;
import com.application.library.desktop.request.view.book.BookDTO;
import com.application.library.desktop.service.HttpRequestService;
import com.application.library.desktop.utils.pagination.PaginationResponseDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksPanelCreateService {

    private final HttpRequestService httpRequestService;
    private final ShelvesPanelCreateService shelvesPanelCreateService;

    BooksPanelCreateService(HttpRequestService httpRequestService, ShelvesPanelCreateService shelvesPanelCreateService) {
        this.httpRequestService = httpRequestService;
        this.shelvesPanelCreateService = shelvesPanelCreateService;
    }

    public ShowBooksPanel createNewBooksPanel() {
        ShowBooksPanel showBooksPanel = new ShowBooksPanel();
        showBooksPanel.setUpdateBooksDataTableTaskSupplier(() -> updateBooksDataTable(showBooksPanel));
        return showBooksPanel;
    }

    public SaveBookPanel createNewSaveBooksPanel() {
        return new SaveBookPanel(shelvesPanelCreateService.createNewShelvesPanel());
    }

    private void updateBooksDataTable(ShowBooksPanel showBooksPanel) {
        PaginationPanel paginationPanel = showBooksPanel.getPaginationPanel();
        PaginationResponseDto<List<BookDTO>> allBooks;

        if (showBooksPanel.getShelfId() == null) {
            allBooks = httpRequestService.getAllBooks(paginationPanel.getPaginationData());
        } else {
            allBooks = httpRequestService.getAllBooksByShelfId(showBooksPanel.getShelfId(), paginationPanel.getPaginationData());
        }

        showBooksPanel.removeAllRows();
        allBooks.getContent().forEach(showBooksPanel::addRow);
        paginationPanel.fillPageDetails(allBooks);
    }

//    private void saveShelfByPanel(SaveShelfPanel saveShelfPanel) {
//        SaveShelfRequestDto saveShelfRequestDto = new SaveShelfRequestDto();
//        saveShelfRequestDto.setName(saveShelfPanel.getShelfName());
//        saveShelfRequestDto.setCapacity(saveShelfPanel.getCapacity());
//        Long id = httpRequestService.saveShelf(saveShelfRequestDto);
//
//        if (id != null) {
//            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.SHELF_SAVE_SUCCESS, NotificationType.SUCCESS));
//            saveShelfPanel.selected();
//        }
//    }
}

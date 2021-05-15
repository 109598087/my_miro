package ntut.csie.islab.miro.adapter.gateway.eventbus.google;

import com.google.common.eventbus.Subscribe;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.event.StickyNoteCreatedDomainEvent;
import ntut.csie.islab.miro.usecase.eventHandler.NotifyBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifyBoardAdapter { // for commit textFigure to board
    private NotifyBoard notifyBoard;

    @Autowired
    public NotifyBoardAdapter(NotifyBoard notifyBoard) {
        this.notifyBoard = notifyBoard;
    }

    @Subscribe
    public void whenTextFigureCreated(StickyNoteCreatedDomainEvent stickyNoteCreatedDomainEvent) {
        notifyBoard.whenTextFigureCreated(stickyNoteCreatedDomainEvent);
    }



}

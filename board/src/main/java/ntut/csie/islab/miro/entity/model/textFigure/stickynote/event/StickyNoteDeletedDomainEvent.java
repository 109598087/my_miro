package ntut.csie.islab.miro.entity.model.textFigure.stickynote.event;

import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.model.common.DateProvider;

import java.util.Date;
import java.util.UUID;

public class StickyNoteDeletedDomainEvent extends DomainEvent {
    private UUID boardId;
    private UUID textFigureId;

    public StickyNoteDeletedDomainEvent(UUID boardId, UUID textFigureId) {
        super(DateProvider.now());
        this.boardId = boardId;
        this.textFigureId = textFigureId;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public UUID getTextFigureId() {
        return textFigureId;
    }
}

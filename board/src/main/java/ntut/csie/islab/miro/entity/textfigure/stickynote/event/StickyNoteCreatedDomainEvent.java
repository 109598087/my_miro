package ntut.csie.islab.miro.entity.textfigure.stickynote.event;

import ntut.csie.islab.miro.entity.textfigure.Position;
import ntut.csie.islab.miro.entity.textfigure.Style;
import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.model.common.DateProvider;

import java.util.UUID;

public class StickyNoteCreatedDomainEvent extends DomainEvent {
    private final UUID boardId;
    private final UUID textfigureId;
    private final Position position;
    private final String content;
    private final Style style;


    public StickyNoteCreatedDomainEvent(UUID boardId, UUID textfigureId, Position position, String content, Style style) {
        super(DateProvider.now());
        this.boardId = boardId;
        this.textfigureId = textfigureId;
        this.position = position;
        this.content = content;
        this.style = style;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public UUID getTextfigureId() {
        return textfigureId;
    }
}

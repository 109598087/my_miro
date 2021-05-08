package ntut.csie.islab.miro.entity.textfigure.stickynote;

import ntut.csie.islab.miro.entity.textfigure.Position;
import ntut.csie.islab.miro.entity.textfigure.Style;
import ntut.csie.islab.miro.entity.textfigure.TextFigure;
import ntut.csie.islab.miro.entity.textfigure.stickynote.event.StickyNoteCreatedDomainEvent;

import java.util.UUID;

public class StickyNote extends TextFigure {

    public StickyNote(UUID boardId, Position position, String content, Style style) {
        super(boardId, position, content, style);
        // publish StickyNoteCreated domain event
        addDomainEvent(new StickyNoteCreatedDomainEvent(boardId, getFigureId()));
    }
}

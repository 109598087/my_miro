package ntut.csie.islab.miro.entity.model.stickyNote;

import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.islab.miro.entity.model.textFigure.Position;
import ntut.csie.islab.miro.entity.model.textFigure.ShapeKindEnum;
import ntut.csie.islab.miro.entity.model.textFigure.Style;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.StickyNote;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.event.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StickyNoteDomainEventTest {
    private TextFigure createStickyNote() {
        return new StickyNote(UUID.randomUUID(), new Position(1.0, 1.0), "content", new Style(10, ShapeKindEnum.TRIANGLE, 87.2, 100, "#123456"));
    }

    @Test
    public void create_a_stickyNote_then_publishes_a_stickyNote_created_domain_event() {
        TextFigure stickyNote = createStickyNote();
        assertEquals(1, stickyNote.getDomainEvents().size());
    }

}

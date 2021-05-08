package ntut.csie.islab.miro.entity.textfigure.stickynote;

import ntut.csie.islab.miro.entity.textfigure.Position;
import ntut.csie.islab.miro.entity.textfigure.ShapeKindEnum;
import ntut.csie.islab.miro.entity.textfigure.Style;
import ntut.csie.islab.miro.entity.textfigure.TextFigure;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StickyNoteDomainEventTest {

    @Test
    public void create_a_stickyNote_than_publishes_a_stickyNote_created_domain_event() {
        TextFigure stickyNote = new StickyNote(UUID.randomUUID(), new Position(1.0, 1.0), "stickynote_content", new Style(20, ShapeKindEnum.RECTANGLE, 100, 100, "f9f900"));
        assertEquals(1, stickyNote.getDomainEvents().size());
    }
}

package ntut.csie.islab.miro.entity.textfigure.stickynote;

import ntut.csie.islab.miro.entity.textfigure.Position;
import ntut.csie.islab.miro.entity.textfigure.ShapeKindEnum;
import ntut.csie.islab.miro.entity.textfigure.Style;
import ntut.csie.islab.miro.entity.textfigure.TextFigure;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StickyNoteTest {
    @Test
    public void stickynote_get_test() {
        UUID boardId = UUID.randomUUID();
        Position position = new Position(100, 100);
        String content = "stickyNote content";
        Style style = new Style(20, ShapeKindEnum.RECTANGLE, 200, 200, "f9f900");
        TextFigure stickynote = new StickyNote(boardId, position, content, style);

        // getBoardIdTest
        assertEquals(boardId, stickynote.getBoardId());
        // getPositionTest
        assertEquals(position, stickynote.getPosition());
        // getContentTest
        assertEquals(content, stickynote.getContent());
        // getStyleTest
        assertEquals(style, stickynote.getStyle());
    }
    @Test
    public void stickynote_attribute_test() {
        UUID boardId = UUID.fromString("87b10f3a-7c1f-4ba8-b3a2-a61594772ff4");
        Position position = new Position(100, 100);
        String content = "stickyNote content";
        Style style = new Style(20, ShapeKindEnum.RECTANGLE, 200, 200, "f9f900");
        TextFigure stickynote = new StickyNote(boardId, position, content, style);

        // boardIdTest
        assertEquals("87b10f3a-7c1f-4ba8-b3a2-a61594772ff4", stickynote.getBoardId().toString());
        // positionTest
        assertEquals(100, stickynote.getPosition().getX());
        assertEquals(100, stickynote.getPosition().getY());
        // contentTest
        assertEquals("stickyNote content", stickynote.getContent());
        // styleTest
        assertEquals(20, stickynote.getStyle().getFontSize());
        assertEquals(ShapeKindEnum.RECTANGLE, stickynote.getStyle().getShape());
        assertEquals(200, stickynote.getStyle().getWidth());
        assertEquals(200, stickynote.getStyle().getHeight());
        assertEquals("f9f900", stickynote.getStyle().getColor());

    }
}

package ntut.csie.islab.miro.entity.textfigure.stickynote;

import ntut.csie.islab.miro.entity.textfigure.Position;
import ntut.csie.islab.miro.entity.textfigure.Style;
import ntut.csie.islab.miro.entity.textfigure.TextFigure;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StickyNoteTest {
    @Test
    public void stickynote_get_test() {
        UUID boardId = UUID.randomUUID();
        Position position = new Position();
        String content = "stickyNote content";
        Style style = new Style();
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
}

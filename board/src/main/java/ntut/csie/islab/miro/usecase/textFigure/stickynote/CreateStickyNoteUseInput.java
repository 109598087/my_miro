package ntut.csie.islab.miro.usecase.textFigure.stickynote;

import ntut.csie.islab.miro.entity.textfigure.Position;
import ntut.csie.islab.miro.entity.textfigure.Style;

import java.util.UUID;

public class CreateStickyNoteUseInput {
    private UUID boardId;
    private Position position;
    private String content;
    private Style style;

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}

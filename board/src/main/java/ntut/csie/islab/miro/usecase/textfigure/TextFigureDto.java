package ntut.csie.islab.miro.usecase.textfigure;

import ntut.csie.islab.miro.entity.model.textFigure.Position;
import ntut.csie.islab.miro.entity.model.textFigure.Style;

import java.util.UUID;

public class TextFigureDto {
    private UUID textFigureId;
    private UUID boardId;
    private Position position;
    private String content;
    private Style style;

    public UUID getTextFigureId() {
        return textFigureId;
    }

    public void setTextFigureId(UUID textFigureId) {
        this.textFigureId = textFigureId;
    }

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

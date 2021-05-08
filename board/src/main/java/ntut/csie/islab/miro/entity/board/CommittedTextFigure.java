package ntut.csie.islab.miro.entity.board;

import java.util.UUID;

public class CommittedTextFigure {
    private UUID boardId;
    private UUID textFigureId;

    public CommittedTextFigure(UUID boardId, UUID textFigureId) {
        this.boardId = boardId;
        this.textFigureId = textFigureId;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public UUID getTextFigureId() {
        return textFigureId;
    }

    public void setTextFigureId(UUID textFigureId) {
        this.textFigureId = textFigureId;
    }
}

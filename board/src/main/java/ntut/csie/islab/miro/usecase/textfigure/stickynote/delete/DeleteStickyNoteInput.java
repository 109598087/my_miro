package ntut.csie.islab.miro.usecase.textfigure.stickynote.delete;

import java.util.UUID;

public class DeleteStickyNoteInput {
    private UUID boardId;
    private UUID textFigureId;

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

package ntut.csie.islab.miro.entity.board;

import java.util.UUID;

public class CommittedFigure {
    private UUID boardId;
    private UUID figureId;

    public CommittedFigure(UUID boardId, UUID figureId) {
        this.boardId = boardId;
        this.figureId = figureId;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public UUID getFigureId() {
        return figureId;
    }

    public void setFigureId(UUID figureId) {
        this.figureId = figureId;
    }
}

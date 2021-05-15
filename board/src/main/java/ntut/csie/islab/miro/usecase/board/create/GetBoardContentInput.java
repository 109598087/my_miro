package ntut.csie.islab.miro.usecase.board.create;

import java.util.UUID;

public class GetBoardContentInput {
    private UUID boardId;

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }
}

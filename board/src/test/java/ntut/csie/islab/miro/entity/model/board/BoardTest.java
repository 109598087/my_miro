package ntut.csie.islab.miro.entity.model.board;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    public void board_get_test() {
        UUID teamId = UUID.randomUUID();
        String boardName = "board_name";
        Board board = new Board(teamId, boardName);

        // getTeamIdTest
        assertEquals(teamId, board.getTeamId());
        // getBoardNameTest
        assertEquals(boardName, board.getBoardName());
    }

    @Test
    public void commit_a_sticknote() {
        // create board
        UUID teamId = UUID.randomUUID();
        String boardName = "boardName";
        Board board = new Board(teamId, boardName);
        // create fake figure (id)
        UUID figureId = UUID.randomUUID();
        // commit figure to its board
        board.commitTextFigure(figureId);

        assertEquals(1, board.getCommittedTextFigures().size());
        assertEquals(figureId, board.getCommittedTextFigures().get(0).getTextFigureId());
    }
}

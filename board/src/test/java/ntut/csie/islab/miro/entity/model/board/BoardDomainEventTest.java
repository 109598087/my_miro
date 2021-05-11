package ntut.csie.islab.miro.entity.model.board;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardDomainEventTest {

    @Test
    public void create_a_board_then_publishes_a_board_created_domain_event_test() {
        UUID teamId = UUID.randomUUID();
        String boardName = "board_name";
        Board board = new Board(teamId, boardName);
        assertEquals(1, board.getDomainEvents().size());
    }

    @Test
    public void commit_a_text_figure_then_publishes_a_figure_committed_domain_event_test() {
        // create board
        UUID teamId = UUID.randomUUID();
        String boardName = "boardName";
        Board board = new Board(teamId, boardName);
        // create fake figure (id)
        UUID figureId = UUID.randomUUID();
        // commit figure to its board
        board.commitTextFigure(figureId);

        assertEquals(2, board.getDomainEvents().size()); // BoardCreatedDomainEvent and FigureCommittedDomainEvent
    }
}

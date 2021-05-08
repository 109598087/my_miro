package ntut.csie.islab.miro.entity.board;

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
}

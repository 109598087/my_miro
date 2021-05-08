package ntut.csie.islab.miro.entity.board;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardDomainEventTest {

    @Test
    public void create_a_board_then_publishes_a_board_created_domain_event(){
        Board board = new Board(UUID.randomUUID(), "board_name");
        assertEquals(1, board.getDomainEvents().size());
    }
}

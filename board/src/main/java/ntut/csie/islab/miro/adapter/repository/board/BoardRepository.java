package ntut.csie.islab.miro.adapter.repository.board;

import ntut.csie.islab.miro.entity.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BoardRepository {
    private List<Board> boards;

    public BoardRepository() {
        this.boards = new ArrayList<>();
    }

    public void save(Board board) {
        this.boards.add(board);
    }

    public Optional<Board> findById(UUID teamId, UUID boardId) {
        return this.boards.stream()
                .filter(s -> teamId.equals(s.getTeamId()))
                .filter(s -> boardId.equals(s.getId()))
                .findFirst();
    }
}

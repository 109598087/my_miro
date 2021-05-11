package ntut.csie.islab.miro.entity.model.board.event;

import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.model.common.DateProvider;

import java.util.Date;
import java.util.UUID;

public class BoardCreatedDomainEvent extends DomainEvent {
    private final UUID teamId;
    private final UUID boardId;
    private final String boardName;

    public BoardCreatedDomainEvent(UUID teamId, UUID boardId, String boardName) {
        super(DateProvider.now());
        this.teamId = teamId;
        this.boardId = boardId;
        this.boardName = boardName;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public UUID getBoardId() {
        return boardId;
    }
}

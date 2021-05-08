package ntut.csie.islab.miro.entity.board;

import ntut.csie.islab.miro.entity.board.event.BoardCreatedDomainEvent;
import ntut.csie.sslab.ddd.model.AggregateRoot;

import java.util.UUID;

public class Board extends AggregateRoot<UUID> {
    private UUID teamId;
    private String boardName;

    public Board(UUID teamId, String boardName) {
        super(UUID.randomUUID());
        this.teamId = teamId;
        this.boardName = boardName;
        addDomainEvent(new BoardCreatedDomainEvent(teamId, getBoardId()));
    }

    private UUID getBoardId() {
        return this.getId();
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}

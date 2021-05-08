package ntut.csie.islab.miro.entity.board;

import ntut.csie.islab.miro.entity.board.event.BoardCreatedDomainEvent;
import ntut.csie.sslab.ddd.model.AggregateRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Board extends AggregateRoot<UUID> {
    private UUID teamId;
    private String boardName;
    private List<CommittedFigure> committedFigures;

    public Board(UUID teamId, String boardName) {
        super(UUID.randomUUID());
        this.teamId = teamId;
        this.boardName = boardName;
        this.committedFigures = new ArrayList<>();
        addDomainEvent(new BoardCreatedDomainEvent(teamId, getBoardId(), boardName));
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

    public void commitFigure(UUID figureId) {
        addFigure(figureId);
    }

    private void addFigure(UUID figureId) {
        CommittedFigure committedFigure = new CommittedFigure(getBoardId(), figureId);
        committedFigures.add(committedFigure);
    }

    public List<CommittedFigure> getCommittedFigures() {
        return committedFigures;
    }
}

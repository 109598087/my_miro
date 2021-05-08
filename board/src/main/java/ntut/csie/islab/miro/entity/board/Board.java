package ntut.csie.islab.miro.entity.board;

import ntut.csie.islab.miro.entity.board.event.BoardCreatedDomainEvent;
import ntut.csie.islab.miro.entity.board.event.TextfigureCommittedDomainEvent;
import ntut.csie.sslab.ddd.model.AggregateRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Board extends AggregateRoot<UUID> {
    private UUID teamId;
    private String boardName;
    private List<CommittedTextFigure> committedTextFigures;

    public Board(UUID teamId, String boardName) {
        super(UUID.randomUUID());
        this.teamId = teamId;
        this.boardName = boardName;
        this.committedTextFigures = new ArrayList<>();
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

    public void commitTextFigure(UUID figureId) {
        addtextFigure(figureId);
        addDomainEvent(new TextfigureCommittedDomainEvent(getBoardId(), figureId));
    }

    private void addtextFigure(UUID figureId) {
        CommittedTextFigure committedtextFigure = new CommittedTextFigure(getBoardId(), figureId);
        committedTextFigures.add(committedtextFigure);
    }

    public List<CommittedTextFigure> getCommittedTextFigures() {
        return committedTextFigures;
    }
}

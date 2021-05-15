package ntut.csie.islab.miro.usecase.eventHandler;

import ntut.csie.islab.miro.adapter.gateway.repository.springboot.board.BoardRepository;
import ntut.csie.islab.miro.entity.model.board.Board;
import ntut.csie.islab.miro.entity.model.board.event.TextfigureCommittedDomainEvent;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.event.StickyNoteCreatedDomainEvent;
import ntut.csie.sslab.ddd.model.DomainEventBus;

import java.util.Optional;

public class NotifyBoard {
    private BoardRepository boardRepository;
    private DomainEventBus domainEventBus;

    public NotifyBoard(BoardRepository boardRepository, DomainEventBus domainEventBus) {
        this.boardRepository = boardRepository;
        this.domainEventBus = domainEventBus;
    }

    public void whenTextFigureCreated(StickyNoteCreatedDomainEvent stickyNoteCreatedDomainEvent) {
        Optional<Board> board = boardRepository.findById(stickyNoteCreatedDomainEvent.getBoardId());

        if (board.isPresent()) {
            board.get().commitTextFigure(stickyNoteCreatedDomainEvent.getFigureId());
            boardRepository.save(board.get());
            domainEventBus.post(new TextfigureCommittedDomainEvent(stickyNoteCreatedDomainEvent.getBoardId(), stickyNoteCreatedDomainEvent.getFigureId()));
        } else {
            throw new RuntimeException("Board not found, board id = " + stickyNoteCreatedDomainEvent.getBoardId());
        }
    }


}

package ntut.csie.islab.miro.usecase.board;

import ntut.csie.islab.miro.adapter.repository.board.BoardRepository;
import ntut.csie.islab.miro.entity.board.Board;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

public class CreateBoardUseCase {
    private BoardRepository boardRepository;
    private DomainEventBus domainEventBus;

    public CreateBoardUseCase(BoardRepository boardRepository, DomainEventBus domainEventBus) {
        this.boardRepository = boardRepository;
        this.domainEventBus = domainEventBus;
    }

    public CreateBoardInput newInput() {
        return new CreateBoardInput();
    }

    public void execute(CreateBoardInput input, CqrsCommandPresenter output) {
        Board board = new Board(input.getTeamId(), input.getBoardName());
        this.boardRepository.save(board);
        this.domainEventBus.postAll(board);
        output.setId(board.getId().toString());
        output.setExitCode(ExitCode.SUCCESS);
        output.setMessage("Create board success");
    }
}

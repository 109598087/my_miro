package ntut.csie.islab.miro.usecase.board;

import ntut.csie.islab.miro.adapter.repository.board.BoardRepository;
import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBus;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateBoardUseCaseTest {
    public BoardRepository boardRepository;
    public DomainEventBus domainEventBus;

    @BeforeEach
    public void setUp() {
        boardRepository = new BoardRepository();
        domainEventBus = new GoogleEventBus();
    }

    @Test
    public void create_board_test() {
        CreateBoardUseCase createBoardUseCase = new CreateBoardUseCase(boardRepository, domainEventBus);
        CreateBoardInput input = createBoardUseCase.newInput();
        CqrsCommandPresenter output = CqrsCommandPresenter.newInstance();
        UUID teamId = UUID.randomUUID();
        String boardName = "boardName";
        input.setTeamId(teamId);
        input.setBoardName(boardName);
        createBoardUseCase.execute(input, output);

        assertNotNull(output.getId());

        assertEquals(ExitCode.SUCCESS,output.getExitCode());

        assertEquals(teamId, boardRepository.findById(teamId, UUID.fromString(output.getId())).get().getTeamId());

    }
}

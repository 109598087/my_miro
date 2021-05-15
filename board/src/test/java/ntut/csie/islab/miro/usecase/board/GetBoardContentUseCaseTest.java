package ntut.csie.islab.miro.usecase.board;

import ntut.csie.islab.miro.adapter.gateway.repository.springboot.board.BoardRepository;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.TextFigureRepository;
import ntut.csie.islab.miro.adapter.presenter.BoardContentViewModel;
import ntut.csie.islab.miro.adapter.presenter.GetBoardContentPresenter;
import ntut.csie.islab.miro.entity.model.board.Board;
import ntut.csie.islab.miro.usecase.board.create.CreateBoardInput;
import ntut.csie.islab.miro.usecase.board.create.CreateBoardUseCase;
import ntut.csie.islab.miro.usecase.board.create.GetBoardContentInput;
import ntut.csie.islab.miro.usecase.board.create.GetBoardContentUseCase;
import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBus;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandViewModel;
import ntut.csie.sslab.ddd.model.DomainEventBus;

//import org.junit.Test; -> //java.lang.NullPointerException: Cannot invoke "ntut.csie.islab.miro.adapter.gateway.repository.springboot.board.BoardRepository.findById(java.util.UUID)" because "this.boardRepository" is null

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBoardContentUseCaseTest {
    public BoardRepository boardRepository;
    public TextFigureRepository textFigureRepository;
    public DomainEventBus domainEventBus;

    @BeforeEach
    public void setUp() {
        boardRepository = new BoardRepository();
        textFigureRepository = new TextFigureRepository();
        domainEventBus = new GoogleEventBus();
    }

    @Test
    public void test_get_board_content_with_empty_board() {
        GetBoardContentUseCase getBoardContentUseCase = new GetBoardContentUseCase(boardRepository,  textFigureRepository,  domainEventBus); //todo: add this?
        GetBoardContentInput input = getBoardContentUseCase.newInput();
        GetBoardContentPresenter output = new GetBoardContentPresenter();

        // create a board
        CreateBoardUseCase createBoardUseCase = new CreateBoardUseCase(boardRepository, domainEventBus);
        CreateBoardInput createBoardInput = createBoardUseCase.newInput();
        CqrsCommandPresenter createBoardOutput = CqrsCommandPresenter.newInstance();
        UUID teamID = UUID.randomUUID();
        String boardName = "boardName";
        createBoardInput.setTeamId(teamID);
        createBoardInput.setBoardName(boardName);
        createBoardUseCase.execute(createBoardInput, createBoardOutput);

        // set
        input.setBoardId(UUID.fromString(createBoardOutput.getId()));

        // execute
        getBoardContentUseCase.execute(input, output);

        assertEquals(UUID.fromString(createBoardOutput.getId()), output.getBoardId());
        assertEquals(0, output.getTextFigures().size());

        // buildViewModel
        BoardContentViewModel boardContentViewModel = output.buildViewModel();

        assertEquals(UUID.fromString(createBoardOutput.getId()), boardContentViewModel.getBoardId());
        assertEquals(0, boardContentViewModel.getTextFigureDtos().size());



    }
}

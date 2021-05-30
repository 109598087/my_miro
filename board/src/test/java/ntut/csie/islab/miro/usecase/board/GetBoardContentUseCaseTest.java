package ntut.csie.islab.miro.usecase.board;

import ntut.csie.islab.miro.adapter.gateway.eventbus.google.NotifyBoardAdapter;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.board.BoardRepository;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.StickyNoteRepositoryImpl;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.stickynote.StickyNoteRepositoryPeer;
import ntut.csie.islab.miro.adapter.presenter.BoardContentViewModel;
import ntut.csie.islab.miro.adapter.presenter.GetBoardContentPresenter;
import ntut.csie.islab.miro.entity.model.textFigure.Position;
import ntut.csie.islab.miro.entity.model.textFigure.ShapeKindEnum;
import ntut.csie.islab.miro.entity.model.textFigure.Style;
import ntut.csie.islab.miro.usecase.board.create.CreateBoardInput;
import ntut.csie.islab.miro.usecase.board.create.CreateBoardUseCase;
import ntut.csie.islab.miro.usecase.board.create.GetBoardContentInput;
import ntut.csie.islab.miro.usecase.board.create.GetBoardContentUseCase;
import ntut.csie.islab.miro.usecase.eventHandler.NotifyBoard;
import ntut.csie.islab.miro.usecase.textfigure.stickynote.create.CreateStickyNoteInput;
import ntut.csie.islab.miro.usecase.textfigure.stickynote.create.CreateStickyNoteUseCase;
import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBus;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.DomainEventBus;

//import org.junit.Test; -> //java.lang.NullPointerException: Cannot invoke "ntut.csie.islab.miro.adapter.gateway.repository.springboot.board.BoardRepository.findById(java.util.UUID)" because "this.boardRepository" is null

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBoardContentUseCaseTest {
    public BoardRepository boardRepository;
    public StickyNoteRepositoryImpl stickyNoteRepositoryImpl;
    public DomainEventBus domainEventBus;
    public NotifyBoardAdapter notifyBoardAdapter; //todo: see when commit textFigure to board
    @Autowired
    private StickyNoteRepositoryPeer stickyNoteRepositoryPeer;
    @BeforeEach
    public void setUp() {
        boardRepository = new BoardRepository();
        stickyNoteRepositoryImpl = new StickyNoteRepositoryImpl(stickyNoteRepositoryPeer);
        domainEventBus = new GoogleEventBus();
        notifyBoardAdapter = new NotifyBoardAdapter(new NotifyBoard(boardRepository, domainEventBus));
        domainEventBus.register(notifyBoardAdapter); // this include : commit textFigure to board
    }

    @Test
    public void test_get_board_content_with_empty_board() {
        GetBoardContentUseCase getBoardContentUseCase = new GetBoardContentUseCase(boardRepository, stickyNoteRepositoryImpl,  domainEventBus); //todo: add this?
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

    @Test
    public void test_get_board_content_with_stickyNote_board() {
        GetBoardContentUseCase getBoardContentUseCase = new GetBoardContentUseCase(boardRepository, stickyNoteRepositoryImpl,  domainEventBus); //todo: add this?
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

        // create a stickyNote
        CreateStickyNoteUseCase createStickyNoteUseCase = new CreateStickyNoteUseCase(stickyNoteRepositoryImpl, domainEventBus);
        CreateStickyNoteInput createStickyNoteInput = createStickyNoteUseCase.newInput();
        CqrsCommandPresenter createStickyNoteOutput = CqrsCommandPresenter.newInstance();
        UUID boardId = UUID.fromString(createBoardOutput.getId()); // this is same boardId!
        System.out.println(boardId);
        Position position = new Position(100, 100);
        String content = "";
        Style style = new Style(20, ShapeKindEnum.RECTANGLE, 200, 200, "#f9f900");
        createStickyNoteInput.setBoardId(boardId);
        createStickyNoteInput.setPosition(position);
        createStickyNoteInput.setContent(content);
        createStickyNoteInput.setStyle(style);
        createStickyNoteUseCase.execute(createStickyNoteInput, createStickyNoteOutput);

        // set
        input.setBoardId(UUID.fromString(createBoardOutput.getId()));

        // execute
        getBoardContentUseCase.execute(input, output);

        assertEquals(UUID.fromString(createBoardOutput.getId()), output.getBoardId());
        assertEquals(1, output.getTextFigures().size());

        // buildViewModel
        BoardContentViewModel boardContentViewModel = output.buildViewModel();
        assertEquals(UUID.fromString(createBoardOutput.getId()), boardContentViewModel.getBoardId());
        assertEquals(1, boardContentViewModel.getTextFigureDtos().size());
    }
}

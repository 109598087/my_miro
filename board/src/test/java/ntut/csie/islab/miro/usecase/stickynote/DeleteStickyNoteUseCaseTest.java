package ntut.csie.islab.miro.usecase.stickynote;

import ntut.csie.islab.miro.adapter.gateway.eventbus.google.NotifyBoardAdapter;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.board.BoardRepository;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.TextFigureRepository;
import ntut.csie.islab.miro.entity.model.board.Board;
import ntut.csie.islab.miro.entity.model.textFigure.Position;
import ntut.csie.islab.miro.entity.model.textFigure.ShapeKindEnum;
import ntut.csie.islab.miro.entity.model.textFigure.Style;
import ntut.csie.islab.miro.usecase.board.create.CreateBoardInput;
import ntut.csie.islab.miro.usecase.board.create.CreateBoardUseCase;
import ntut.csie.islab.miro.usecase.eventHandler.NotifyBoard;
import ntut.csie.islab.miro.usecase.textfigure.stickynote.create.CreateStickyNoteInput;
import ntut.csie.islab.miro.usecase.textfigure.stickynote.create.CreateStickyNoteUseCase;
import ntut.csie.islab.miro.usecase.textfigure.stickynote.delete.DeleteStickyNoteInput;
import ntut.csie.islab.miro.usecase.textfigure.stickynote.delete.DeleteStickyNoteUseCase;
import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBus;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteStickyNoteUseCaseTest {
    private TextFigureRepository textFigureRepository;
    private BoardRepository boardRepository;
    private DomainEventBus domainEventBus;
    public NotifyBoardAdapter notifyBoardAdapter; //todo: see when commit textFigure to board

    @BeforeEach
    public void setUp(){
        textFigureRepository = new TextFigureRepository();
        boardRepository = new BoardRepository();
        domainEventBus = new GoogleEventBus();
        notifyBoardAdapter = new NotifyBoardAdapter(new NotifyBoard(boardRepository, domainEventBus));
        domainEventBus.register(notifyBoardAdapter); // this include : commit textFigure to board
    }

    @Test
    public void delete_stickyNote_in_board_test(){
        // create a board
        CreateBoardUseCase createBoardUseCase = new CreateBoardUseCase(boardRepository, domainEventBus);
        CreateBoardInput createBoardInput = createBoardUseCase.newInput();
        CqrsCommandPresenter createBoardOutput = CqrsCommandPresenter.newInstance();
        UUID teamId = UUID.randomUUID();
        String boardName = "boardName";
        createBoardInput.setTeamId(teamId);
        createBoardInput.setBoardName(boardName);
        createBoardUseCase.execute(createBoardInput, createBoardOutput);

        // create a stickyNote
        CreateStickyNoteUseCase createStickyNoteUseCase = new CreateStickyNoteUseCase(textFigureRepository, domainEventBus);
        CreateStickyNoteInput createStickyNoteInput = createStickyNoteUseCase.newInput();
        CqrsCommandPresenter createStickyNoteOutput = CqrsCommandPresenter.newInstance();
        UUID boardId = UUID.fromString(createBoardOutput.getId());
        Position position = new Position(100, 100);
        String content = "";
        Style style = new Style(20, ShapeKindEnum.RECTANGLE, 200, 200, "#f9f900");
        createStickyNoteInput.setBoardId(boardId);
        createStickyNoteInput.setPosition(position);
        createStickyNoteInput.setContent(content);
        createStickyNoteInput.setStyle(style);
        createStickyNoteUseCase.execute(createStickyNoteInput, createStickyNoteOutput);

        assertNotNull(textFigureRepository.findById(boardId, UUID.fromString(createStickyNoteOutput.getId())));

        // delete a stickyNote in board
        DeleteStickyNoteUseCase deleteStickyNoteUseCase = new DeleteStickyNoteUseCase(textFigureRepository, domainEventBus);
        DeleteStickyNoteInput input = deleteStickyNoteUseCase.newInput();
        CqrsCommandPresenter output = CqrsCommandPresenter.newInstance();
        input.setBoardId(boardId);
        input.setTextFigureId(UUID.fromString(createStickyNoteOutput.getId()));
        deleteStickyNoteUseCase.execute(input, output);

        assertNull(textFigureRepository.findById(boardId, UUID.fromString(createStickyNoteOutput.getId())).orElse(null));




    }
}

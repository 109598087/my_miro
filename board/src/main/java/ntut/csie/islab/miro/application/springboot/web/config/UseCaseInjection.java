package ntut.csie.islab.miro.application.springboot.web.config;

import ntut.csie.islab.miro.adapter.gateway.repository.springboot.board.BoardRepository;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.StickyNoteRepositoryImpl;
import ntut.csie.islab.miro.usecase.board.create.CreateBoardUseCase;
import ntut.csie.islab.miro.usecase.board.create.GetBoardContentUseCase;
import ntut.csie.islab.miro.usecase.eventHandler.NotifyBoard;
import ntut.csie.islab.miro.usecase.textfigure.stickynote.create.CreateStickyNoteUseCase;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration("EzMiroUserCaseInjection")
public class UseCaseInjection {
    private BoardRepository boardRepository;
    private StickyNoteRepositoryImpl stickyNoteRepositoryImpl;
    private DomainEventBus eventBus;


    @Bean(name = "createNotifyBoard")
    public NotifyBoard createNotifyBoard() {
        return new NotifyBoard(boardRepository, eventBus);
    }

    @Bean(name = "createBoardUseCase")
    public CreateBoardUseCase createBoardUseCase() {
        return new CreateBoardUseCase(boardRepository, eventBus);
    }

    @Bean(name = "createStickyNoteUseCase")
    public CreateStickyNoteUseCase createStickyNoteUseCase() {
        return new CreateStickyNoteUseCase(stickyNoteRepositoryImpl, eventBus);
    }

    @Bean(name = "getBoardContentUseCase")
    public GetBoardContentUseCase getBoardContentUseCase() {
        return new GetBoardContentUseCase(boardRepository, stickyNoteRepositoryImpl, eventBus);
    }

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Autowired
    public void setFigureRepository(StickyNoteRepositoryImpl stickyNoteRepositoryImpl) {
        this.stickyNoteRepositoryImpl = stickyNoteRepositoryImpl;
    }

    @Autowired
    public void setEventBus(DomainEventBus eventBus) {
        this.eventBus = eventBus;
    }


}

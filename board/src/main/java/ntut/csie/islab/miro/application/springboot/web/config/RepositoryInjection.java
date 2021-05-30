package ntut.csie.islab.miro.application.springboot.web.config;

import ntut.csie.islab.miro.adapter.gateway.repository.springboot.board.BoardRepository;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.StickyNoteRepositoryImpl;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.stickynote.StickyNoteRepositoryPeer;
import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBus;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration("EZMiroRepositoryInjection")
public class RepositoryInjection {
    private StickyNoteRepositoryPeer stickyNoteRepositoryPeer;


    @Bean(name = "boardRepository")
    public BoardRepository boardRepository() {
        return new BoardRepository();
    }

    @Bean(name = "textFigureRepository")
    public StickyNoteRepositoryImpl textFigureRepository() {
        return new StickyNoteRepositoryImpl(stickyNoteRepositoryPeer);
    }

    @Bean(name = "ezMiroEventBus")
    public DomainEventBus eventBus() {
        return new GoogleEventBus();
    }


}

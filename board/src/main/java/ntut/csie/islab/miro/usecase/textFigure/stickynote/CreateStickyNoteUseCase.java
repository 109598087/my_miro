package ntut.csie.islab.miro.usecase.textFigure.stickynote;

import ntut.csie.islab.miro.adapter.repository.textfigure.TextFigureRepository;
import ntut.csie.islab.miro.entity.textfigure.TextFigure;
import ntut.csie.islab.miro.entity.textfigure.stickynote.StickyNote;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

public class CreateStickyNoteUseCase {
    private TextFigureRepository textFigureRepository;
    private DomainEventBus domainEventBus;

    public CreateStickyNoteUseCase(TextFigureRepository textFigureRepository, DomainEventBus domainEventBus) {
        this.textFigureRepository = textFigureRepository;
        this.domainEventBus = domainEventBus;
    }

    public CreateStickyNoteUseInput newInput() {
        return new CreateStickyNoteUseInput();
    }

    public void execute(CreateStickyNoteUseInput input, CqrsCommandPresenter output) {
        TextFigure stickyNote = new StickyNote(input.getBoardId(), input.getPosition(), input.getContent(), input.getStyle());
        textFigureRepository.save(stickyNote);
        domainEventBus.postAll(stickyNote);
        output.setId(stickyNote.getId().toString());
        output.setExitCode(ExitCode.SUCCESS);
        output.setMessage("Create stickyNote success");
    }
}

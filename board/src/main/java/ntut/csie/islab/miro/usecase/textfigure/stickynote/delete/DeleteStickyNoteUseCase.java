package ntut.csie.islab.miro.usecase.textfigure.stickynote.delete;

import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.TextFigureRepository;
import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

public class DeleteStickyNoteUseCase {
    private TextFigureRepository textFigureRepository;
    private DomainEventBus domainEventBus;

    public DeleteStickyNoteUseCase(TextFigureRepository textFigureRepository, DomainEventBus domainEventBus) {
        this.textFigureRepository = textFigureRepository;
        this.domainEventBus = domainEventBus;
    }

    public DeleteStickyNoteInput newInput() {
        return new DeleteStickyNoteInput();
    }

    public void execute(DeleteStickyNoteInput input, CqrsCommandPresenter output) {
        TextFigure stickyNote = textFigureRepository.findById(input.getBoardId(), input.getTextFigureId()).get();
        textFigureRepository.delete(stickyNote);
        domainEventBus.postAll(stickyNote);
        output.setId(stickyNote.getId().toString());
        output.setExitCode(ExitCode.SUCCESS);
        output.setMessage("Delete stickyNote success");
    }
}

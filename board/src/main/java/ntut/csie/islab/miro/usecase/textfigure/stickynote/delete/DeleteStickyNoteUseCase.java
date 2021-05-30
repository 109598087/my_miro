package ntut.csie.islab.miro.usecase.textfigure.stickynote.delete;

import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.StickyNoteRepositoryImpl;
import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

public class DeleteStickyNoteUseCase {
    private StickyNoteRepositoryImpl stickyNoteRepositoryImpl;
    private DomainEventBus domainEventBus;

    public DeleteStickyNoteUseCase(StickyNoteRepositoryImpl stickyNoteRepositoryImpl, DomainEventBus domainEventBus) {
        this.stickyNoteRepositoryImpl = stickyNoteRepositoryImpl;
        this.domainEventBus = domainEventBus;
    }

    public DeleteStickyNoteInput newInput() {
        return new DeleteStickyNoteInput();
    }

    public void execute(DeleteStickyNoteInput input, CqrsCommandPresenter output) {
        TextFigure stickyNote = stickyNoteRepositoryImpl.findById(input.getTextFigureId()).get();
        stickyNoteRepositoryImpl.deleteById(stickyNote.getTextFigureId());
        domainEventBus.postAll(stickyNote);
        output.setId(stickyNote.getId().toString());
        output.setExitCode(ExitCode.SUCCESS);
        output.setMessage("Delete stickyNote success");
    }
}

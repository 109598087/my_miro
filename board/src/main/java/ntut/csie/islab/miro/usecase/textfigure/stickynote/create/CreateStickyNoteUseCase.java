package ntut.csie.islab.miro.usecase.textfigure.stickynote.create;

import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.StickyNoteRepositoryImpl;
import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.StickyNote;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.*;

public class CreateStickyNoteUseCase {

    private StickyNoteRepositoryImpl stickyNoteRepositoryImpl;
    private DomainEventBus domainEventBus;

    public CreateStickyNoteUseCase(StickyNoteRepositoryImpl stickyNoteRepositoryImpl, DomainEventBus domainEventBus) {
        this.stickyNoteRepositoryImpl = stickyNoteRepositoryImpl;
        this.domainEventBus = domainEventBus;
    }

    public CreateStickyNoteInput newInput() {
        return new CreateStickyNoteInput();
    }

    public void execute(CreateStickyNoteInput input, CqrsCommandOutput output) {
        TextFigure stickyNote = new StickyNote(input.getBoardId(), input.getPosition(), input.getContent(), input.getStyle());
        stickyNoteRepositoryImpl.save((StickyNote) stickyNote);
        domainEventBus.postAll(stickyNote);
        output.setId(stickyNote.getId().toString());
        output.setExitCode(ExitCode.SUCCESS);
        output.setMessage("Create stickyNote success");
    }
}

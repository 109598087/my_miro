package ntut.csie.islab.miro.usecase.textfigure.stickynote;

import ntut.csie.islab.miro.entity.model.textFigure.stickynote.StickyNote;
import ntut.csie.sslab.ddd.usecase.AbstractRepository;

import java.util.UUID;

public interface StickyNoteRepository extends AbstractRepository<StickyNote, UUID> {
    
}

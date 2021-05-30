package ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure;

import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.stickynote.StickyNoteData;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.stickynote.StickyNoteMapper;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.stickynote.StickyNoteRepositoryPeer;
import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.StickyNote;
import ntut.csie.islab.miro.usecase.textfigure.stickynote.StickyNoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StickyNoteRepositoryImpl implements StickyNoteRepository {
    private StickyNoteRepositoryPeer peer;

    public StickyNoteRepositoryImpl(StickyNoteRepositoryPeer peer) {
        this.peer = peer;
    }

    @Override
    public List<StickyNote> findAll() {
        List<StickyNoteData> stickyNoteDataList = new ArrayList<>();
        peer.findAll().forEach(stickyNoteDataList::add);
        return StickyNoteMapper.transformToDomain(stickyNoteDataList);
    }

    @Override
    public Optional<StickyNote> findById(UUID stickyNoteId) {
        // whenever call this , it will rebuild StickyNote again.
        return peer.findById(stickyNoteId.toString()).map(StickyNoteMapper::transformToDomain);
    }

    @Override
    public void save(StickyNote stickyNote) {
        peer.save(StickyNoteMapper.transformToData(stickyNote));
    }

    @Override
    public void deleteById(UUID stickyNoteId) {
        peer.deleteById(stickyNoteId.toString());
    }  //todo: repository test
}

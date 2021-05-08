package ntut.csie.islab.miro.adapter.repository.textfigure;

import ntut.csie.islab.miro.entity.textfigure.TextFigure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TextFigureRepository {
    private List<TextFigure> textFigures;

    public TextFigureRepository() {
        this.textFigures = new ArrayList<TextFigure>();
    }

    public void save(TextFigure textFigure) {
        textFigures.add(textFigure);
    }

    public Optional<TextFigure> findById(UUID boardId, UUID textFigureId) {
        return textFigures.stream()
                .filter(s -> boardId.equals(s.getBoardId()))
                .filter(s -> textFigureId.equals(s.getTextFigureId()))
                .findFirst();
    }
}

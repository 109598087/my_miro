package ntut.csie.islab.miro.adapter.presenter;

import ntut.csie.islab.miro.usecase.textfigure.TextFigureDto;
import ntut.csie.sslab.ddd.adapter.presenter.ViewModel;

import java.util.List;
import java.util.UUID;

public class BoardContentViewModel implements ViewModel {
    private UUID BoardId;
    private List<TextFigureDto> textFigureDtos;

    public UUID getBoardId() {
        return BoardId;
    }

    public void setBoardId(UUID boardId) {
        BoardId = boardId;
    }

    public List<TextFigureDto> getTextFigureDtos() {
        return textFigureDtos;
    }

    public void setTextFigureDtos(List<TextFigureDto> textFigureDtos) {
        this.textFigureDtos = textFigureDtos;
    }
}

package ntut.csie.islab.miro.adapter.presenter;

import ntut.csie.islab.miro.usecase.board.getcontent.GetBoardContentOutput;
import ntut.csie.islab.miro.usecase.textfigure.TextFigureDto;
import ntut.csie.sslab.ddd.adapter.presenter.Presenter;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandViewModel;
import ntut.csie.sslab.ddd.usecase.Result;

import java.util.List;
import java.util.UUID;

public class GetBoardContentPresenter extends Result implements Presenter<CqrsCommandViewModel>, GetBoardContentOutput {
    private UUID boardId;
    private List<TextFigureDto> textFigureDtos;

    @Override // todo: need add
    public CqrsCommandViewModel buildViewModel() {
        return null;
    }

    @Override
    public GetBoardContentOutput setBoardId(UUID boardId) {
        this.boardId = boardId;
        return null; // todo: ?
    }

    @Override
    public UUID getBoardId() {
        return this.boardId;
    }

    @Override
    public GetBoardContentOutput setTextFigures(List<TextFigureDto> textFigureDtos) {
        this.textFigureDtos = textFigureDtos;
        return null; // todo: ?
    }

    @Override
    public List<TextFigureDto> getTextFigures() {
        return this.textFigureDtos;
    }
}

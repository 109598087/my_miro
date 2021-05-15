package ntut.csie.islab.miro.adapter.presenter;

import ntut.csie.islab.miro.usecase.board.getcontent.GetBoardContentOutput;
import ntut.csie.islab.miro.usecase.textfigure.TextFigureDto;
import ntut.csie.sslab.ddd.adapter.presenter.Presenter;
import ntut.csie.sslab.ddd.usecase.Result;

import java.util.List;
import java.util.UUID;

public class GetBoardContentPresenter extends Result implements Presenter<BoardContentViewModel>, GetBoardContentOutput { //todo: BoardContentViewModel -> CqrsCommandViewModel
    private UUID boardId;
    private List<TextFigureDto> textFigureDtos;

    @Override
    public BoardContentViewModel buildViewModel() {
        BoardContentViewModel boardContentViewModel = new BoardContentViewModel();
        boardContentViewModel.setBoardId(this.boardId);
        boardContentViewModel.setTextFigureDtos(this.textFigureDtos);
        return boardContentViewModel;
    }

    @Override
    public GetBoardContentOutput setBoardId(UUID boardId) {
        this.boardId = boardId;
        return this; // todo: this?
    }

    @Override
    public UUID getBoardId() {
        return this.boardId;
    }

    @Override
    public GetBoardContentOutput setTextFigures(List<TextFigureDto> textFigureDtos) {
        this.textFigureDtos = textFigureDtos;
        return this; // todo: this ?
    }

    @Override
    public List<TextFigureDto> getTextFigures() {
        return this.textFigureDtos;
    }
}

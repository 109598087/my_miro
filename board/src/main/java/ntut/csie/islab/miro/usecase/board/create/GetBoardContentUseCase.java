package ntut.csie.islab.miro.usecase.board.create;

import ntut.csie.islab.miro.adapter.gateway.repository.springboot.board.BoardRepository;
import ntut.csie.islab.miro.adapter.gateway.repository.springboot.textfigure.TextFigureRepository;
import ntut.csie.islab.miro.adapter.presenter.GetBoardContentPresenter;
import ntut.csie.islab.miro.entity.model.board.Board;
import ntut.csie.islab.miro.entity.model.board.CommittedTextFigure;
import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.islab.miro.usecase.textfigure.ConvertTextFigureToDto;
import ntut.csie.islab.miro.usecase.textfigure.TextFigureDto;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;

import java.util.ArrayList;
import java.util.List;


public class GetBoardContentUseCase {
    private BoardRepository boardRepository;
    private TextFigureRepository textFigureRepository;
    private DomainEventBus domainEventBus;

    public GetBoardContentUseCase(BoardRepository boardRepository, TextFigureRepository textFigureRepository, DomainEventBus domainEventBus) {
        this.boardRepository = boardRepository;
        this.textFigureRepository = textFigureRepository;
        this.domainEventBus = domainEventBus;
    }

    public GetBoardContentInput newInput() {
        return new GetBoardContentInput();
    }

    public void execute(GetBoardContentInput input, GetBoardContentPresenter output) {
        Board board = this.boardRepository.findById(input.getBoardId()).orElse(null); // maybe add get()
        if (board == null) {
            output.setBoardId(input.getBoardId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Get board failed: board not found, board if = " + output.getBoardId());
        }
        // textFigure => committedTextFigure
        List<TextFigure> textFigures = new ArrayList<>();
        List<CommittedTextFigure> committedTextFigures = board.getCommittedTextFigures();

        for (CommittedTextFigure committedTextFigure : committedTextFigures) {
            TextFigure textFigure = this.textFigureRepository.findById(board.getId(), committedTextFigure.getTextFigureId()).get();
            textFigures.add(textFigure);
        }
        // textFigure to textFigureDto
        List<TextFigureDto> textFigureDtos = ConvertTextFigureToDto.transform(textFigures);

        output.setBoardId(board.getId());
        output.setTextFigures(textFigureDtos);
    }
}

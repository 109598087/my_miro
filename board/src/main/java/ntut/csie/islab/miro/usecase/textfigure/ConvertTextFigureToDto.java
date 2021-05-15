package ntut.csie.islab.miro.usecase.textfigure;

import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;

import java.util.ArrayList;
import java.util.List;

public class ConvertTextFigureToDto {
    public static List<TextFigureDto> transform(List<TextFigure> textFigures) {
        List<TextFigureDto> textFigureDtos = new ArrayList<>();
        for (TextFigure textFigure : textFigures) {
            TextFigureDto textFigureDto = new TextFigureDto();
            textFigureDto.setTextFigureId(textFigure.getId()); // need ?
            textFigureDto.setBoardId(textFigure.getBoardId());
            textFigureDto.setContent(textFigure.getContent());
            textFigureDto.setPosition(textFigure.getPosition());
            textFigureDto.setStyle(textFigure.getStyle());
            textFigureDtos.add(textFigureDto);
        }
        return textFigureDtos;
    }
}

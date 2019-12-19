package net.bulldozer.tourofall.question.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnARenderingModelsSet {
	private QuestionRenderingModel questionRenderingModel;
	private List<AnswerRenderingModel> answerRenderingModels;

}

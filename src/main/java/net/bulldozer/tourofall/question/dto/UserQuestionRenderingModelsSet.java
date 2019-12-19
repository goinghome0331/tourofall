package net.bulldozer.tourofall.question.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionRenderingModelsSet {
	private List<QuestionRenderingModel> questionRenderingModels;
	private boolean nextIndext;
}

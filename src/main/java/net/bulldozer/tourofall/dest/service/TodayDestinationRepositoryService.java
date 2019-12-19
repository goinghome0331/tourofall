package net.bulldozer.tourofall.dest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.dest.dto.TodayDestination;
import net.bulldozer.tourofall.dest.dto.TodayDestinationRederingModel;
import net.bulldozer.tourofall.dest.repository.TodayDestinationRepository;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;

@Service
public class TodayDestinationRepositoryService implements TodayDestinationService{
	@Autowired
	private TodayDestinationRepository todayDestinationRepository;
	
	@Autowired
	private EvaluationRepository evaluationRepository;
	
	
	@Transactional(readOnly=true)
	@Override
	public List<TodayDestinationRederingModel> getTodayDestinationRenderingModels() {
		List<TodayDestinationRederingModel> todayDestinationRederingModels = new ArrayList<TodayDestinationRederingModel>();
		
		List<TodayDestination> todayDestinations = todayDestinationRepository.findAll();
		Iterator<TodayDestination> todayDestinationIter = todayDestinations.iterator();
		
		while(todayDestinationIter.hasNext()){
			TodayDestination todayDestination = todayDestinationIter.next();
			
			double meanScore = 0;
			List<Evaluation> evaluations = evaluationRepository.findByItemId(todayDestination.getItemId());
			Iterator<Evaluation> evaluationIter = evaluations.iterator();
			
			while(evaluationIter.hasNext()){
				Evaluation evaluation =  evaluationIter.next();
				meanScore += evaluation.getScore();
			}
			
			meanScore = meanScore / evaluations.size();
			
			
			TodayDestinationRederingModel todayDestinationRederingModel = TodayDestinationRederingModel.getBuilder()
																	.title(todayDestination.getTitle())
																	.itemId(todayDestination.getItemId())
																	.address(todayDestination.getAddress())
																	.imageUrl(todayDestination.getImageUrl())
																	.meanScore(meanScore)
																	.destinationType(todayDestination.getDestinationType())
																	.build();
			
			todayDestinationRederingModels.add(todayDestinationRederingModel);
		}
		
		return todayDestinationRederingModels;
	}
	
}

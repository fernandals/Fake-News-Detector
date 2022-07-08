package br.ufrn.imd.model;

import org.apache.commons.text.similarity.CosineSimilarity;

import java.util.Map;

public class CosineSimilarityMetric extends SimilarityScore{

	private Map<String, Integer> frequencyMap1;
	private Map<String, Integer> frequencyMap2;
	
	@Override
	public double similarityScore() {
		// TO DO
		
		return 1.0;
	}

}

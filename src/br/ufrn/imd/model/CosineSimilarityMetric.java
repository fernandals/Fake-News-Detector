package br.ufrn.imd.model;

import org.apache.commons.text.similarity.CosineSimilarity;
import java.util.Map;
import java.util.HashMap;

public class CosineSimilarityMetric extends SimilarityScore{

	private Map<CharSequence, Integer> frequencyMap1;
	private Map<CharSequence, Integer> frequencyMap2;
	
	public CosineSimilarityMetric(String s1, String s2) {
		super();
		super.setStr1(s1);
		super.setStr2(s2);
		this.buildFrequencyMaps();
	}
	
	private void buildFrequencyMaps() {
		this.frequencyMap1 = this.buildFrequencyMap(this.str1);
		this.frequencyMap2 = this.buildFrequencyMap(this.str2);
	}
	
	private Map<CharSequence, Integer> buildFrequencyMap(String s){
		String[] splitted = s.split(" ");
		Map<CharSequence, Integer> frequencyMap = new HashMap<CharSequence, Integer>();
		
		for (String split : splitted) {
			frequencyMap.put(split, 1);
		}
		
		return frequencyMap;
	}

	@Override
	public double similarityScore() {
		CosineSimilarity cos = new CosineSimilarity();
		
		double score = cos.cosineSimilarity(this.frequencyMap1, this.frequencyMap2);
		
		return score;
	}

}

package br.ufrn.imd.model;

import org.apache.commons.text.similarity.LevenshteinDistance;
import java.lang.Math;

public class LevenshteinDistanceMetric extends SimilarityScore {
	
	public LevenshteinDistanceMetric( String s1, String s2 ) {
		super();
		super.setStr1(s1);
		super.setStr2(s2);
	}

	@Override
	public double similarityScore() {
		
		LevenshteinDistance ld = new LevenshteinDistance();
		
		int distance = ld.apply(str1, str2);
		int maxLen = Math.max(str1.length(), str2.length());
		double score = ( (double) distance ) / maxLen;
		
		return score;
	}
}

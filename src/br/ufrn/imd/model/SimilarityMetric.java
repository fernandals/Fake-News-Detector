package br.ufrn.imd.model;

public class SimilarityMetric {
	private String str1;
	private String str2;
	
	public SimilarityMetric() {}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}
	
	public double similarityScore() {
		
		return 1.1; 
	}
}

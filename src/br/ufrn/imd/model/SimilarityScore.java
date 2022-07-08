package br.ufrn.imd.model;

public abstract class SimilarityScore {
	protected String str1;
	protected String str2;
	
	public SimilarityScore() {}

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
	
	abstract public double similarityScore();
	
}

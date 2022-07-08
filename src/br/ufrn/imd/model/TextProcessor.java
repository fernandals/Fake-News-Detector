package br.ufrn.imd.model;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TextProcessor {
	private int nGrams;
	private String textToProcess;
	private String[] words;
	
	public TextProcessor( String text ) {
		this.textToProcess = text;
		this.nGrams = 3;
	}
	
	public void setNGrams( int n ) {
		this.nGrams = n;
	}
	
	public String getTextToProcess() {
		return this.textToProcess;
	}
	
	public void processText( ) {
		this.words = removeSymbols();
		removeNGrams(this.nGrams);
		uniqueAndLower();
		sortText();
	}
	
	public String[] removeSymbols() {
		// numbers
		this.textToProcess = this.textToProcess.replaceAll("\\d", " ");
		// accents
		this.textToProcess = Normalizer.normalize(this.textToProcess, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		// punctuation		
		this.textToProcess = this.textToProcess.replaceAll("[^a-zA-Z]+", " ");
				
		String[] wrds = this.textToProcess.split(" ");
		return wrds;
	}

	public void removeNGrams( int n ) {			
		String result = "";		
		
		for( String w : words ) {
			if( w.length() > n ) {
				result += w + " ";
			}
		}
		
		this.words = result.split(" ");
		this.textToProcess = result;
	}
	
	public void uniqueAndLower() {
		Set<String> uniqueWords = new HashSet<String>();
		String result = "";
		
		for( String w : words ) {
			if( uniqueWords.add(w) )
				result += w + " ";
		}
		
		result = result.toLowerCase();
		this.words = result.split(" ");
		this.textToProcess = result;	
	}
	
	public void sortText() {
		Arrays.sort(words);
		
		String result = "";
		for( String w : words ) {
			result += w + " ";
		}
		this.textToProcess = result;		
	}
}

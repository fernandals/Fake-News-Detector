package br.ufrn.imd.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.ufrn.imd.dao.NewsDAO;
import br.ufrn.imd.model.News;
import br.ufrn.imd.model.TextProcessor;

public class CSVHandler {
	private String filePath;
	private ArrayList< String > csvLines;
	private TextProcessor processor;
	private NewsDAO newsRepo;
	
	public CSVHandler() {
		newsRepo = new NewsDAO();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	};
	
	public void importCSV() {
		this.csvLines = new ArrayList< String >();
		File file = new File(this.filePath);
		
		try( Scanner sc = new Scanner(file) ) {
			sc.nextLine();	// discarding first line (heading)
			
			while( sc.hasNextLine() ) {	
				String line = sc.nextLine();
				String[] words = line.split(",");
				
				// check if line begins with id
				if( words[0].matches("[0-9.]+") ) {
					this.csvLines.add(line);
				} else {
					int lastIndex = this.csvLines.size() - 1;
					String lastString = this.csvLines.get(lastIndex);
					lastString += " " + line;
					this.csvLines.set(lastIndex, lastString);
				}
			}
		} catch( FileNotFoundException e ) {
			System.out.println("File not found.");
		}
	}
	
	public void readCSV() {
		for( String line : csvLines ) {
			// News fields
			  int id = 0;
			  String content = null, link = null;
			  Date timestamp = null;
			  
			  try (Scanner rowScanner = new Scanner(line)) {
			      rowScanner.useDelimiter(",");
			      
			      String element;
			      int current_col=1, 					// 1 = id | 2 = content | 3 = link | 4 = date
			    		  contentBreak=0;				// contentBreak used for news that have commas 
			      
			      while (rowScanner.hasNext()) {
			    	element = rowScanner.next();
			    	
			    	if( element.startsWith("\"") )
			    		contentBreak=1;	    	
			    	
			    	if( element.endsWith("\"") )
			    		contentBreak=0;
			    	
			    	switch(current_col) {
			    		case 1:
			    			id = Integer.parseInt(element);
			    			break;
			    		case 2:
			    			if(content==null)
			    				content = element;
			    			else
			    				content += element;
			    			break;
			    		case 3:
			    			link = element;
			    			break;
			    		case 4:
			    			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			    			timestamp = formatter.parse(element);
			    			//System.out.println(formatter.format(timestamp));
			    			break;
			    	}
			        
			    	if( contentBreak == 1 )
			    		current_col = 2;
			    	else
				    	current_col += 1;
			        
			    }
			} catch (ParseException e) {
				e.printStackTrace();
			}
			  
			createNews(id, content, link, timestamp);
		}
	}
	
	public void exportCSV() {
		
	}
	
	// create news and adds it to the newsList
	public void createNews( int id, String content, String link, Date timestamp ) {
		// processing news content
		processor = new TextProcessor(content);		
		processor.setNGrams(3);		// interface
		processor.processText();
		
		News n = new News();
		n.setId(id);
		n.setOriginalContent(content);
		n.setProcessedContent(processor.getTextToProcess());
		n.setLink(link);
		n.setTimestamp(timestamp);
		
		newsRepo.addNews(n);
	}
}

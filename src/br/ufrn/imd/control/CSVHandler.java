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
		processor = new TextProcessor();
		newsRepo = new NewsDAO();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	};
	
	// Exception handling
	/*
	public void testCSV() {
		File file = new File(this.filePath);
		Desktop desktop = Desktop.getDesktop();
		
		if( file.exists() ) {
			desktop.open(file);
			catch( ) {
				
			}
		} else {
			System.out.println("File doesn't exists.");
		}
		if(file.exists())  
			desktop.open(file);  
		catch(Exception e)  
		{  
		e.printStackTrace();  
		}
	}
	*/
	
	public void importCSV() {
		this.csvLines = new ArrayList< String >();
		File file = new File(this.filePath);
		
		try( Scanner sc = new Scanner(file) ) {
			sc.nextLine();	// discarding first line (heading)
			
			while( sc.hasNextLine() )  
				this.csvLines.add(sc.nextLine());
		} catch( FileNotFoundException e ) {
			//testCSV();
			System.out.println("File not found.");
		}
	}
	
	public void readCSV() {
		/*
		String line = csvLines.get(118);
		System.out.println(line);
		try (Scanner rowScanner = new Scanner(line)) {
	      rowScanner.useDelimiter(",");
	      
	      // News fields
	      int id = 0;
	      String content=null, link = null;
	      Date timestamp = null;
	      
	      String element;
	      int current_col=1, 		// 1 = id | 2 = content | 3 = link | 4 = date
	    		  contentBreak=0;	// contentBreak used for news that have commas 
	      while (rowScanner.hasNext()) {
	    	element = rowScanner.next();
	    	
	    	// mark the start of news content
	    	if( Character.compare(element.charAt(0), '"')==0 ) {
	    		contentBreak=1;
	    	}
	    	
	    	// keep column count in content
	    	if( contentBreak == 1 )
	    		current_col = 2;
	    	
	    	// mark the end of news content
	    	if( Character.compare(element.charAt(element.length()-1), '"')==0 ) {
	    		contentBreak=0;
	    	}
	    	
	    	switch(current_col) {
	    		case 1:
	    			System.out.println(element);
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
	    			// FIX
	    	    	//System.out.print(element + '\n');
	    			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    			timestamp = formatter.parse(element);
	    			break;
	    	}
	        current_col += 1;
	      }
			//System.out.println("TIMESTAMP = " + timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/

		
		for( String line : csvLines ) {
			try (Scanner rowScanner = new Scanner(line)) {
			      rowScanner.useDelimiter(",");
			      
			      // News fields
			      int id = 0;
			      String content=null, link = null;
			      Date timestamp = null;
			      
			      String element;
			      int current_col=1, 		// 1 = id | 2 = content | 3 = link | 4 = date
			    		  contentBreak=0;	// contentBreak used for news that have commas 
			      while (rowScanner.hasNext()) {
			    	element = rowScanner.next();
			    	
			    	// mark the start of news content
			    	if( Character.compare(element.charAt(0), '"')==0 ) {
			    		contentBreak=1;
			    		System.out.println(element);
			    	}
			    	
			    	// keep column count in content
			    	if( contentBreak == 1 )
			    		current_col = 2;
			    	
			    	// mark the end of news content
			    	if( Character.compare(element.charAt(element.length()-1), '"')==0 ) {
			    		//System.out.println(current_col + element);
			    		contentBreak=0;
			    	}
			    	
			    	switch(current_col) {
			    		case 1:
			    			System.out.println(element);
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
			    			// FIX
			    	    	//System.out.print(element + '\n');
			    			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			    			timestamp = formatter.parse(element);
			    			break;
			    	}
			        current_col += 1;
			        
			        createNews(id, content, link, timestamp);
			      }
					//System.out.println("TIMESTAMP = " + timestamp);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public void exportCSV() {
		
	}
	
	// create news and adds it to the newsList
	public void createNews( int id, String content, String link, Date timestamp ) {
		News n = new News();
		n.setId(id);
		n.setOriginalContent(content);
		n.setTimestamp((java.sql.Date) timestamp);
		// text processor.....
		newsRepo.addNews(n);
	}
	
}

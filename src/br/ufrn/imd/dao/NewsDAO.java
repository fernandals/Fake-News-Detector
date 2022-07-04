package br.ufrn.imd.dao;

import java.util.ArrayList;

import br.ufrn.imd.model.News;

public class NewsDAO {
	private ArrayList< News > newsList;
	
	public NewsDAO() {
		newsList = new ArrayList<News>();
	}
	
	public void addNews( News n ) {
		this.newsList.add(n);
	}
	
	public News findNews( int id ) {
		News found = null;
		for( News n : newsList ) {
			if( n.getId() == id ) {
				found = n;
				break;
			}
		}
		return found;
	}
	
	public void listNews() {
		System.out.println("***************************************");
		for( News n : newsList ) {
			System.out.println(n.getId() + "     " + n.getLink());
		}
		System.out.println("***************************************");
	}
}

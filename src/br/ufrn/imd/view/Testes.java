package br.ufrn.imd.view;

import br.ufrn.imd.control.CSVHandler;

public class Testes {

	public static void main(String[] args) {
		CSVHandler handler = new CSVHandler();

		handler.setFilePath("/home/fernanda/ufrn/lp2/projeto/boatos-de-whatsapp-boatosorg/boatos.csv");
		handler.importCSV();
		handler.readCSV();
	}

}

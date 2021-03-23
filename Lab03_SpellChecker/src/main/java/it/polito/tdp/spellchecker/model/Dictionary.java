package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
	
	private Map<String, String> parole;
	private List<RichWord> errate;
	
	public Dictionary() {
		parole = new TreeMap<String, String>();
		errate = new ArrayList<RichWord>();
	}
	
	public void loadDictionary(String lingua) {
		try {
			String nomeFile = "src\\main\\resources\\"+lingua+".txt";
			FileReader fr = new FileReader(nomeFile);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while((word = br.readLine()) != null) {
				parole.put(word.toLowerCase(), word.toLowerCase());
			}
			br.close();
			fr.close();
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("File non trovato");
		} 
		catch (IOException ioe) {
			System.out.println("Errore lettura file");
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		for(String w : inputTextList) {
			if(!parole.containsKey(w.toLowerCase())) {
				errate.add(new RichWord(w, false));
			}
		}
		
		return errate;
	}
	
}

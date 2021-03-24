package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	private List<String> dizionario;
	private List<RichWord> errate;
	private List<String> lingue;
	
	public Dictionary() {
		lingue = new ArrayList<String>();
		dizionario = new ArrayList<String>();
		errate = new ArrayList<RichWord>();
	}
	
	public List<String> getLingue() {
		this.lingue.add("English");
		this.lingue.add("Italian");
		return this.lingue;
	}
	
	public void loadDictionary(String lingua) {
		try {
			String nomeFile = "src/main/resources/"+lingua+".txt";
			FileReader fr = new FileReader(nomeFile);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while((word = br.readLine()) != null) {
				dizionario.add(word.toLowerCase());
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
			if(!dizionario.contains(w.toLowerCase())) {
				errate.add(new RichWord(w, false));
			}
		}
		
		return errate;
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		int c = 0;
		for(String i : inputTextList) {
			for(String d : dizionario) {
				if(d.compareTo(i.toLowerCase()) == 0)
					c++;
			}
			if(c == 0)
				errate.add(new RichWord(i, false));
			c = 0;
		}
		
		return errate;
	}
	
	public boolean dichotomicSearch(List<String> dizionario, String parolaInput) {
		int mezzo = 0;
		int min = 0;
		int max = dizionario.size() - 1;
		
		while(min <= max) {
			mezzo = (min+max)/2;
			if(dizionario.get(mezzo).compareTo(parolaInput) == 0)
				return true;
			else if(dizionario.get(mezzo).compareTo(parolaInput) < 0)
				min = mezzo + 1;
			else
				max = mezzo - 1;
		}
		return false;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		for(String i : inputTextList) {
			if(!this.dichotomicSearch(dizionario, i))
				errate.add(new RichWord(i, false));
		}
		
		return errate;
	}
	
}

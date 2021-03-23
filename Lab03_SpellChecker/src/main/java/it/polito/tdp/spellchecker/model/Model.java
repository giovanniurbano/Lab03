package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	List<String> lingue = new ArrayList<String>();
	
	public List<String> getLingue() {
		this.lingue.add("English");
		this.lingue.add("Italian");
		return this.lingue;
	}

}

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.Model;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Model model;
	private List<String> input;
	private ArrayList<RichWord> errori;
	private double initTime;
	double tempo = 0.0;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtErrori;

    @FXML
    private Label txtNumErrori;

    @FXML
    private Label txtTempo;

    @FXML
    void doClear(ActionEvent event) {
    	txtInput.setText("");
    	txtErrori.setText("");
    	txtNumErrori.setText("");
    	txtTempo.setText("");
    	errori.clear();
    }

    @FXML
    void doSpell(ActionEvent event) {
    	Dictionary d = new Dictionary();
    	d.loadDictionary(boxLingua.getValue());
    	
    	input = new ArrayList<String>();
    	errori = new ArrayList<RichWord>();
    	
    	String parole = txtInput.getText();
    	parole = parole.replaceAll("[.,?\\/#!$%\\^&\\*;:{}=\\-_'~()\\[\\]\"]", "");
    	
    	String words[] = parole.split(" ");
    	for(String w : words)
    		input.add(w);
    	
    	initTime = System.nanoTime();
    	errori.addAll(d.spellCheckText(input));
    	tempo = (System.nanoTime() - initTime)/1000000000;
    	for(RichWord e : errori)
    		txtErrori.appendText(e.toString());
    	txtNumErrori.setText("The text contains " + errori.size() + " errors");
    	txtTempo.setText("Spell check completed in " + tempo + " seconds");
    }

    @FXML
    void initialize() {
        assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumErrori != null : "fx:id=\"txtNumErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";
    }

	public void setModel(Model model) {
		this.model = model;
		this.boxLingua.getItems().addAll(this.model.getLingue());
	}
    
}


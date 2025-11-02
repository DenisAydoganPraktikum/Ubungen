package main;

import gui.BahnhofControl; // Geändert
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		// Startet den Controller, der dann Model und View erstellt
		new BahnhofControl(primaryStage); 
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}
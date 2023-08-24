package com.internshaala.javafxapp;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application {
	public static void main(String[] args)
	{
		System.out.println("Main");
		launch(args);
	}
	@Override
	public void init() throws Exception {
		System.out.println("init");//creates instance of your application OR initialise your application by calling primary.show() method
		super.init();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");//starts an application and make it visible to the user
		FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load();

		MenuBar menuBar=createMenu();

		rootNode.getChildren().add(0,menuBar);
		Scene scene = new Scene(rootNode);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Temperature Converter Tool");
		//primaryStage.setResizable(false);
		primaryStage.show();
	}
	private MenuBar createMenu(){
		//file menu
		Menu fileMenu=new Menu("File");
		MenuItem newMenuItem=new MenuItem("New");
		newMenuItem.setOnAction(event -> System.out.println("New Menu Item Clicked"));
		SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();
		MenuItem quitMenuItem=new MenuItem("Quit");
		quitMenuItem.setOnAction(event->{
			Platform.exit();
			System.exit(0);
		});
		fileMenu.getItems().addAll(newMenuItem,separatorMenuItem,quitMenuItem);
		//Help menu
		Menu help=new Menu("Help");
		MenuItem aboutApp=new MenuItem("About");
		aboutApp.setOnAction(event -> aboutApp());
		help.getItems().add(aboutApp);
		//MenuBar
		MenuBar menuBar=new MenuBar();
		menuBar.getMenus().addAll(fileMenu,help);

		return menuBar;
	}

	private void aboutApp() {
		Alert alertDialog=new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("My First Desktop Application");
		alertDialog.setHeaderText("Learning JavaFX");//subtitle
		alertDialog.setContentText("I am Just A beginner but soon i will be pro and start developing awesome games.");

		ButtonType yesBtn=new ButtonType("yes");
		ButtonType noBtn=new ButtonType("No");

		alertDialog.getButtonTypes().setAll(yesBtn,noBtn);

		Optional<ButtonType> clickBtn=alertDialog.showAndWait();//To display to the user
		if(clickBtn.isPresent() && clickBtn.get()==yesBtn)
		{
			System.out.println("YES BUTTON CLICKED");
		}else
		{
			System.out.println("NO BUTTON CLICKED");
		}
	}

	@Override
	public void stop() throws Exception {
		System.out.println("stop");//called when app is basically stopped and is about to shutdown
		super.stop();
	}
}

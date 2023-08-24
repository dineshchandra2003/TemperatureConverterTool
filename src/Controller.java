package com.internshaala.javafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public TextField userInputField;
	@FXML
	public Button convertButton;

	private static final String C_T_F_TEXT="Celsius to Fahrenheit";
	private static final String F_T_C_TEXT="Fahrenheit to Celsius";
	private boolean isC_to_F=true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBox.getItems().add(C_T_F_TEXT);
		choiceBox.getItems().add(F_T_C_TEXT);
		choiceBox.setValue(C_T_F_TEXT);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
			if(newValue.equals(C_T_F_TEXT))
			{
				isC_to_F=true;
			}else
			{
				isC_to_F=false;
			}
		});

		convertButton.setOnAction(event -> {
			convert();
		});
	}

	private void convert() {
		String input=userInputField.getText();//23->"23" 23.5->"23.5"
		Float enteredTemperature=0.0f;
		try{
			enteredTemperature=Float.parseFloat(input);
		}catch (Exception exception){
			warnUser();
			return;
		}

		Float newTemperature=0.0f;
		if(isC_to_F)
		{
			newTemperature=(enteredTemperature*9/5)+32;
		}else
		{
			newTemperature=(enteredTemperature-32)*5/9;
		}
		display(newTemperature);
	}

	private void warnUser() {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occured");
		alert.setHeaderText("Invalid Temperature Entered");
		alert.setContentText("Please Enter A Valid Temperature");
		alert.show();
	}

	private void display(Float newTemperature) {
		String unit=isC_to_F?"F":"C";
		System.out.println("The new Temperature is:"+newTemperature+unit);


		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("    RESULT    ");
		alert.setContentText("The new Temperature is:"+newTemperature+unit);
		alert.show();
	}
}

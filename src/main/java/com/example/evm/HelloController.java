package com.example.evm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField address;

    @FXML
    private TextField city;

    @FXML
    private TextField name;

    @FXML
    private TextField cnic;

    @FXML
    private TextField voteID;

    @FXML
    private Button nextBtn;

    public void nextButtonClicked(ActionEvent actionEvent) {
        DB_Manager db = new DB_Manager();
        db.addVoterInfo(address,name,cnic,city,voteID);

    }
}
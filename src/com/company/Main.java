package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Scanner;

public class Main extends Application{
 // https://imgur.com/a/QEhEX6b 4th grade
    // https://github.com/javaterminal/TerminalFX
    public static void main(String[] args) {
        boolean continueGame = true;
        System.out.println("Welcome to the Anglo Tech Day!");
        Scanner scan = new Scanner(System.in);
        while(continueGame){
            System.out.println("Please enter the characters in order without any spaces or quotes. (e.g.: 87g4fw");
            String input = scan.nextLine();

        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        /*InputStream sceneStream = com.kodedu.terminalfx.TerminalAppStarter.class.getResourceAsStream("/fxml/Terminal_Scene.fxml");*/
        Parent root = FXMLLoader.load(getClass().getResource("output.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(com.company.TerminalAppStarter.class.getResource("/styles/Styles.css").toExternalForm());

        stage.setTitle("TerminalFX");
        stage.setScene(scene);
        stage.show();
    }
}

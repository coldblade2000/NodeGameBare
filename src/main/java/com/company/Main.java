package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
 // https://imgur.com/a/QEhEX6b 4th grade
    // https://github.com/javaterminal/TerminalFX
    public static void main(String[] args) {
        launch(args);
        /*boolean continueGame = true;
        System.out.println("Welcome to the Anglo Tech Day!");
        Scanner scan = new Scanner(System.in);
        Gson gson = new Gson();
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new Node("hello", '4'));
        list.add(new Node("bye", 'g'));
        list.add(new Node("hello","goodbye", 'j'));
        list.add(new Node("hello", "iohuegr", '2'));
        list.add(new Node("hello", 'a'));

        //System.out.println(gson.toJson(list));
        while(continueGame){
            System.out.println("Please enter the characters in order without any spaces or quotes. (e.g.: 87g4fw). Alternatively, type \"quit\" without quotes to exit the application. ");
            String input = scan.nextLine();
            if(input.equalsIgnoreCase("quit")){
                System.exit(0);
                break;
            }
            launch();
        }*/
    }

    @Override
    public void start(Stage stage) throws Exception {
        /*InputStream sceneStream = com.kodedu.terminalfx.TerminalAppStarter.class.getResourceAsStream("/fxml/Terminal_Scene.fxml");*/
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/output.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(com.company.TerminalAppStarter.class.getResource("/styles/Styles.css").toExternalForm());
        scene.getStylesheets().add(Main.class.getResource("/styles/java-keywords.css").toExternalForm());
        stage.setTitle("TerminalFX");
        stage.setScene(scene);
        stage.show();
    }
}

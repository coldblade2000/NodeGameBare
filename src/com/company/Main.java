package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

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
    public void start(Stage primaryStage) throws Exception {

    }
}

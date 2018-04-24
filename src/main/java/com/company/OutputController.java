package main.java.com.company;

import main.java.com.company.config.TerminalConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class OutputController {
 //https://docs.oracle.com/javafx/2/ui_controls/tooltip.htm TOOLTIPS

    public TabPane tabPane;
    public HBox Toolbar;
    public ImageView info;
    public Button bSequence;
    public TextArea tvSequence;
    Tooltip tooltip = new Tooltip();


    @FXML
    public void initialize(){
        tvSequence.setTooltip(new Tooltip("\nPlease enter the characters in order without\nany spaces or quotes. (e.g.: 87g4fw)"));
        TerminalConfig defaultConfig = new TerminalConfig();


        TerminalBuilder terminalBuilder = new TerminalBuilder(defaultConfig);
        TerminalTab terminal = terminalBuilder.newTerminal();
//        terminal.onTerminalFxReady(() -> {
//            terminal.command("java -version\r");
//        });

        tabPane.getTabs().add(terminal);
    }

    public void setSequence(MouseEvent mouseEvent) {
        System.out.println("clICKED");
    }

    public void showTooltip(MouseEvent mouseEvent) {

    }

    public void runCode(MouseEvent mouseEvent) {

    }
}

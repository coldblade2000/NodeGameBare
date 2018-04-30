package com.company;

import com.company.config.TerminalConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputController {
 //https://docs.oracle.com/javafx/2/ui_controls/tooltip.htm TOOLTIPS

    public TabPane tabPane;
    public HBox Toolbar;
    public Button bSequence, bRun;
    public TextArea tvSequence;
    public AnchorPane anchorText;
    private CodeArea codeArea;
    private TerminalTab terminal;

    @FXML
    public void initialize(){
        //tvSequence.setTooltip(new Tooltip("\nPlease enter the characters in order without\nany spaces or quotes. (e.g.: 87g4fw)"));
        Tooltip.install(
                tvSequence,
                new Tooltip("\nPlease enter the characters in order without\nany spaces or quotes. (e.g.: 87g4fw)")
        );
        TerminalConfig defaultConfig = new TerminalConfig();

        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        // recompute the syntax highlighting 500 ms after user stops editing area
        Subscription cleanupWhenNoLongerNeedIt = codeArea.multiPlainChanges()
                // do not emit an event until 500 ms have passed since the last emission of previous stream
                .successionEnds(Duration.ofMillis(500))
                // run the following code block when previous stream emits an event
                .subscribe(ignore -> codeArea.setStyleSpans(0, computeHighlighting(codeArea.getText())));

        // when no longer need syntax highlighting and wish to clean up memory leaks
        // run: `cleanupWhenNoLongerNeedIt.unsubscribe();`
        codeArea.replaceText(0, 0, "Empty");


        TerminalBuilder terminalBuilder = new TerminalBuilder(defaultConfig);
        terminal = terminalBuilder.newTerminal();
//        terminal.onTerminalFxReady(() -> {
//            terminal.command("java -version\r");
//        });
        StackPane stackPane = new StackPane(new VirtualizedScrollPane<>(codeArea));
        AnchorPane.setBottomAnchor(stackPane, 0.0);
        AnchorPane.setRightAnchor(stackPane, 0.0);
        AnchorPane.setLeftAnchor(stackPane, 0.0);
        AnchorPane.setTopAnchor(stackPane, 0.0);
        anchorText.getChildren().add(stackPane);
        tabPane.getTabs().add(terminal);
        stackPane.toBack();
        bRun.toFront();
    }

    public void setSequence(MouseEvent mouseEvent) {
        String result = SequenceProcesser.process(tvSequence.getText());
        System.out.println(result);
        //tvSequence.clear();
        codeArea.replaceText(result);
    }

    public void runCode(MouseEvent mouseEvent) throws IOException, AWTException {
        PrintStream stream = new PrintStream("Output.java");
        File file = new File("Output.java");
        System.out.println(file.getAbsolutePath());
        stream.println(codeArea.getText());
        /*terminal.onTerminalFxReady(() -> {
            terminal.command("javac output.java");
        });*/
        ProcessBuilder builder;
        System.out.println(System.getProperty("os.name"));
        if(System.getProperty("os.name").contains("Windows")){
             builder = new ProcessBuilder(
                    "cmd.exe", "/c", "javac Output.java");
        }else{
             builder = new ProcessBuilder(
                    "/bin/bash", "-c", "javac Output.java");
        }

        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }

        terminal.onTerminalFxReady(() -> terminal.command("java Output"));
        new Robot().keyPress(KeyEvent.VK_ENTER);
        //new Robot().keyPress(KeyEvent.VK_ENTER);

        /*terminal.onTerminalFxReady(() -> {
            terminal.command("java output.class");
        });
        new Robot().keyPress(KeyEvent.VK_ENTER);*/
    }

    private static final String[] KEYWORDS = new String[] {
            "abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super",
            "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while"};
    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final String PAREN_PATTERN = "\\(|\\)";
    private static final String BRACE_PATTERN = "\\{|\\}";
    private static final String BRACKET_PATTERN = "\\[|\\]";
    private static final String SEMICOLON_PATTERN = "\\;";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );
    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
}

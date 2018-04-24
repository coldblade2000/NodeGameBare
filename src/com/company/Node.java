package com.company;

public class Node {
    private String beginning, end;
    private char identifier;
    private boolean isExpression;

    public Node(String beginning, String end, char identifier){
        this.beginning = beginning;
        this.end = end;
        this.identifier = identifier;
        isExpression = false;
    }
    public Node(String beginning, char identifier){
        this.beginning = beginning;
        this.identifier = identifier;
        isExpression = true;
    }

    public String getBeginning() {
        return beginning;
    }

    public String getEnd() {
        return end;
    }

    public boolean isExpression() {
        return isExpression;
    }

    public char getIdentifier() {
        return identifier;
    }
}

/*[
        {
        "beginning": "hello",
        "identifier": "4",
        "isExpression": true
        },
        {
        "beginning": "bye",
        "identifier": "g",
        "isExpression": true
        },
        {
        "beginning": "hello",
        "end": "goodbye",
        "identifier": "j",
        "isExpression": false
        },
        {
        "beginning": "hello",
        "end": "iohuegr",
        "identifier": "2",
        "isExpression": false
        },
        {
        "beginning": "hello",
        "identifier": "a",
        "isExpression": true
        }
        ]*/

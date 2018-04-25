
package com.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sun.misc.JavaNioAccess;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SequenceProcesser {
    public static String process(String sequence){
        Gson gson = new Gson();

        //Get input stream from nodes.json and convert it to a string.
        Scanner s = new Scanner(SequenceProcesser.class.getResourceAsStream("/nodes.json")).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        //Make ArrayList of Node from the json file
        ArrayList<Node> nodes = gson.fromJson(result, new TypeToken<ArrayList<Node>>(){}.getType());
        ArrayList<Node> nodeSequence = new ArrayList<>();

        for(char a:sequence.toCharArray()){
            for(Node b: nodes){
                if(b.getIdentifier()==a) {
                    nodeSequence.add(b);
                    break;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        Stack<String> stack = new Stack<>();

        for(Node a:nodeSequence){
            builder.append(a.getBeginning());
            if(!a.isExpression()){
                stack.push(a.getEnd());
            }
        }
        for (int i = 0; i < stack.size(); i++) {
            builder.append(stack.pop());
        }

        return builder.toString();
    }
}

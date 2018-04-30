package com.company.NODES; //6gb4lh2x
 // Set Up the program
import java.util.Scanner;
public class lineareqbetter {
    public static void main(String[] args) {
        try {
            double firstT, secondT, thirdT;
            double answer;
            String secondS;
            int xIndex, equalIndex;
            Scanner scan = new Scanner(System.in);
            boolean bContinue = true;
            System.out.println("Hello, welcome to the linear algebra processing program!\n ");
            while (bContinue) {
                bContinue = false;
                // Get the equation from the user
                System.out.println("Please input the question, or write 'quit' to exit: ");
                String question = scan.nextLine().toLowerCase().trim().replace(" ", "");
                if (question.contains("quit")) {
                    bContinue = false;
                    System.exit(0);
                }
                xIndex = question.indexOf("x");
                equalIndex = question.indexOf("=");
                // Analyze the question to get the first, second and third terms.
                firstT = Integer.valueOf(question.substring(0, xIndex));
                secondT = Integer.valueOf(question.substring(xIndex + 1, equalIndex));
                thirdT = Integer.valueOf(question.substring(equalIndex + 1));
                System.out.println("The first term is: "+firstT+"x");
                System.out.println("The second term is: "+secondT);
                System.out.println("The third term is: "+thirdT+"\n");
                if (secondT > 0) {
                    secondS = "+ ";
                } else {
                    secondS = "- ";
                }
                // Subtract the second term from the third term.
                thirdT = thirdT - secondT;
                System.out.println("Subtracted the second term from both sides. Now the equation is: " + ((int) firstT) + "x" + " = " + (int) thirdT);
                // Divide the third term by the coefficient of x.
                thirdT = thirdT / firstT;
                System.out.println("Divide the third term by the coefficient of x. Now the equation is: " + "x" + " = " + (int) thirdT);
                // Repeat the original question to the user
                System.out.println("\n \nThe question was: " + ((int) firstT) + "x " + secondS + (int) Math.abs(secondT) + " = " + (int) thirdT);
                // Tell the answer to the equation
                System.out.println("\n \nThe answer to the question is: x = " + thirdT);
                // Repeat the probam
                bContinue=true;
                System.out.println("====================================================\n \n ");

            }
        }catch (Exception e){
            System.out.println("!!! Whoops, there's been some sort of issue with your input. The program will close, but you can just run it again. !!!");
            System.exit(0);
        }
    }
}

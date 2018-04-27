import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double firstT, secondT, thirdT;
        double answer;
        String secondS;
        Scanner scan = new Scanner(System.in);

        System.out.println("Hello, welcome to the linear algebra processing program! Remember to always add a negative sign if the term is negative.\n ");

        System.out.println("Please input the coefficient of X: ");
        firstT = scan.nextDouble();

        System.out.println("Please input the second term of the equation: ");
        secondT = scan.nextDouble();

        System.out.println("Please input the third term of the equation: ");
        thirdT = scan.nextDouble();

        //answer = (thirdT+secondT)/firstT;
        if(secondT>0){
            answer = (thirdT-secondT)/firstT;
            secondS = "+ ";
        }else{
            answer = (thirdT+secondT)/firstT;
            secondS = "- ";

        }

        System.out.println("\n \nThe question is: "+ ((int) firstT)+ "x "+secondS+ (int)secondT + " = "+(int)thirdT);
        System.out.println("\n \nThe answer to the question is: x = "+answer);
    }
}

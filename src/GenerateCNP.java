mport java.io.BufferedReader;
import javax.print.DocFlavor;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class GenerateCNP {


    public static String getRandomNumberString(){
        Random rnd = new Random();
        int RandomNumber = rnd.nextInt(999);
        return String.format("%03d",  RandomNumber);
    }

    public static void main(String[] args) throws IOException {
//            int[] Digits= {199090301, 291090544, 293050452, 194031256, 622081856};
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter five numbers with 9 digits : ");
        int u = 0;
        long[] myNum = new long[5];
        while (u < 5) {
            long l=scr.nextLong();
            int a = String.valueOf(l).length();
            if (a < 9) {
                System.out.println("Please insert 9 digits number ");
                l=scr.nextLong();
            }
            myNum[u] = l;
            u++;
        }

        for (int i = 0; i <= myNum.length; i++) {
            String Random3digits;
            System.out.println("Random 3 digits number " + (Random3digits = getRandomNumberString()));
            System.out.println("Input Number " + myNum[i]);
            String Digits12 = myNum[i] + Random3digits;
            System.out.println("Number with 12 digits " + Digits12);
            long sum12digit =0;
            int o =Long.parseLong(Digits12);
            for(int i= 0; i < Digits12.length(); i++){
               sum12digit += i * o[i];
            }
              long ad =Integer.parseInt(Digits12);
              long af = ad % 11;
              String number13 = Digits12 + af;
              System.out.println("Number 13 digits" + number13);

        }
    }

}
import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String [] args) throws IOException{

    // Polynomial p = new Polynomial();
    // System.out.println(p.evaluate(3));
    double [] c1 = {1,1,1}; // coeff
    int [] c11 = {0,1,2}; // exp
    Polynomial p1 = new Polynomial(c1,c11);
    double [] c2 = {2,1};
    int [] c22 = {0,3};
    Polynomial p2 = new Polynomial(c2,c22);
    Polynomial s = p1.add(p2);
    for (int i = 0; i < s.coeff.length; i++){
        System.out.println("the exponent: " + s.exps[i]);
        System.out.println(s.coeff[i]);
    }
    System.out.println("s(0.1) = " + s.evaluate(1));

    Polynomial s1 = p1.multiply(p2);
    for (int i = 0; i < s1.coeff.length; i++){
        System.out.println(s1.coeff[i]);
    }
    System.out.println("These r exponents:");
    for (int i = 0; i < s1.coeff.length; i++){
        System.out.println(s1.exps[i]);
    }


    if(s.hasRoot(1))
    System.out.println("1 is a root of s");
    else
    System.out.println("1 is not a root of s");
    File file = new File("/Users/ramia/b07lab1/b07test1.txt");
    Polynomial p3 = new Polynomial(file);
    for(int i = 0; i < p3.coeff.length; i++){
        System.out.println(p3.coeff[i]); //coeffs
    }
    for(int i = 0; i < p3.exps.length; i++){
        System.out.println(p3.exps[i]); //exps
    }
    p3.saveToFile("/Users/ramia/b07lab1/output.txt");
    }
    }
    
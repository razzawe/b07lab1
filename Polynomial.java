import java.io.*;
import java.util.*;


public class Polynomial{
    //fields
    double[] coeff;
    int[] exps;

    //methods
    public Polynomial(){
        double[] x = {0};
        coeff = x;
    }
    public Polynomial(double[] coeff, int[] exps){
        this.coeff = coeff;
        this.exps = exps;
    }
    public Polynomial(File file) throws IOException{
        boolean firstneg = false;
        Scanner input = new Scanner(file);
        String poly = input.nextLine();
        if (poly.contains("-")){
            
            poly = poly.replace("-","+-");
        }
        if (poly.substring(0,1).equals("+")){
            firstneg=true;
            poly = poly.replaceFirst("\\+-","");
            
        }
       

        if(poly.substring(1,poly.length()).contains("+")){
            String[] poly_split = poly.split("\\+");
            double[] new_coeffs = new double[poly_split.length];
            int[] new_exps = new int[poly_split.length];


            for(int i = 0; i < poly_split.length; i++){
                String[] poly_split2 = poly_split[i].split("x");
                if(!(poly_split[i].contains("x"))){ //if no x
                    new_coeffs[i]=Double.parseDouble(poly_split[i]);
                    if (firstneg){
                        new_coeffs[i] = new_coeffs[i] * -1;
                        firstneg = false;
                    };
                    new_exps[i]=0;
                }

                else if(poly_split[i].equals("x")){
                    new_coeffs[i]=1;
                    if (firstneg){
                        new_coeffs[i] = new_coeffs[i] * -1;
                        firstneg = false;
                    };
                    new_exps[i]=1;
                }
                else{
                    if(poly_split[i].charAt(0) == 'x'){
                        new_coeffs[i] = 1;
                        if (firstneg){
                            new_coeffs[i] = new_coeffs[i] * -1;
                            firstneg = false;
                        };
                        new_exps[i] = Integer.parseInt(poly_split2[1]);

                    }
                    else if(poly_split[i].charAt(poly_split[i].length()-1)=='x'){
                        if(poly_split[i].contains("-x")){
                            new_coeffs[i] = -1;
                        }
                        else{
                            new_coeffs[i] = Double.parseDouble(poly_split2[0]);
                        }
                        if (firstneg){
                            new_coeffs[i] = new_coeffs[i] * -1;
                            firstneg = false;
                        };
                        new_exps[i] = 1;
                    }
                    else{
                        if(poly_split[i].contains("-x")){
                            new_coeffs[i] = -1;
                        }
                        else{
                            new_coeffs[i] = Double.parseDouble(poly_split2[0]);
                        }
                        if (firstneg){
                            new_coeffs[i] = new_coeffs[i] * -1;
                            firstneg = false;
                        };

                        new_exps[i] = Integer.parseInt(poly_split2[1]);
                    }
                }

            }
            this.coeff = new_coeffs;
            this.exps = new_exps;


        }
        else{ // condition w/ just 1 term long polynomial
            double[] new_coeffs = new double[1];
            int[] new_exps = new int[1];                
            boolean neg=false;
            if(poly.contains("+")){ // remove '+' that may be added for negative terms.
                poly = poly.replace("+","");
            }

            if(poly.contains("-")){ //checking if negative
                neg=true;
                poly = poly.replace("-","");
            }
            if (!poly.contains("x")){ //if no x
                new_coeffs[0]=Double.parseDouble(poly);
                if(neg){
                    new_coeffs[0] = new_coeffs[0] *(-1);
                }
                new_exps[0]=0;
            }
            if(poly.equals("x")){ //if only x
                new_coeffs[0]=1;
                if(neg){
                    new_coeffs[0] = new_coeffs[0] *(-1);
                }
                new_exps[0]=1;
            }
            
            else{ // has x and other things.

                String[] poly_split2 = poly.split("x");

                if(poly.charAt(0) == 'x'){
                    new_coeffs[0] = 1;
                    if(neg){
                        new_coeffs[0] = new_coeffs[0] *(-1);
                    }
                    new_exps[0] = Integer.parseInt(poly_split2[1]);
                }
                else if(poly.charAt(poly.length()-1) == 'x'){
                    new_coeffs[0] = Double.parseDouble(poly_split2[0]);
                    if(neg){
                        new_coeffs[0] = new_coeffs[0] *(-1);
                    }
                    new_exps[0] = 1;
                }
                else{

                    new_coeffs[0] = Double.parseDouble(poly_split2[0]);
                    if(neg){
                        new_coeffs[0] = new_coeffs[0] *(-1);
                    }
                    new_exps[0] = Integer.parseInt(poly_split2[1]);
                }
            }

            this.coeff = new_coeffs;
            this.exps = new_exps;

            }
        }

    public boolean numInArray(int num, int[] array){
        for (int i = 0; i < array.length; i++){
            if(array[i] == num){
                return true;
            }
        }
        return false;
    }

    public Polynomial add(Polynomial poly){
        Polynomial new_poly = new Polynomial();

        int poly_len = this.exps.length;
        for (int i = 0; i < poly.exps.length; i++){
            if (!(poly.numInArray(poly.exps[i],this.exps))){ // adds length of missing # exponents.
                poly_len++;
            }
        }

        int[] new_exps = new int[poly_len]; // Initializing polynomial list
 
        for (int i = 0; i < this.exps.length; i++){ //Fills new exponents w/ 'this.exps'
            new_exps[i] = this.exps[i];
        }
        for (int i = this.exps.length; i < poly_len; i++){ //Fills new exponents w/ 'poly.exps'
            for (int j = 0; j < poly.exps.length; j++){
                if(!(poly.numInArray(poly.exps[j], new_exps))){
                    new_exps[i] = poly.exps[j];
                }
            }
        }


        double[] new_coeffs = new double[poly_len]; // Initilaizing co-efficient list (assume it is sorted based on exponent #)

        for (int i = 0; i < new_exps.length; i++){ // Adding all coefficient values to new_coeffs from this.coeff array
            for (int j = 0; j < this.exps.length; j++){
                if (new_exps[i] == this.exps[j]){
                    new_coeffs[i] += this.coeff[j];
                }
            }
        }
            
        
        for (int i = 0; i < new_exps.length; i++){ // Adding all coefficient values to new_coeffs from poly.coeffs array
            for (int j = 0; j < poly.exps.length; j++){
                if (new_exps[i] == poly.exps[j]){
                    new_coeffs[i] += poly.coeff[j];
                }
            }
        }

        int temp1 = 0; // Bubble sorts the exponents
        double temp2 = 0;
        for (int i = 0; i < poly_len; i++){
            for (int j = i; j < poly_len; j++){
                if (new_exps[i] > new_exps[j]){
                    temp1 = new_exps[i];
                    temp2 = new_coeffs[i];
                    new_exps[i] = new_exps[j];
                    new_coeffs[i] = new_coeffs[j];
                    new_exps[j] = temp1;
                    new_coeffs[j] = temp2;
                }
            }
        }
        new_poly.coeff = new_coeffs;
        new_poly.exps = new_exps;

        return new_poly;
    }



    public double evaluate(double x){
        double value = 0;
       
        for (int i = 0; i < this.coeff.length; i++){
            value += this.coeff[i] * Math.pow(x,this.exps[i]);
        }
        return value;
    }


    public boolean hasRoot(double x){
        if (evaluate(x) == 0){
            return true;
        }
            
        else{
            return false;
        }
    }

    public Polynomial multiply(Polynomial poly){
        Polynomial new_poly = new Polynomial();
        int index = 0;
        int temp_len = this.exps.length * poly.exps.length;
        int new_len = 0;
        int[] temp_exps = new int[temp_len];
        double[] temp_coeffs = new double[temp_len];

        int biggest_exp = 0;

        for (int i = 0; i < this.exps.length; i++){ //unsimplified polynomial 
            for (int j = 0; j < poly.exps.length; j++){
                temp_exps[index] = this.exps[i] + poly.exps[j];
                temp_coeffs[index] = this.coeff[i] * poly.coeff[j];
                index++;
            }
        }

        for (int i = 0; i < temp_len; i++){ // Find largest exponent.
            if(biggest_exp < temp_exps[i]){
                biggest_exp = temp_exps[i];
            }
        }
        for (int i = 0; i < biggest_exp; i++){ //getting length of new product of polynomials.
            if (numInArray(i, temp_exps)){
                new_len++;
            }
        }
        new_len++;
        int[] new_exps = new int[new_len];
        double[] new_coeffs = new double[new_len];

        index = 0;
        for (int i = 0; i < temp_len; i++){ // Filling up exponent array (proper size, no dupes)
            if(!(numInArray(temp_exps[i], new_exps))){
                new_exps[index] = temp_exps[i];
                index++;
                
            }
            
        }

        for (int i = 0; i < new_len; i++){ // Filling up coefficient array.
            for (int j = 0; j < temp_exps.length; j++){
                if(new_exps[i] == temp_exps[j]){
                    new_coeffs[i] += temp_coeffs[j];
                }
            }
        }

        int temp1 = 0; // Bubble sorts the exponents
        double temp2 = 0;
        for (int i = 0; i < new_len; i++){
            for (int j = i; j < new_len; j++){
                if (new_exps[i] > new_exps[j]){
                    temp1 = new_exps[i];
                    temp2 = new_coeffs[i];
                    new_exps[i] = new_exps[j];
                    new_coeffs[i] = new_coeffs[j];
                    new_exps[j] = temp1;
                    new_coeffs[j] = temp2;
                }
            }
        }


        new_poly.coeff = new_coeffs;
        new_poly.exps = new_exps;

        return new_poly;

    }




    public void saveToFile(String filename) throws IOException{
        FileWriter writer = new FileWriter(filename);
        for(int i = 0; i < this.coeff.length; i++){
            if(this.coeff[i]==1 && this.exps[i]==1){ 
                writer.write("x"); // case where 'just x' term
                if(i<this.coeff.length-1){
                    writer.write("+");
                }
                
            }
            if(this.coeff[i]!=0 && this.exps[i]==0){
                writer.write(String.valueOf(this.coeff[i])); // no 'x' term
                writer.write("+");
            }
            if(this.coeff[i]!=0 && this.exps[i]>=1){
                writer.write(String.valueOf(this.coeff[i]));
                writer.write("x");
                if(this.exps[i]>1){
                    writer.write(String.valueOf(this.exps[i]));
                }
                if(i<this.coeff.length-1 && this.coeff[i+1]>0){
                    writer.write("+");
                }
                
            }
            
        }
        writer.close();
    }


}


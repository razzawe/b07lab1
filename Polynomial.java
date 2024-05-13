public class Polynomial{
    //fields
    double[] coeff;

    //methods
    public Polynomial(){
        double[] x = {0};
        coeff = x;
    }
    public Polynomial(double[] x){
        coeff = x;
    }


    // public Polynomial add(Polynomial poly){
    //     int length = poly.coeff.length + this.coeff.length;
    //     System.out.println("the length is: "+ length); // test
    //     double [] x = new double[length];
    //     System.out.println("Length x:" + x.length); // test
    //     for (int i = 0; i<this.coeff.length; i++){
    //         x[i] = this.coeff[i];
    //         // System.out.println(x[i]); // test
    //     }
    //     // System.out.println(x.length); // test
    //     for (int i = this.coeff.length; i<length; i++){
    //         x[i] = poly.coeff[i-(this.coeff.length)];
    //     }
    //     poly.coeff = x;
    //     for (int i = 0; i<length; i++){
    //         System.out.println(poly.coeff[i]); // test
    //     }
    //     return poly;
    // }
    public Polynomial add(Polynomial poly){
        int length = poly.coeff.length + this.coeff.length;
        System.out.println("the length is: "+ length); // test
        double [] x = new double[length];

        for (int i = 0; i<length; i++){
            if(poly.coeff.length-1 >= i && this.coeff.length-1>=i){
                x[i] = poly.coeff[i] + this.coeff[i];
            }
            else if (poly.coeff.length-1 >= i){
                x[i] = poly.coeff[i];
            }
            else if (this.coeff.length-1 >= i){
                x[i] = this.coeff[i];
            }
        }
        poly.coeff = x;
        for (int i = 0; i<length; i++){
            System.out.println(poly.coeff[i]); // test
        }
        return poly;
    }


    public double evaluate(double x){
        double value = 0;
       
        for (int i = 0; i < this.coeff.length; i++){
            value += coeff[i] * Math.pow(x,i);
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

}


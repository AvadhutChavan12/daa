package daa;
import java.math.BigInteger; 
import java.util.Scanner; 
 
class Assignment1 { 
    public static BigInteger square(BigInteger x) { 
        String xStr = x.toString(); 
        int n = xStr.length(); 
 
        if (n == 1) { 
            return x.multiply(x); 
        } 
 
        int m = n / 2; 
        BigInteger powerOfTen = BigInteger.TEN.pow(m); 
 
        BigInteger end = x.divide(powerOfTen); 
        BigInteger start = x.mod(powerOfTen); 
 
        BigInteger z0 = square(start); 
        BigInteger z1 = square(start.add(end)); 
        BigInteger z2 = square(end); 
 
        BigInteger result = z2.multiply(powerOfTen).multiply(powerOfTen) 
                          .add((z1.subtract(z2).subtract(z0)).multiply(powerOfTen)) 
                          .add(z0); 
 
        return result; 
    } 
 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
 
        System.out.print("Enter a large number to square: "); 
        BigInteger lNumber = scanner.nextBigInteger(); 
 
        BigInteger r = square(lNumber); 
        System.out.println("The square of " + lNumber + " is " + r); 
 
        scanner.close(); 
    } 
} 
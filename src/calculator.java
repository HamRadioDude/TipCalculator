import java.math.BigDecimal;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;
import java.math.RoundingMode;

public class calculator {
    public static void main(String[] args) {
        // You are going to create a tip calculator program. You will have the user enter the cost of items, and
        // keep entering items until the user enters 0.  At that point, you will display the total cost, taxes
        // (assume 2.5 percent or .025),
        // total with taxes, and recommend a 17.5% tip. Display appropriately for currency, with dollar signs and rounding
        // to two decimal places.


        // Variables, use doubles for money
        double totalInput = 0.00;
        double tempInput = 0.1; // running total and quit. 0.1 because it cannot be 0.
        double taxAmount = .025d; // hindsight. I could have just assigned these bigdecimals originally, instead of later.
        double recTipA = .175d;  //probably not the greatest name recTipA for recommended TipAmount
        double totalCost = 0.00d;
        double totalIP = 0.00d;

        Scanner scanInput = new Scanner(System.in);
        NumberFormat formatCurrency = NumberFormat.getCurrencyInstance(Locale.US);



        while (tempInput != 0) {
            System.out.println("Enter item's cost, 0 will stop the program and tally the total/tip amount: ");
            if (scanInput.hasNextDouble()) { //https://www.tutorialspoint.com/java/util/scanner_hasnextdouble.htm
                tempInput = scanInput.nextDouble();
                totalInput = tempInput + totalInput;
            }

        }
        // This is where it gets tricky. I wrote without using big decimal,
        // then when i ran the app, i saw it was off by fractions of a a cent. So
        // I changed everything and broke it.
        //*********************SK 09.16.18 - 1704 ***********************************

        BigDecimal decimaltaxAmount = new BigDecimal(Double.toString(taxAmount));
        BigDecimal decimalTotalInput  = new BigDecimal(Double.toString(totalInput));
        // **** Troubleshooting 09/16/2018 1800 hours - checked system out, two above calculate fine.
        BigDecimal decimalTotalIP = (decimalTotalInput.multiply(decimaltaxAmount)); // total amount of tax
        BigDecimal decimalTotalIP2 = (decimalTotalInput.add(decimalTotalIP));
        // add taxes to total ***09.16.18 1802 - NOPE . Breaks the code and calculates BS
        //************* 09.16.18 - 1809 ************* SK - Fixed the above calculation. Had to do with taxs number of 2.50 and addition of some random variable
        // Tax should calculate (tax*total) + total.
        // ddecimalTotalIP2 was because I needed to break up my original math
        // now convert:
//      //   totalCost = (recTipA * totalIP); to big decimal

        BigDecimal decimalrecTipA = new BigDecimal(Double.toString(recTipA)); // 17.5 percent tip amount
        BigDecimal decimaltipCalc = (decimalrecTipA.multiply(decimalTotalIP2)); // okay, multiply the tip 17.5 to the total
       // BigDecimal decimalTotalIP2 = (decimalTotalIP.add(decimaltipCalc));
        BigDecimal decimalGrand = (decimaltipCalc.add(decimalTotalIP2)); // This is a adding the input and the tip calculated




        System.out.println("If you want to tip " + recTipA + "% ,You should Tip: " + formatCurrency.format(decimaltipCalc) + " on a bill totaling: " + formatCurrency.format(totalInput) +" This totals your bill to: " + formatCurrency.format(decimalGrand));
        System.out.println("Additionally, a tax rate of " + decimaltaxAmount + "% was included in the tip estimation.");
    }

}

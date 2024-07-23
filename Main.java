import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMonthlyInvestment {
        double totalSavings;
        double averagePeriod;
        double monthlyInvest;
        double mo3;
        double mo6;
        double mo9;
        double yr1;

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter total savings you want to invest (assuming you invest first batch today): ");
        totalSavings = scan.nextInt();
        System.out.print("Enter the number of months you want to average in for: ");
        averagePeriod = scan.nextInt();
        System.out.print("How much do you want to invest monthly: ");
        monthlyInvest = scan.nextInt();

        if (totalSavings / monthlyInvest < averagePeriod) {  scan.close(); throw new InvalidMonthlyInvestment(); }

        double[][] yields = new double[4][2];

        System.out.println();
        System.out.print("Enter 3 Mo Yield: ");
        mo3 = scan.nextDouble();
        yields[0][0] = 0;
        yields[0][1] = mo3;
        System.out.print("Enter 6 Mo Yield: ");
        mo6 = scan.nextDouble();
        yields[1][0] = 1;
        yields[1][1] = mo6;
        System.out.print("Enter 9 Mo Yield: ");
        mo9 = scan.nextDouble();
        yields[2][0] = 2;
        yields[2][1] = mo9;
        System.out.print("Enter 1 Yr Yield: ");
        yr1 = scan.nextDouble();
        yields[3][0] = 3;
        yields[3][1] = yr1;

        System.out.println("------------------------------------");

        calculateInvestment(yields, monthlyInvest, averagePeriod, totalSavings);

        scan.close();
    }

    public static void calculateInvestment(double[][] yields, double monthly, double period, double totalSavings) {
        double total = monthly * period;
        int moneyMo3 = 0;
        int moneyMo6 = 0;
        int moneyMo9 = 0;
        int moneyMo12 = 0;
        int missed = 0;


        for (int i = 0; i < period; i++) {
            if (i < 3) {
                missed += 1;
            }
            else if (3 <= i  && i < 6) {
                moneyMo3 += monthly;
            } else if (6 <= i && i < 9) {
                moneyMo6 += monthly;
            } else if (9 <= i && i < 12) {
                moneyMo9 += monthly;
            } else if (12 <= i) {
                moneyMo12 += monthly;
            }
        }

        moneyMo3 = (((int) moneyMo3) / 1000) * 1000;
        moneyMo6 = (((int) moneyMo6) / 1000) * 1000;
        moneyMo9 = (((int) moneyMo9) / 1000) * 1000;
        moneyMo12 = (((int) moneyMo12) / 1000) * 1000;
        total = total - moneyMo3 - moneyMo6 - moneyMo9 - moneyMo12;

        double yield1 = moneyMo3 * (.25)*(yields[0][1] / 100);
        double yield2 = moneyMo6 * (.5)*(yields[1][1] / 100);
        double yield3 = moneyMo9 * (.75)*(yields[2][1] / 100);
        double yield4 = moneyMo12 * (1)*(yields[3][1] / 100);

        System.out.println("You should by the follwing:");
        System.out.printf("$%,d of 3 month bonds which will yield %,.2f\n", moneyMo3, yield1);
        System.out.printf("$%,d of 6 month bonds which will yield %,.2f\n", moneyMo6, yield2);
        System.out.printf("$%,d of 9 month bonds which will yield %,.2f\n", moneyMo9, yield3);
        System.out.printf("$%,d of 1 year bonds which will yield %,.2f\n", moneyMo12, yield4);

        System.out.println();

        System.out.printf("You will have $%,.2f remaining from your original desired investment amount\n", total);
        System.out.printf("You will have a total of $%,.2f remaining from savings after investing bonds\n", totalSavings - moneyMo3 - moneyMo6 - moneyMo9 - moneyMo12);
        System.out.printf("You will have a total of $%,.2f remaining after dollar cost averaging\n", totalSavings - (monthly * period));
        System.out.printf("You will NET and additional $%,.2f from these bonds\n", yield1 + yield2 + yield3 + yield4);
    }
}
public class InvalidMonthlyInvestment extends Exception {
    public InvalidMonthlyInvestment() {
        super("ERROR: Your monthly investments are too high. Please try again.");
    }
}

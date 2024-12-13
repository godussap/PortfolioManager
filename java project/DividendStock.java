public class DividendStock extends Stock {
    private double dividendsPaid;

    public DividendStock(String symbol, double pricePaidPerShare, double currentPrice, double sharesOwned, double dividendsPaid) {
        super(symbol, pricePaidPerShare, currentPrice, sharesOwned);
        this.dividendsPaid = dividendsPaid;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Dividends Paid: $%.2f", dividendsPaid);
    }

    @Override
    public String getType() {
        return "Dividend Stock";
    }
}
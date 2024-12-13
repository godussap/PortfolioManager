import java.io.Serializable;

public abstract class Asset implements Serializable {
    private String symbol;
    private double pricePaidPerShare;
    private double currentPrice;
    private double sharesOwned;

    public Asset(String symbol, double pricePaidPerShare, double currentPrice, double sharesOwned) {
        this.symbol = symbol;
        this.pricePaidPerShare = pricePaidPerShare;
        this.currentPrice = currentPrice;
        this.sharesOwned = sharesOwned;
    }

    public double calculateMarketValue() {
        return sharesOwned * currentPrice;
    }

    public double calculateNetValue() {
        return calculateMarketValue() - (sharesOwned * pricePaidPerShare);
    }

    public String getSymbol() {
        return symbol;
    }

    public double getSharesOwned() {
        return sharesOwned;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("%s: %s, Shares: %.2f, Current Price: $%.2f, Market Value: $%.2f",
                getType(), symbol, sharesOwned, currentPrice, calculateMarketValue());
    }
}
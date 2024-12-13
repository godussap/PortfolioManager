public class Stock extends Asset {
    public Stock(String symbol, double pricePaidPerShare, double currentPrice, double sharesOwned) {
        super(symbol, pricePaidPerShare, currentPrice, sharesOwned);
    }

    @Override
    public String getType() {
        return "Stock";
    }
}
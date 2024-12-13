public class MutualFund extends Asset {
    public MutualFund(String symbol, double pricePaidPerShare, double currentPrice, double sharesOwned) {
        super(symbol, pricePaidPerShare, currentPrice, sharesOwned);
    }

    @Override
    public String getType() {
        return "Mutual Fund";
    }
}
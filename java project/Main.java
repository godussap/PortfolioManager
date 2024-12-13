public class Main {
    public static void main(String[] args) {
        Stock stock = new Stock("AAPL", 150.00, 175.00, 10);
        DividendStock dividendStock = new DividendStock("TSLA", 600.00, 700.00, 5, 50.00);
        MutualFund mutualFund = new MutualFund("VFIAX", 100.00, 120.00, 20);

        System.out.println(stock);
        System.out.println(dividendStock);
        System.out.println(mutualFund);
    }
}
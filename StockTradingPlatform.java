import java.util.*;

public class StockTradingPlatform {

    static class Stock {
        String symbol;
        double price;

        Stock(String symbol, double price) {
            this.symbol = symbol;
            this.price = price;
        }

        void displayStock() {
            System.out.println(symbol + " : ₹" + price);
        }
    }

    static class Transaction {
        String type;
        String symbol;
        int quantity;
        double price;

        Transaction(String type, String symbol, int quantity, double price) {
            this.type = type;
            this.symbol = symbol;
            this.quantity = quantity;
            this.price = price;
        }

        void displayTransaction() {
            System.out.println(type + " | " + symbol + " | Qty: " + quantity + " | Price: ₹" + price);
        }
    }

    static class Portfolio {
        HashMap<String, Integer> holdings = new HashMap<>();
        ArrayList<Transaction> history = new ArrayList<>();
        double balance = 100000;

        void buyStock(Stock stock, int quantity) {

            double cost = stock.price * quantity;

            if (cost > balance) {
                System.out.println("Not enough balance!");
                return;
            }

            balance -= cost;

            holdings.put(stock.symbol,
                    holdings.getOrDefault(stock.symbol, 0) + quantity);

            history.add(new Transaction("BUY", stock.symbol, quantity, stock.price));

            System.out.println("Stock purchased successfully!");
        }

        void sellStock(Stock stock, int quantity) {

            int owned = holdings.getOrDefault(stock.symbol, 0);

            if (owned < quantity) {
                System.out.println("Not enough shares to sell!");
                return;
            }

            balance += stock.price * quantity;

            holdings.put(stock.symbol, owned - quantity);

            history.add(new Transaction("SELL", stock.symbol, quantity, stock.price));

            System.out.println("Stock sold successfully!");
        }

        void displayPortfolio() {

            System.out.println("\n--- Portfolio ---");

            for (String symbol : holdings.keySet()) {
                System.out.println(symbol + " : " + holdings.get(symbol) + " shares");
            }

            System.out.println("Balance: ₹" + balance);
        }

        void showHistory() {

            System.out.println("\n--- Transaction History ---");

            for (Transaction t : history) {
                t.displayTransaction();
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> market = new ArrayList<>();

        market.add(new Stock("TCS", 3500));
        market.add(new Stock("INFY", 1500));
        market.add(new Stock("RELIANCE", 2800));
        market.add(new Stock("HDFC", 1700));

        Portfolio portfolio = new Portfolio();

        while (true) {

            System.out.println("\n===== Stock Trading Platform =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Market Data ---");
                    for (Stock s : market) {
                        s.displayStock();
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = sc.next();

                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();

                    for (Stock s : market) {
                        if (s.symbol.equalsIgnoreCase(buySymbol)) {
                            portfolio.buyStock(s, buyQty);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = sc.next();

                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();

                    for (Stock s : market) {
                        if (s.symbol.equalsIgnoreCase(sellSymbol)) {
                            portfolio.sellStock(s, sellQty);
                        }
                    }
                    break;

                case 4:
                    portfolio.displayPortfolio();
                    break;

                case 5:
                    portfolio.showHistory();
                    break;

                case 6:
                    System.out.println("Exiting platform...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
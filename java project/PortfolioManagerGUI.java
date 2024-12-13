
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class PortfolioManagerGUI {
    private Portfolio portfolio = new Portfolio();

    public void show() {
        JFrame frame = new JFrame("Portfolio Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JTextArea portfolioDisplay = new JTextArea();
        portfolioDisplay.setEditable(false);

        JButton addButton = new JButton("Add Asset");
        JButton removeButton = new JButton("Remove Asset");
        JButton saveButton = new JButton("Save Portfolio");
        JButton loadButton = new JButton("Load Portfolio");
        JButton displayButton = new JButton("Display File Contents");

        addButton.addActionListener(e -> addAsset(portfolioDisplay));
        removeButton.addActionListener(e -> removeAsset(portfolioDisplay));
        saveButton.addActionListener(e -> savePortfolio());
        loadButton.addActionListener(e -> loadPortfolio(portfolioDisplay));
        displayButton.addActionListener(e -> displayFileContents(portfolioDisplay));

        JPanel panel = new JPanel();
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(saveButton);
        panel.add(loadButton);
       

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(portfolioDisplay), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addAsset(JTextArea portfolioDisplay) {
        String[] options = {"Stock", "Dividend Stock", "Mutual Fund"};
        String type = (String) JOptionPane.showInputDialog(null, "Select Asset Type:", "Add Asset",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (type == null) return;

        String symbol = JOptionPane.showInputDialog("Enter Symbol:");
        double pricePaid = Double.parseDouble(JOptionPane.showInputDialog("Enter Price Paid Per Share:"));
        double currentPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter Current Price Per Share:"));
        double sharesOwned = Double.parseDouble(JOptionPane.showInputDialog("Enter Number of Shares Owned:"));

        Asset asset = null;

        switch (type) {
            case "Stock":
                asset = new Stock(symbol, pricePaid, currentPrice, (int) sharesOwned);
                break;
            case "Dividend Stock":
                double dividends = Double.parseDouble(JOptionPane.showInputDialog("Enter Dividends Paid:"));
                asset = new DividendStock(symbol, pricePaid, currentPrice, (int) sharesOwned, dividends);
                break;
            case "Mutual Fund":
                asset = new MutualFund(symbol, pricePaid, currentPrice, sharesOwned);
                break;
        }

        if (asset != null) {
            portfolio.addAsset(asset);
            portfolioDisplay.setText(portfolio.getAssets().toString().replace(",", "\n").replace("[", "").replace("]", ""));
        }
    }

    private void removeAsset(JTextArea portfolioDisplay) {
        String symbol = JOptionPane.showInputDialog("Enter Symbol to Remove:");
        portfolio.removeAsset(symbol);
        portfolioDisplay.setText(portfolio.getAssets().toString().replace(",", "\n").replace("[", "").replace("]", ""));
    }

    private void savePortfolio() {
        try {
            portfolio.saveToFile("portfolio.txt");
            JOptionPane.showMessageDialog(null, "Portfolio saved successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving portfolio: " + e.getMessage());
        }
    }

    private void loadPortfolio(JTextArea portfolioDisplay) {
        try {
            portfolio.loadFromFile("portfolio.txt");
            portfolioDisplay.setText(portfolio.getAssets().toString().replace(",", "\n").replace("[", "").replace("]", ""));
            JOptionPane.showMessageDialog(null, "Portfolio loaded successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading portfolio: " + e.getMessage());
        }
    }

    private void displayFileContents(JTextArea portfolioDisplay) {

        StringBuilder fileContents = new StringBuilder();
    
        try (BufferedReader br = new BufferedReader(new FileReader("portfolio.dat"))) {
    
            String line;
    
            while ((line = br.readLine()) != null) {
               
    
                fileContents.append(line).append("\n");
    
            }
    
            portfolioDisplay.setText(fileContents.toString());
    
            JOptionPane.showMessageDialog(null, "File contents displayed successfully!");
    
        } catch (IOException e) {
    
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
    
        }
    
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PortfolioManagerGUI().show());
    }
}    
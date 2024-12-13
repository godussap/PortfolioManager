import java.io.*;
import java.util.ArrayList;

class Portfolio implements Serializable {
    private ArrayList<Asset> assets;

    public Portfolio() {
        assets = new ArrayList<>();
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
    }

    public void removeAsset(String symbol) {
        assets.removeIf(asset -> asset.getSymbol().equalsIgnoreCase(symbol));
    }

    public ArrayList<Asset> getAssets() {
        return assets;
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(assets);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            assets = (ArrayList<Asset>) ois.readObject();
        }
    }
}
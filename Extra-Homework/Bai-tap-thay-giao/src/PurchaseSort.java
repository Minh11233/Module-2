import java.util.Comparator;
public class PurchaseSort implements Comparator<Product>{
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getPurchase() - o2.getPurchase();
    }
}

package shop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class Order {
    private final String orderId;
    private final Address shippingAddress;
    private final List<Line> lines = new ArrayList<>();

    public Order(String orderId, Address shippingAddress) {
        if (orderId == null || orderId.isBlank() || shippingAddress == null) {
            throw new IllegalArgumentException("Order id and address are required");
        }
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
    }

    public String getOrderId() { return orderId; }
    public Address getShippingAddress() { return shippingAddress; }

    public void addLine(Product product, int quantity) {
        lines.add(new Line(product, quantity));
    }

    public List<Line> getLines() { return Collections.unmodifiableList(lines); }

    public long getItemsTotal(){
        long acc = 0;
        for (int i = 0; i < this.lines.size(); i++) {
            Line line = this.lines.get(i);
            acc += line.getTotal();
        }
        return acc;
    }

    
    public Receipt createReceipt(){
        return new Receipt();
    }

    public static class Line{
        private final Product product;
        private final int quantity;

        public Line(Product product, int quantity){
            if (product == null){
                throw new IllegalArgumentException("Produk tidak boleh null");
            }

            if (quantity <= 0){
                throw new IllegalArgumentException("quantity harus > 0");
            }

            this.product = product;
            this.quantity = quantity;
        }

        public Long getTotal(){
            return this.product.getFinalPrice() * this.quantity;
        }
    }

    public class Receipt{
        public String toText(){

            
            String txt = "STRUK PESANAN " + orderId + "\n";

            for (int i = 0; i < lines.size(); i++) {
                Line line = lines.get(i);
                txt +="- " + line.product.getInfo() + "\n";
            }

            txt += "TOTAL BARANG: " + getItemsTotal() + "\n";
            txt += "Kirim Ke: " + shippingAddress;

            return txt;
        }
    }
}

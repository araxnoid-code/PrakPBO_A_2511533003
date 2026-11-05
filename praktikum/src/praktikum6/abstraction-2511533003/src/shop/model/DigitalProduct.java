package shop.model;

import shop.discount.Discountable;

public class DigitalProduct extends Product implements Discountable {
    private static final int DIGITAL_DISCOUNT_PERCENT = 10;

    public DigitalProduct(String sku, String name, long price,String downloadUrl){
        super(sku, name, price);

        if (downloadUrl.isEmpty()){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String category() {
        return "Digital";
    }

    public String getDownloadUrl(){
        return "Digital";
    }

    @Override
    public int discountPercent() {
        return this.DIGITAL_DISCOUNT_PERCENT;
    }


    @Override
    public long getFinalPrice() {
        return applyDiscount(getPrice());
    }

    
}

package data;

public final class Gift implements Comparable<Gift> {
    private String productName;
    private Double price;
    private String category;

    public Gift(final String productName, final Double price,
                final String category) {
        this.setProductName(productName);
        this.setPrice(price);
        this.setCategory(category);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    @Override
    public int compareTo(final Gift o) {
        return (int) (getPrice() - o.getPrice());
    }
}

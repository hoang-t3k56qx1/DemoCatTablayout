package android.com.demotablayout.model;

public class Cat {
    private int img;
    private String name;
    private double price;
    private String discribe;

    public Cat(int img, String name, double price, String discribe) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.discribe = discribe;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "img=" + img +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discribe='" + discribe + '\'' +
                '}';
    }
}

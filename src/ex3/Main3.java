package ex3;

class Product implements Cloneable {
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return name + ": " + price;
    }
}

class Order implements Cloneable {
    Product product;

    public Order(Product product) {
        this.product = product;
    }

    public Order clone() {
        Order cloned = null;
        try {
            cloned = (Order) super.clone();
            cloned.product = product.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }

    @Override
    public String toString() {
        return "Заказ: " + product;
    }
}


public class Main3 {
    public static void main(String[] args) {

        Product product1 = new Product("Samsung S24 Ultra", 1000);
        Order originalOrder = new Order(product1);

        Order clonedOrder = originalOrder.clone();
        clonedOrder.product.name = "Iphone 15";

        System.out.println(originalOrder);
        System.out.println(clonedOrder);

    }
}
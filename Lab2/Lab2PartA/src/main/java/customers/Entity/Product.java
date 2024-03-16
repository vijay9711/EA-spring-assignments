package customers.Entity;

public class Product {
    public String name;
    public int itemNo;
    public String Category;

    public Product(String name, int itemNo, String category){
        super();
        this.itemNo = itemNo;
        this.name = name;
        this.Category = category;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


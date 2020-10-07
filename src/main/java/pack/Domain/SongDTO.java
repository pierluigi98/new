package pack.Domain;

public class SongDTO {
    private String id;
    //private String category;
    private int quantity;

    public SongDTO(String id, int quantity) {
        this.id = id;
        //this.category = category;
        this.quantity = quantity;
    }
/*
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SongDTO{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

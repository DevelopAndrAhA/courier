package kg.bish.courier.models_to_db;

import javax.persistence.*;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int price;
    private String weight;
    private String count;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adverst_id")
    private AdvertisingDB advertisingDB;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public AdvertisingDB getAdvertisingDB() {
        return advertisingDB;
    }

    public void setAdvertisingDB(AdvertisingDB advertisingDB) {
        this.advertisingDB = advertisingDB;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

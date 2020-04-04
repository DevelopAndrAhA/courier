package kg.bish.courier.models_to_db;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ALTYNBEK on 05.02.2020.
 */
@Entity
public class AdvertisingDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adverst_id")
    long id;
    private double latitude;
    private double longitude;
    private double all_price;
    private long client_id;

    @OneToMany(mappedBy = "advertisingDB", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Product> productses;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProductses() {
        return productses;
    }

    public void setProductses(List<Product> productses) {
        this.productses = productses;
    }

    public double getAll_price() {
        return all_price;
    }

    public void setAll_price(double all_price) {
        this.all_price = all_price;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }
}


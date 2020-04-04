package kg.bish.courier.models_to_db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ADV_COURIER_TB {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private long courier_ID;
        private long client_ID;
        private long adv_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourier_ID() {
        return courier_ID;
    }

    public void setCourier_ID(long courier_ID) {
        this.courier_ID = courier_ID;
    }

    public long getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(long client_ID) {
        this.client_ID = client_ID;
    }

    public long getAdv_id() {
        return adv_id;
    }

    public void setAdv_id(long adv_id) {
        this.adv_id = adv_id;
    }
}

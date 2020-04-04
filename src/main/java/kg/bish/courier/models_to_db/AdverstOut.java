package kg.bish.courier.models_to_db;

/**
 * Created by Altynbek on 30.03.2020.
 */
public class AdverstOut {
    private AdvertisingDB advertisingDB;
    private ClientDB clientDB;

    public AdvertisingDB getAdvertisingDB() {
        return advertisingDB;
    }

    public void setAdvertisingDB(AdvertisingDB advertisingDB) {
        this.advertisingDB = advertisingDB;
    }

    public ClientDB getClientDB() {
        return clientDB;
    }

    public void setClientDB(ClientDB clientDB) {
        this.clientDB = clientDB;
    }
}

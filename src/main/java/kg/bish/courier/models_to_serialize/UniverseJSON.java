package kg.bish.courier.models_to_serialize;

/**
 * Created by Altynbek on 22.02.2020.
 */
public class UniverseJSON {
    private long courierClientId;
    private String statusStr;
    private String secretKey;
    private int startId;
    private int endId;


    private int advId;
    private int clientId;

    public long getCourierClientId() {
        return courierClientId;
    }

    public void setCourierClientId(long courierClientId) {
        this.courierClientId = courierClientId;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getStartId() {
        return startId;
    }

    public void setStartId(int startId) {
        this.startId = startId;
    }

    public int getEndId() {
        return endId;
    }

    public void setEndId(int endId) {
        this.endId = endId;
    }

    public int getAdvId() {
        return advId;
    }

    public void setAdvId(int advId) {
        this.advId = advId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

}

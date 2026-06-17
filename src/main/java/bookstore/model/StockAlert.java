package bookstore.model;

public class StockAlert {

    private String alertMessage;

    public StockAlert(String alertMessage) {

        this.alertMessage = alertMessage;
    }

    public void sendAlert() {

        System.out.println(alertMessage);
    }
}

package model;

public class PurchaseOrder {
    private String purchaseOrderID;
    private String orderDate;
    private double totalAmount;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String purchaseOrderID, String orderDate, double totalAmount) {
        this.purchaseOrderID = purchaseOrderID;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
    public String getPurchaseOrderID() {
        return purchaseOrderID;
    }
    public void setPurchaseOrderID(String purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
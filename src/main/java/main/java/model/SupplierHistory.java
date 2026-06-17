package model;
import java.util.ArrayList;
import java.util.List;

public class SupplierHistory {
    //Encapsulation
    private String historyID;
    private List<PurchaseOrder> orderList;
  
    public SupplierHistory() {
        this.orderList = new ArrayList<>();
    }
    public SupplierHistory(String historyID, List<PurchaseOrder> orderList) {
        this.historyID = historyID;
        this.orderList = orderList;
    }
    public void viewHistory() {
        System.out.println("Viewing Supplier History...");
    }
    public void filterHistory(String criteria) {
        System.out.println("Filtering history by: " + criteria);
    }
    // Getter & Setter
    public String getHistoryID() {
        return historyID;
    }
    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }
    public List<PurchaseOrder> getOrderList() {
        return orderList;
    }
    public void setOrderList(List<PurchaseOrder> orderList) {
        this.orderList = orderList;
    }
}


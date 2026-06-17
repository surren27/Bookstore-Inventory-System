package controller;
import model.Report;
import model.Supplier;

public class SupplierController {
    private String controllerID;
    public SupplierController() {
    }

    public SupplierController(String controllerID) {
        this.controllerID = controllerID;
    }

    public boolean validateSupplier(Supplier supplier) {
        if (supplier.getSupplierID() == null || supplier.getSupplierID().isEmpty()) {
            return false;
        }
        if (supplier.getCompanyName() == null || supplier.getCompanyName().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateOrder() {
        System.out.println("Validating Purchase Order...");
        return true;
    }

    public void manageOrder() {
        System.out.println("Managing Purchase Order...");
    }

    public Report generateReport() {
        Report report = new Report();
        report.setReportID("R001");
        report.setReportType("Supplier Report");
        return report;
    }

    public String getControllerID() {
        return controllerID;
    }

    public void setControllerID(String controllerID) {
        this.controllerID = controllerID;
    }
}
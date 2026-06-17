package model;

public class Supplier {
    private String supplierID;
    private String companyName;
    private String contactNo;
    private String email;
    private String address;

    public Supplier() {
    }
    public Supplier(String supplierID, String companyName,
                    String contactNo, String email,
                    String address) {

        this.supplierID = supplierID;
        this.companyName = companyName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
    }

    public String getSupplierID() {
        return supplierID;
    }
    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

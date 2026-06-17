package model;
import java.util.Date;
import interfaces.Reportable;

public class Report implements Reportable {
    //Encapsulation
    private String reportID;
    private String reportType;
    private Date dateGenerated;
    // Default Constructor
    public Report() {
    }

    // Parameterized Constructor
    public Report(String reportID, String reportType, Date dateGenerated) {
        this.reportID = reportID;
        this.reportType = reportType;
        this.dateGenerated = dateGenerated;
    }
    // compileReport()
    public void compileReport() {
        System.out.println("Compiling report...");
    }
    // displayReport()
    @Override
    public void displayReport() {
        System.out.println("Displaying report...");
    }
    // downloadReport()
    @Override
    public void downloadReport() {
        System.out.println("Downloading report...");
    }
    //Getter & Setter
    public String getReportID() {
        return reportID;
    }
    public void setReportID(String reportID) {
        this.reportID = reportID;
    }
    public String getReportType() {
        return reportType;
    }
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    public Date getDateGenerated() {
        return dateGenerated;
    }
    public void setDateGenerated(Date dateGenerated) {
        this.dateGenerated = dateGenerated;
    }
}
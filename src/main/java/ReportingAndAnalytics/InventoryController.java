
package ReportingAndAnalytics;



  import java.util.List;

/**
 * InventoryController.java
 * Implements "Generate Inventory Report" use case (Sequence Diagram 3).
 * Actors: Manager & Staff
 */
public class InventoryController {
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * 3. getInventoryData(filter)
     * Returns the inventory report (list of [Book, Low/Normal label]),
     * or null if data missing (9a), to be handled by GUI as
     * "Inventory data not available".
     */
    public List<Object[]> getInventoryData(String filter) {
        try {
            return inventoryService.getInventoryReport(filter); // 4-8
        } catch (Exception e) {
            // 9b. System error -> retry option provided (handled by GUI)
            return null;
        }
    }
}  

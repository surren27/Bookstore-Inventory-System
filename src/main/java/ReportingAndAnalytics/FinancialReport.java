
package ReportingAndAnalytics;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * FinancialReport.java
 * GUI for "Generate Financial Report" use case (Sequence Diagram 6).
 * Actor: Manager
 *
 * Flow:
 * 2. (Optional) Select time period
 * 3-9. getFinancialData(period)
 * 10. Financial Report -> 11. Display Financial Report
 * 6a. Cost data missing -> Calculate Revenue Only
 * 10a. Calculation error -> "Calculation failed"
 */
public class FinancialReport extends JFrame {
    private FinancialService financialService;

    private JTextField fromField;
    private JTextField toField;
    private JLabel resultLabel;

    public FinancialReport(FinancialService financialService) {
        this.financialService = financialService;

        setTitle("Generate Financial Report");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 2. (Optional) Select time period
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("From (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        fromField = new JTextField(12);
        add(fromField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("To (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        toField = new JTextField(12);
        add(toField, gbc);
        
        LocalDate now = LocalDate.now();

fromField.setText(
now.withDayOfMonth(1).toString()
);

toField.setText(
now.toString()
);


        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton generateBtn = new JButton("Generate Financial Report");
        add(generateBtn, gbc);

        gbc.gridy = 3;
        resultLabel = new JLabel(" ");
        add(resultLabel, gbc);

        // 1. Select Financial Report -> 3. getFinancialData(period)
        generateBtn.addActionListener(e -> {
            String fromText = fromField.getText().trim();
            String toText = toField.getText().trim();

            LocalDate from = null;
            LocalDate to = null;

            try {
                if (!fromText.isEmpty()) from = LocalDate.parse(fromText);
                if (!toText.isEmpty()) to = LocalDate.parse(toText);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid date format. Please use YYYY-MM-DD.",
                        "Invalid Date Format", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3-9. getFinancialData(period)
            FinancialService.FinancialResult result = financialService.getFinancialData(from, to);

            if (result == null) {
                // 10a. "Calculation failed"
                JOptionPane.showMessageDialog(this, "Calculation failed",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 11. Display Financial Report
            StringBuilder sb = new StringBuilder("<html>");
            sb.append("Period: ").append(result.from).append(" to ").append(result.to).append("<br>");
            sb.append(String.format("Total Revenue: %.2f<br>", result.revenue));

            if (result.costMissing) {
                // 6a. Calculate Revenue Only
                sb.append("Cost data unavailable - Revenue Only calculated<br>");
            } else {
                sb.append(String.format("Total Cost: %.2f<br>", result.cost));
                sb.append(String.format("Profit: %.2f<br>", result.profit));
            }
            sb.append("</html>");

            resultLabel.setText(sb.toString());
        });
    }
}   


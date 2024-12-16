package com.mdpf.cliorderwinmdpf;

import java.util.Date;
import java.util.regex.Pattern;

public class Order {

    private Date dateTime;
    private boolean takeaway;
    private int tableNumber;
    private String fullCustomerName;
    private String email;
    private String customerComments;
    private double grandTotal;
    private String paymentMethod;
    private int tipping; // 0 for none, otherwise 5, 10, 15, or 20
    private boolean requestInvoice;

    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Order(Date dateTime, boolean takeaway, int tableNumber, String fullCustomerName,
            String email, String customerComments, double grandTotal,
            String paymentMethod, int tipping, boolean requestInvoice) {
        this.dateTime = dateTime;
        this.takeaway = takeaway;
        this.tableNumber = tableNumber;
        this.fullCustomerName = fullCustomerName;
        setEmail(email); // Call to setter for validation
        this.customerComments = customerComments;
        this.grandTotal = grandTotal;
        this.paymentMethod = paymentMethod;
        this.tipping = tipping;
        this.requestInvoice = requestInvoice;
    }

    public void setEmail(String email) {
        if (email.isEmpty() || EMAIL_PATTERN.matcher(email).matches()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    // Getters for accessing order data
    public Date getDateTime() {
        return dateTime;
    }

    public boolean isTakeaway() {
        return takeaway;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getFullCustomerName() {
        return fullCustomerName;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getTipping() {
        return tipping;
    }

    public boolean isRequestInvoice() {
        return requestInvoice;
    }

    public void OrderDetails() {
        System.out.println(
                "DateTime: " + dateTime + "\n"
                + "Takeaway: " + (takeaway ? "Takeaway" : "Restaurant") + "\n"
                + "TableNumber: " + tableNumber + "\n"
                + "CustomerName: " + fullCustomerName + "\n"
                + "Email: " + email  + "\n"
                + "Comments: " + customerComments + "\n"
                + "GrandTotal: " + grandTotal + "\n"
                + "PaymentMethod: " + paymentMethod + "\n"
                + "Tipping: " + tipping + "%\n"
                + "RequestInvoice: " + (requestInvoice ? "Yes" : "No") + "\n"
                + '}'
        );
    }
}

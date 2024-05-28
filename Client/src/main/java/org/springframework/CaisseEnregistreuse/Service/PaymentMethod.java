package org.springframework.CaisseEnregistreuse.Service;

public class PaymentMethod {

        private String paymentMethod;
        private double quantity;

        public PaymentMethod() {
        }

        public PaymentMethod(String paymentMethod, double quantity) {
            this.paymentMethod = paymentMethod;
            this.quantity = quantity;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }
}

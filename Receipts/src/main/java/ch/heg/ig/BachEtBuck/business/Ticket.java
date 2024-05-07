package ch.heg.ig.BachEtBuck.business;

import ch.heg.ig.BachEtBuck.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

	@Getter
	@Setter
	@Column(name = "receipt_number")
	@NotNull
	private Integer receiptNumber;

	@Getter
	@Setter
	@Column(name = "purchase_date")
	@NotNull
	private String purchaseDate;

	@Getter
	@Setter
	@Column(name = "items_purchased")
	@NotBlank
	private String itemsPurchased;

	@Getter
	@Setter
	@Column(name = "quantity")
	@NotBlank
	private Integer quantity;

	@Getter
	@Setter
	@Column(name = "price_per_item")
	@NotBlank
	private Double pricePerItem;

	@Getter
	@Setter
	@Column(name = "total_amount")
	@NotNull
	private BigDecimal totalAmount;

	@Getter
	@Setter
	@Column(name = "payment_method")
	@NotBlank
	private String paymentMethod;

	@Getter
	@Setter
	@Column(name = "store_location")
	@NotBlank
	private String storeLocation;

	@Getter
	@Setter
	@Column(name = "cashier_name")
	@NotBlank
	private String cashierName;

	@Getter
	@Setter
	@Column(name = "card_number")
	@NotBlank
	private String cardNumber;

	@Override
	public String toString() {
		return "Ticket{" + "receiptNumber=" + receiptNumber + ", purchaseDate=" + purchaseDate + ", totalAmount="
				+ totalAmount + ", itemsPurchased='" + itemsPurchased + '\'' + ", paymentMethod='" + paymentMethod
				+ '\'' + ", storeLocation='" + storeLocation + '\'' + ", cashierName='" + cashierName + '\''
				+ ", cardNumber='" + cardNumber + '\'' + '}';
	}

}

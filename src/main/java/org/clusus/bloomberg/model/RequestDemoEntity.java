package org.clusus.bloomberg.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@Table(name = "demo")
@ToString
public class RequestDemoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	@CreatedDate
	private Date createdDate;

	@NotNull
	@Column(nullable = false)
	private double amount;

	@Size(max = 3)
	@NotBlank
	@Column(nullable = false)
	private String fromCurrency;

	@Size(max = 3)
	@NotEmpty
	@Column(nullable = false)
	private String toCurrency;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}
}

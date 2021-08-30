package org.clusus.bloomberg.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDemoEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "CHAR(20)")
	private UUID id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	@CreatedDate
	private Date createdDate;

	@NotEmpty
	@Column(nullable = false)
	private double amount;

	@Max(3)
	@NotEmpty
	@Column(nullable = false)
	private String fromCurrency;

	@Max(3)
	@NotEmpty
	@Column(nullable = false)
	private String toCurrency;

}

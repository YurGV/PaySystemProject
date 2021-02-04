package by.grodno.pvt.site.webappsample.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;



@Data
public class CardDTO {

	private Integer id;

	@Length(min = 3, max = 20)
	private String cardName;

	@Length(min = 12, max = 12)
	private String cardNumber;

	@PositiveOrZero
	private Double balance;

	@Min(2021)
	private Integer valid;

	@Min(99)
	@Max(1000)
	private Integer cvv;

	private Boolean lock;

}




package by.grodno.pvt.site.webappsample.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class CardDTO {

	private Integer id;

	@Length(min = 3, max = 20)
	private String cardName;

	private Double balance;
	private Integer valid;
	private Boolean lock;

}




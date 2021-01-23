package by.grodno.pvt.site.webappsample.dto;

import lombok.Data;


@Data
public class CardDTO {

	private Integer id;
	private String cardName;
	private Double balance;
	private Integer valid;
	private Boolean lock;

	public Integer getId() {
		return id;
	}

	public String getCardName() {
		return cardName;
	}

	public Double getBalance() {
		return balance;
	}

	public Integer getValid() {
		return valid;
	}

	public Boolean getLock() {
		return lock;
	}
}




package by.grodno.pvt.site.webappsample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_trans")

public class CardTransactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private double value;

	private Date transactionDate;

}
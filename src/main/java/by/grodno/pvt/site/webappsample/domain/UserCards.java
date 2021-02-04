package by.grodno.pvt.site.webappsample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "user_cards")

public class UserCards {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String cardName;
	private String cardNumber;
	private Double balance;
	private Integer valid;
	private Integer cvv;

	@Column(name = "isLock")
	private Boolean lock;

	@OneToMany(mappedBy = "userCards")
	private List<Transactions> transactions;

}

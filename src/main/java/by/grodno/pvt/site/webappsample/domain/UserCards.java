package by.grodno.pvt.site.webappsample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
	private Double balance;
	private Integer valid;

	@Column(name = "isLock")
	private Boolean lock;

	@OneToMany(mappedBy = "userCards")
	private List<Transactions> transactions;

//	public UserCards(Integer id, String cardName, double balance, int valid, boolean lock, Transactions transactions) {
//	}

//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User ownerUser;

}

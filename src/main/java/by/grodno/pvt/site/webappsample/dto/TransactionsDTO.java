package by.grodno.pvt.site.webappsample.dto;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
public class TransactionsDTO {


    private Integer id;

    private Date transactionDate;


    private double value;

    @Length(min = 3, max = 20)
    private String procedure;

    private String cardName;
}

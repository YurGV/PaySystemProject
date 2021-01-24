package by.grodno.pvt.site.webappsample.dto;


import lombok.Data;
import java.util.Date;

@Data
public class TransactionsDTO {


    private Integer id;

    private Date transactionDate;

    private double value;

    private String procedure;
}

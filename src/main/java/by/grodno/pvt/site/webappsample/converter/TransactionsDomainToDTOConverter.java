package by.grodno.pvt.site.webappsample.converter;


import by.grodno.pvt.site.webappsample.domain.Transactions;
import by.grodno.pvt.site.webappsample.dto.TransactionsDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TransactionsDomainToDTOConverter implements Converter<Transactions, TransactionsDTO> {

    @Override
    public TransactionsDTO convert(Transactions source) {

        TransactionsDTO t = new TransactionsDTO();

        t.setId(source.getId());
        t.setProcedure(source.getProcedure());
        t.setTransactionDate(source.getTransactionDate());
        t.setValue(source.getValue());
        t.setCardName(source.getUserCards().getCardName());

        return t;
    }
}

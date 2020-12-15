package edu.northeastern.cs5500.delivery.repository;

import edu.northeastern.cs5500.delivery.model.CreditCard;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.bson.types.ObjectId;

public class InMemoryCreditCardRepository extends InMemoryRepository<CreditCard>
        implements GenericCreditCardRepository {

    @Inject
    public InMemoryCreditCardRepository() {
        super();
    }

    @Override
    public CreditCard updateByUserName(CreditCard item, String userName) {
        Collection<CreditCard> databaseItems = this.getAll();
        for (CreditCard databaseItem : databaseItems) {
            if (databaseItem.getUserName().equals(userName)) {
                ObjectId id = databaseItem.getId();
                collection.replace(id, item);
            }
        }
        return item;
    }

    @Override
    public void deleteByCardNumber(Long cardNum, String userName) {
        Collection<CreditCard> databaseItems = this.getAllByUserName(userName);
        for (CreditCard databaseItem : databaseItems) {
            if (databaseItem.getCardNumber().equals(cardNum)) {
                ObjectId id = databaseItem.getId();
                collection.remove(id);
                break;
            }
        }
    }

    @Override
    public Collection<CreditCard> getAllByUserName(String userName) {
        // TODO Auto-generated method stub
        Collection<CreditCard> databaseItems = this.getAll();
        Collection<CreditCard> result = new ArrayList<CreditCard>();
        for (CreditCard databaseItem : databaseItems) {
            if (databaseItem.getUserName().equals(userName)) {
                result.add(databaseItem);
            }
        }
        return result;
    }
}

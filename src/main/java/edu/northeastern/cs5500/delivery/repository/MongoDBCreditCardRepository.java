package edu.northeastern.cs5500.delivery.repository;

import static com.mongodb.client.model.Filters.eq;

import edu.northeastern.cs5500.delivery.model.CreditCard;
import edu.northeastern.cs5500.delivery.service.MongoDBService;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.bson.Document;

public class MongoDBCreditCardRepository extends MongoDBRepository<CreditCard>
        implements GenericCreditCardRepository {

    @Inject
    public <T> MongoDBCreditCardRepository(MongoDBService mongoDBService) {
        super(CreditCard.class, mongoDBService);
    }

    @Override
    public CreditCard updateByUserName(CreditCard item, String userName) {
        return collection.findOneAndReplace(eq("userName", userName), item);
    }

    @Override
    public void deleteByCardNumber(Long value, String userName) {
        Document find = new Document();
        find.append("userName", userName);
        find.append("cardNumber", value);
        collection.deleteOne(find);
    }

    @Override
    public Collection<CreditCard> getAllByUserName(String userName) {
        // TODO Auto-generated method stub
        Document find = new Document();
        find.append("userName", userName);
        return collection.find(find).into(new ArrayList<>());
    }
}

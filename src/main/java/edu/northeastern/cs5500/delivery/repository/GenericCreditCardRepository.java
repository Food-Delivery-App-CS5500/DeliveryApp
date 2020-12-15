package edu.northeastern.cs5500.delivery.repository;

import edu.northeastern.cs5500.delivery.model.CreditCard;
import java.util.Collection;

/*
 * Generic repository for creditcard to implement a search, update or delete by username or card number.
 */
public interface GenericCreditCardRepository extends GenericRepository<CreditCard> {

    /**
     * return the updated creditcard based on username
     *
     * @param CreditCard new creditcard object
     * @param String userName being searched
     * @return CreditCard the updated creditcard object
     */
    public CreditCard updateByUserName(CreditCard newcard, String userName);

    /**
     * return the updated creditcard based on username
     *
     * @param CreditCard new creditcard object
     * @param String userName being searched
     * @return CreditCard the updated creditcard object
     */
    public void deleteByCardNumber(Long value, String userName);

    /**
     * return a list of creditcard based on username
     *
     * @param String userName being searched
     * @return a list of CreditCard objects
     */
    public Collection<CreditCard> getAllByUserName(String userName);
}

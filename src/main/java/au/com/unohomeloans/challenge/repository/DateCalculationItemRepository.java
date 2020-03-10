package au.com.unohomeloans.challenge.repository;

import au.com.unohomeloans.challenge.domain.DateCalculationItem;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DateCalculationItemRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public DateCalculationItemRepository(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void save(final DateCalculationItem dateCalculationItem) {
        dynamoDBMapper.save(dateCalculationItem);
    }

    public int countAllItems() {
        return dynamoDBMapper.scan(DateCalculationItem.class, new DynamoDBScanExpression()).size();
    }
}
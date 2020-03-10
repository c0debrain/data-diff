package au.com.unohomeloans.challenge.config;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

class DynamoDBConfigTest {

    private DynamoDBConfig dynamoDBConfig;

    @BeforeEach
    public void setup() {
        dynamoDBConfig = new DynamoDBConfig();
    }

    @Test
    public void shouldReturnValidProductionConfig() {
        DynamoDBMapper result = dynamoDBConfig.dynamoDBMapperForProduction();
        assertThat(result, notNullValue());
    }
}
package au.com.unohomeloans.challenge.config;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

class DynamoDBNonProdConfigTest {

    private DynamoDBNonProdConfig dynamoDBNonProdConfig;

    @BeforeEach
    public void setup() {
        dynamoDBNonProdConfig = new DynamoDBNonProdConfig();
        dynamoDBNonProdConfig.setAmazonAWSAccessKey("abcde");
        dynamoDBNonProdConfig.setAmazonAWSSecretKey("zzzzzz");
    }

    @Test
    public void shouldReturnValidConfig() {
        dynamoDBNonProdConfig.setAmazonDynamoDBEndpoint("http://my-dynamo-url/");
        DynamoDBMapper result = dynamoDBNonProdConfig.dynamoDBMapperForNonProduction();
        assertThat(result, notNullValue());
    }

    @Test
    public void shouldReturnValidConfigWhenUrlIsEmpty() {
        DynamoDBMapper result = dynamoDBNonProdConfig.dynamoDBMapperForNonProduction();
        assertThat(result, notNullValue());
    }

}
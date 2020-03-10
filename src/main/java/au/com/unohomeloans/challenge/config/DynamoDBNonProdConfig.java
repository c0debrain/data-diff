package au.com.unohomeloans.challenge.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;

@Profile("!prod")
@Configuration
public class DynamoDBNonProdConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public DynamoDBMapper dynamoDBMapperForNonProduction() {
        AmazonDynamoDB client = new AmazonDynamoDBClient(amazonAWSCredentials());
        client.setRegion(Region.getRegion(Regions.US_EAST_1));
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            client.setEndpoint(amazonDynamoDBEndpoint);
        }
        return new DynamoDBMapper(client);
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazonAWSAccessKey, amazonAWSSecretKey);
    }

    void setAmazonDynamoDBEndpoint(String amazonDynamoDBEndpoint) {
        this.amazonDynamoDBEndpoint = amazonDynamoDBEndpoint;
    }

    void setAmazonAWSAccessKey(String amazonAWSAccessKey) {
        this.amazonAWSAccessKey = amazonAWSAccessKey;
    }

    void setAmazonAWSSecretKey(String amazonAWSSecretKey) {
        this.amazonAWSSecretKey = amazonAWSSecretKey;
    }
}

package com.mercadolibre;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;
import java.util.stream.Collectors;

public class LambdaMutantStats implements RequestHandler {
    private AmazonDynamoDB amazonDynamoDB;
    private String DYNAMODB_TABLE_NAME = "mutantdb-qa";

    @Override
    public Object handleRequest(Object input, Context context) {
        this.initDynamoDbClient();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        List<MutantTableDB> mutantTableDBList = mapper.scan(MutantTableDB.class, new DynamoDBScanExpression());

        List<MutantTableDB> mutantlist = mutantTableDBList.stream().
                filter(x -> x.getIsmutant().equals("true")).collect(Collectors.toList());

        ResponseData responseData = ResponseData.builder()
                .count_mutant_dna(mutantlist.size())
                .count_human_dna(mutantTableDBList.size()-mutantlist.size())
                .ratio(Double.valueOf(mutantlist.size())/Double.valueOf(mutantTableDBList.size()))
                .build();
        return responseData;
    }
    private void initDynamoDbClient() {
        this.amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
    }
}

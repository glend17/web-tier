package org.cse546.config;

import com.amazonaws.auth.BasicAWSCredentials;

public class AWSConfiguration {
    public static final String SQS_REQUEST_URL = "";
    public static final String SQS_RESPONSE_URL = "";

    public static final  String imageBucketName="cse546-project1-image-bucket";
    public static final  String resultBucketName="cse546-project1-results-bucket";



    public static final String APP_TIER_AMI_ID = "";
    public static final String APP_TIER_KEY_PAIR_NAME = "";


    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";
    public static BasicAWSCredentials getAWSCREDENTIALS() {
        BasicAWSCredentials AWS_CREDENTIALS = new BasicAWSCredentials(
                ACCESS_KEY,
                SECRET_KEY
        );
        return AWS_CREDENTIALS;

    }
}

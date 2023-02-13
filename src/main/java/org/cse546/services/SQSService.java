package org.cse546.services;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import org.cse546.utility.AWSUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Service used to store images

@Service
public class SQSService {

    private static final Logger logger = LoggerFactory.getLogger(SQSService.class);

    @Autowired
    private AWSUtility awsUtility;

    //Request queue
    public void sendSavedImagesToRequestQueue(List<String> fileNames){
        for(String fileName :  fileNames) {
            logger.info("Sending image {} to SQS Request Queue ",fileName);
            awsUtility.getSQSQueue().sendMessage(awsUtility.getSqsRequestUrl(), fileName);
        }
    }

    public List<Message> readSQSResponseMessages(){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
                .withQueueUrl(awsUtility.getSqsResponseUrl())
                .withMaxNumberOfMessages(10)
                .withWaitTimeSeconds(15);	//use long polling


        ReceiveMessageResult result = awsUtility.getSQSQueue().receiveMessage(receiveMessageRequest);
        List<Message> msgList=result.getMessages();

        logger.info("Messages Read from queue {} ",msgList.size());
        return msgList;
    }

    public Map<String,String> receiveSQSResponse(List<String> fileNameList){
        int originalFileSize = fileNameList.size();
        int filesRead = 0;
        Map<String,String> results = new HashMap<>();
        while(filesRead<originalFileSize){
            List<Message> messages = readSQSResponseMessages();
            filesRead+=messages.size();
            for(Message m : messages){
                String body = m.getBody();
                //responses are of the form "image name,result"
                String[] values = body.split(",");

                //first part is the name of image, second part is the result from the
                //Neural network
                results.put(values[0], values[1]);

                logger.info("Received " + values[0]);
                try{
                    //delete the message
                    awsUtility.getSQSQueue().deleteMessage(awsUtility.getSqsResponseUrl(), m.getReceiptHandle());
                }catch (Exception e) {
                    logger.info("exception while deleting message, maybe deleted already");
                }
            }
        }
        return results;
    }


}
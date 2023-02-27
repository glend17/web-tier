```
Group Member Names:
Pratyush Pandey, ASU ID: 1225718442, Email: ppande28@asu.edu
Dhanraj Bhosale, ASU ID: 1225506620, Email: dbhosal1@asu.edu
Glen Dsouza, ASU ID: 1222318617, Email: gsdsouza@asu.edu

Member Tasks:
Pratyush
1. Creating the AMI image id and template with all dependencies. Took up the responsibility of understanding how AMI image id can be templatized. Figured out how all the dependencies(downloading java, bringing up jar on bootup) is done. 
2. Creating App Tier and debugging/testing using Spring Boot. Figured out technical issues with the response queue deletion. Figured out how to process images in app-tier, there were issues with consuming the message and sending it as an input to invoke python process.
3. Adding template to autoscaling group, figuring out the autoscaling process. Testing end to end. Alarm and policies required to scale up app-tier. Testing the scaling process. 

Dhanraj
1. Creating the AMI image id and template with all dependencies. Took up the responsibility of understanding how AMI image id can be templatized. Figured out how all the dependencies(downloading java, bringing up jar on bootup) is done.
2. AWS Infrastructure side configuration, deployment and testing of web-tier and app-tier. Figured out alarm and policies required to scale app tier. Performed a series of end to end tests and found issues and debugged and fixed them.
3. Metrics evaluation using dashboard. Found a critical issue with the deletion of the SQS response queue. Figured out how to fix it.



Glen
1. Creating web-tier using Spring Boot and debugging/testing using Spring Boot. Entire web-tier application, AWS java sdk, initialization of utility classes required to connect to AWS. Logic flow within web tier and SQS and S3 services. 
2. Creating App Tier and debugging/testing using Spring Boot. AWS java sdk, initialization of utility classes, java code in all the packages. The logic flow within and between the app-tier application, s3 and sqs services.
3. Project report, architecture diagram. 

PEM key for web-tier SSH access:
Web tier’s URL:
EIP:
SQS queue names: 
S3 bucket names:
```

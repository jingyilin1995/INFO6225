package com.csye6225.fall2018.coursepractise.datamodel;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class SnsConnector {
	static AmazonSNS sns;
	public static void init() {
		if(sns == null) {
			AWSCredentialsProvider credentialsProvider;
			try
			{
				credentialsProvider = new InstanceProfileCredentialsProvider(false);
				credentialsProvider.getCredentials();
			}
			catch(Exception e)
			{
				System.out.println("Fetching credentials from Profile");
				credentialsProvider = new ProfileCredentialsProvider();
				credentialsProvider.getCredentials();
			}
			 sns= AmazonSNSClientBuilder
					 .standard()
					 .withCredentials(credentialsProvider)
					 .withRegion("us-east-2")
					 .build();
			 System.out.println("I created the snsclient");
		}
	}
	public AmazonSNS getClient() {
		return sns;
	}
}

package com.thamiz.message;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

public class CreateTopic {

	public static void main(String[] args) throws IOException {
        Properties properties = ConfigUtils.getConfiguration("admin-config");
        AdminClient adminClient = AdminClient.create(properties);
        CreateTopicsResult result = adminClient.createTopics(Arrays.asList(new NewTopic("test1", 1, (short)1)));
        System.out.println("Topic Created: ");
    }
	
}

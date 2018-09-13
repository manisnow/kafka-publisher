package com.thamiz.message;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {

	public static void main(String[] args) throws IOException {
		SimpleConsumer consumer = new SimpleConsumer();
		consumer.recieveAndPrintMessage("test");

	}

	public void recieveAndPrintMessage(String topic) throws IOException {
		Properties prop = ConfigUtils.getConfiguration("consumer-config");

		KafkaConsumer<Long, Employee> consumer = new KafkaConsumer<>(prop);
		consumer.subscribe(Collections.singletonList(topic));
		while (true) {
			ConsumerRecords<Long, Employee> consumerRecords = consumer.poll(1000L);
			
			for (ConsumerRecord<Long, Employee> record : consumerRecords) {
				Employee message = record.value();
				System.out.println(message);
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			consumer.commitSync();
		}
		//consumer.close();
	}

}
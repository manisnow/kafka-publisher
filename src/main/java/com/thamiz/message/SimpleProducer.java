package com.thamiz.message;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {

	private Producer<Long, Employee> producer;

	long count = 0;

	private Producer<Long, String> dtmoProdcer;

	public SimpleProducer() throws IOException {
		if (producer == null) {
			createProducer();
		}
	}

	public SimpleProducer(Producer<Long, Employee> producer) {
		this.producer = producer;
	}

	private void createProducer() throws IOException {

		Properties configuration = ConfigUtils.getConfiguration("producer-config");
		producer = new KafkaProducer<>(configuration);
		dtmoProdcer = new KafkaProducer<>(configuration);
	}

	public void sendMessage(String topic, Employee message) throws IOException {
		producer.send(new ProducerRecord<Long, Employee>(topic, count++, message));
		producer.flush();
		producer.close();
		System.out.println("Done");
	}

	public void sendDtmoMessage(String topic, String message) throws IOException {
		dtmoProdcer.send(new ProducerRecord<Long, String>(topic, count++, message));
		dtmoProdcer.flush();
		dtmoProdcer.close();
		System.out.println("Done");
	}

	public void close() {
		producer.close();
	}

	public static void main(String[] args) throws IOException {
		SimpleProducer producer = new SimpleProducer();
		Employee emp = new Employee();
		emp.setEmpId(101L);
		emp.setFirstName("Ninad");
		emp.setLastName("Ingole");
		emp.setJoiningDate(new Date());
		producer.sendMessage("test", emp);
		// producer.sendDtmoMessage("TutorialTopic", "Dtmo Message");
		producer.close();
	}

}
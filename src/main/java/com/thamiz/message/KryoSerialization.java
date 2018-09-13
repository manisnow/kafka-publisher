package com.thamiz.message;

import java.util.Date;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Custom Serializer/Deserializer Implementation for {@link Employee} Object.
 * 
 * @author ninad
 *
 */
public class KryoSerialization implements Serializer<Employee>, Deserializer<Employee> {

	private ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
		protected Kryo initialValue() {
			Kryo kryo = new Kryo();
			kryo.addDefaultSerializer(Employee.class, new KryoSerializer());
			return kryo;
		};
	};

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	@Override
	public Employee deserialize(String topic, byte[] data) {
		Employee employee = kryos.get().readObject(new ByteBufferInput(data), Employee.class);
		return employee;
	}

	@Override
	public byte[] serialize(String topic, Employee data) {
		ByteBufferOutput output = new ByteBufferOutput(100);
		kryos.get().writeObject(output, data);
		return output.toBytes();
	}

	@Override
	public void close() {
		kryos.remove();
	}

	private static class KryoSerializer extends com.esotericsoftware.kryo.Serializer<Employee> {

		@Override
		public Employee read(Kryo kryo, Input input, Class<Employee> clazz) {
			Employee emp = new Employee();
			emp.setEmpId(input.readLong());
			emp.setFirstName(input.readString());
			emp.setLastName(input.readString());
			emp.setJoiningDate(new Date(input.readLong(true)));
			return emp;
		}

		@Override
		public void write(Kryo kryo, Output output, Employee message) {
			output.writeLong(message.getEmpId());
			output.writeString(message.getFirstName());
			output.writeString(message.getLastName());
			output.writeLong(message.getJoiningDate().getTime(), true);
		}

	}

}
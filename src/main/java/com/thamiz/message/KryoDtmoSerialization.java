package com.thamiz.message;

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
public class KryoDtmoSerialization implements Serializer<String>, Deserializer<String> {

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
	public String deserialize(String topic, byte[] data) {
		String employee = kryos.get().readObject(new ByteBufferInput(data), String.class);
		return employee;
	}

	@Override
	public byte[] serialize(String topic, String data) {
		ByteBufferOutput output = new ByteBufferOutput(100);
		kryos.get().writeObject(output, data);
		return output.toBytes();
	}

	@Override
	public void close() {
		kryos.remove();
	}

	private static class KryoSerializer extends com.esotericsoftware.kryo.Serializer<String> {

		@Override
		public String read(Kryo kryo, Input input, Class<String> clazz) {
			
			return input.readString();
		}

		@Override
		public void write(Kryo kryo, Output output, String message) {
			
			output.writeString(message);
			
		}

	}

}
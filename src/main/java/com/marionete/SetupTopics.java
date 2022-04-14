package com.marionete;

import com.marionete.model.FruitOrder;
import com.marionete.model.VegetableOrder;
import com.marionete.utils.StreamsUtils;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Properties;

public class SetupTopics {

    public static void main(String[] args) throws IOException {
        runProducer();
    }

    public static void runProducer() throws IOException {
        Properties properties = StreamsUtils.loadProperties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        try(Admin adminClient = Admin.create(properties);
            Producer<String, SpecificRecord> producer = new KafkaProducer<>(properties)) {

            final String inputOneTopic = properties.getProperty("input_one.topic");
            final String inputTwoTopic = properties.getProperty("input_two.topic");
            final String outputTopic = properties.getProperty("join.output.topic");
            final int numPartitions = Integer.parseInt(properties.getProperty("num.partitions"));
            final short replicationFactor = Short.parseShort(properties.getProperty("replication.factor"));

            List<NewTopic> topics = List.of(StreamsUtils.createTopic(inputOneTopic, numPartitions, replicationFactor),
                    StreamsUtils.createTopic(inputTwoTopic, numPartitions, replicationFactor),
                    StreamsUtils.createTopic(outputTopic, numPartitions, replicationFactor));
            adminClient.createTopics(topics);

            FruitOrder fruitOrderOne = FruitOrder.newBuilder()
                    .setFruitId("apple")
                    .setOrderId("order-1")
                    .setUserId("user-1").build();

            FruitOrder fruitOrderTwo = FruitOrder.newBuilder()
                    .setFruitId("lemon")
                    .setOrderId("order-2")
                    .setUserId("user-2").build();
            List<FruitOrder> fruitOrders = List.of(fruitOrderOne, fruitOrderTwo);

            VegetableOrder vegetableOrderOne = VegetableOrder.newBuilder()
                    .setVegetableId("green-beans")
                    .setOrderId("order-1")
                    .setUserId("user-1").build();

            VegetableOrder vegetableOrderTwo = VegetableOrder.newBuilder()
                    .setVegetableId("garlic")
                    .setOrderId("order-2")
                    .setUserId("user-2").build();

            List<VegetableOrder> vegetableOrders = List.of(vegetableOrderOne, vegetableOrderTwo);

            fruitOrders.forEach((fo -> {
                ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(inputOneTopic, fo.getUserId(), fo);
                producer.send(producerRecord, StreamsUtils.callback());
            }));

            vegetableOrders.forEach((vo -> {
                ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(inputTwoTopic, vo.getUserId(), vo);
                producer.send(producerRecord, StreamsUtils.callback());
            }));
        }
    }

}

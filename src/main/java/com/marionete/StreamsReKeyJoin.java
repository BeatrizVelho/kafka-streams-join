package com.marionete;

import com.marionete.model.CombinedOrder;
import com.marionete.model.FruitOrder;
import com.marionete.model.VegetableOrder;
import com.marionete.utils.StreamsUtils;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class StreamsReKeyJoin {

    public static <T extends SpecificRecord> SpecificAvroSerde<T> getSpecificAvroSerde(final Map<String, Object> serdeConfig) {
        final SpecificAvroSerde<T> specificAvroSerde = new SpecificAvroSerde<>();
        specificAvroSerde.configure(serdeConfig, false);
        return specificAvroSerde;
    }

    public static Topology buildTopology(Properties streamsProps, Map<String, Object> configMap) {

        StreamsBuilder builder = new StreamsBuilder();
        String inputOneTopic = streamsProps.getProperty("input_one.topic");
        String inputTwoTopic = streamsProps.getProperty("input_two.topic");
        String outputTopic = streamsProps.getProperty("join.output.topic");

        SpecificAvroSerde<FruitOrder> fruitSerde = getSpecificAvroSerde(configMap);
        SpecificAvroSerde<VegetableOrder> vegetableSerde = getSpecificAvroSerde(configMap);
        SpecificAvroSerde<CombinedOrder> combinedSerde = getSpecificAvroSerde(configMap);

        CombinedOrderJoiner combinedOrderJoiner = new CombinedOrderJoiner();

        KStream<String, FruitOrder> fruitStream =
                builder.stream(inputOneTopic, Consumed.with(Serdes.String(), fruitSerde))
                        .peek((key, value) -> System.out.println("Fruit stream incoming record key " + key + " value " + value));

        KStream<String, VegetableOrder> vegetableStream =
                builder.stream(inputTwoTopic, Consumed.with(Serdes.String(), vegetableSerde))
                        .peek((key, value) -> System.out.println("Vegetable stream incoming record " + key + " value " + value));

        ///////////////////
        if (streamsProps.keySet().containsAll(Arrays.asList(
                "input_one.join.field", "input_one.new.topic",
                "input_two.join.field", "input_two.new.topic"))) {

            String inputOneJoinField = streamsProps.getProperty("input_one.join.field");
            String inputOneNewTopic = streamsProps.getProperty("input_one.new.topic");

            KStream<String, FruitOrder> fruitStreamNew = fruitStream.selectKey((k, v) -> new JSONObject(v.toString()).getString(inputOneJoinField))
                    .peek((key, value) -> System.out.println("New Fruit stream incoming record key " + key + " value " + value));
            fruitStreamNew.to(inputOneNewTopic, Produced.with(Serdes.String(), fruitSerde));

            fruitStream = fruitStreamNew;


            String inputTwoJoinField = streamsProps.getProperty("input_two.join.field");
            String inputTwoNewTopic = streamsProps.getProperty("input_two.new.topic");

            KStream<String, VegetableOrder> vegetableStreamNew = vegetableStream.selectKey((k, v) -> new JSONObject(v.toString()).getString(inputTwoJoinField))
                    .peek((key, value) -> System.out.println("New Vegetable stream incoming record key " + key + " value " + value));
            vegetableStreamNew.to(inputTwoNewTopic, Produced.with(Serdes.String(), vegetableSerde));

            vegetableStream = vegetableStreamNew;
        }
        ///////////////////

        KStream<String, CombinedOrder> combinedStream =
                fruitStream.join(
                                vegetableStream,
                                combinedOrderJoiner,
                                JoinWindows.of(Duration.ofMinutes(30)),
                                StreamJoined.with(Serdes.String(), fruitSerde, vegetableSerde))
                        .peek((key, value) -> System.out.println("Stream-Stream Join record key " + key + " value " + value));

        combinedStream.to(outputTopic, Produced.with(Serdes.String(), combinedSerde));

        return builder.build();
    }

    public static void main(String[] args) throws IOException {
        Properties streamsProps = StreamsUtils.loadProperties();

        Map<String, Object> configMap = StreamsUtils.propertiesToMap(streamsProps);

        KafkaStreams kafkaStreams = new KafkaStreams(buildTopology(streamsProps, configMap), streamsProps);

        SetupTopics.runProducer();

        final CountDownLatch latch = new CountDownLatch(1);

        // Attach shutdown handler to catch Control-C.
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                kafkaStreams.close(Duration.ofSeconds(5));
                latch.countDown();
            }
        });

        try {
            kafkaStreams.cleanUp();
            kafkaStreams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }

        System.exit(0);
    }
}

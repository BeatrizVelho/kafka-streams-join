package com.marionete;

import static com.marionete.utils.StreamsUtils.*;
import com.marionete.model.CombinedOrder;
import com.marionete.model.FruitOrder;
import com.marionete.model.VegetableOrder;
import com.marionete.utils.StreamsUtils;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StreamsReKeyJoinTest {

    private final static String TEST_CONFIG_FILE_REKEY = "src/test/resources/streams_rekey.properties";
    private final static String TEST_CONFIG_FILE_NO_REKEY = "src/test/resources/streams_no_rekey.properties";

    private List<CombinedOrder> actualJoinResults;

    public  void setUp(String filePath) throws IOException {
        final Properties streamsProps = new Properties();
        try (InputStream inputStream = new FileInputStream(filePath)) {
            streamsProps.load(inputStream);
        }

        Map<String, Object> configMap = StreamsUtils.propertiesToMap(streamsProps);

        final Topology topology = StreamsReKeyJoin.buildTopology(streamsProps, configMap);

        try (final TopologyTestDriver testDriver = new TopologyTestDriver(topology, streamsProps)) {
            Serde<String> stringSerde = Serdes.String();

            String inputOneTopic = streamsProps.getProperty("input_one.topic");
            String inputTwoTopic = streamsProps.getProperty("input_two.topic");
            String outputTopic = streamsProps.getProperty("join.output.topic");

            final SpecificAvroSerde<FruitOrder> fruitSerde = getSpecificAvroSerde(configMap);
            final SpecificAvroSerde<VegetableOrder> vegetableSerde = getSpecificAvroSerde(configMap);
            final SpecificAvroSerde<CombinedOrder> combinedSerde = getSpecificAvroSerde(configMap);

            final TestInputTopic<String, FruitOrder> fruitTopic = testDriver
                    .createInputTopic(inputOneTopic, stringSerde.serializer(), fruitSerde.serializer());
            final TestInputTopic<String, VegetableOrder> vegetableTopic = testDriver
                    .createInputTopic(inputTwoTopic, stringSerde.serializer(), vegetableSerde.serializer());
            final TestOutputTopic<String, CombinedOrder> combinedTopic = testDriver
                    .createOutputTopic(outputTopic, stringSerde.deserializer(), combinedSerde.deserializer());

            final List<FruitOrder> fruitOrders = new ArrayList<>();
            fruitOrders.add(FruitOrder.newBuilder().setFruitId("fruit-11").setOrderId("order-109").setUserId("user-1").build());
            fruitOrders.add(FruitOrder.newBuilder().setFruitId("fruit-12").setOrderId("order-110").setUserId("user-2").build());

            final List<VegetableOrder> vegetableOrders = new ArrayList<>();
            vegetableOrders.add(VegetableOrder.newBuilder().setVegetableId("vegetable-13").setOrderId("order-109").setUserId("user-1").build());
            vegetableOrders.add(VegetableOrder.newBuilder().setVegetableId("vegetable-14").setOrderId("order-111").setUserId("user-2").build());

            for (final FruitOrder f : fruitOrders) {
                fruitTopic.pipeInput(f.getUserId(), f);
            }

            for (final VegetableOrder v : vegetableOrders) {
                vegetableTopic.pipeInput(v.getUserId(), v);
            }

            this.actualJoinResults = combinedTopic.readValuesToList();
        }
    }

    @Test
    public void topologyShouldJoinRecordsReKey() throws IOException {
        setUp(TEST_CONFIG_FILE_REKEY);
        final List<CombinedOrder> expectedJoinResults = new ArrayList<>();
        expectedJoinResults.add(CombinedOrder.newBuilder()
                .setFruitId("fruit-11").setVegetableId("vegetable-13").setOrderId("order-109").setUserId("user-1").build());

        assertThat(expectedJoinResults, equalTo(actualJoinResults));
    }

    @Test
    public void topologyShouldJoinRecordsNoReKey() throws IOException {
        setUp(TEST_CONFIG_FILE_NO_REKEY);
        final List<CombinedOrder> expectedJoinResults = new ArrayList<>();
        expectedJoinResults.add(CombinedOrder.newBuilder()
                .setFruitId("fruit-11").setVegetableId("vegetable-13").setOrderId("order-109").setUserId("user-1").build());
        expectedJoinResults.add(CombinedOrder.newBuilder()
                .setFruitId("fruit-12").setVegetableId("vegetable-14").setOrderId("order-110").setUserId("user-2").build());

        assertThat(expectedJoinResults, equalTo(actualJoinResults));
    }
}
package org.javatraining.simplehazlecastexample.client;


import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.javatraining.simplehazlecastexample.model.Person;

public class SimpleClientOne {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        GroupConfig groupConfig = clientConfig.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("pass");

        HazelcastInstance hzClient
                = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<Integer, Person> personMap = hzClient.getMap("data");
        personMap.put(1, new Person(1, "Alex"));
        personMap.put(2, new Person(2, "Sara"));
        personMap.put(3, new Person(3, "Bob"));




    }
}

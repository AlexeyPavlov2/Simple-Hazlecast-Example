package org.javatraining.simplehazlecastexample.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.javatraining.simplehazlecastexample.model.Person;

import java.util.Comparator;

public class SimpleClientTwo {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        GroupConfig groupConfig = clientConfig.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("pass");

        HazelcastInstance hzClient
                = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<Integer, Person> personMap = hzClient.getMap("data");

        personMap.entrySet().stream()
                .sorted(Comparator.comparing(el -> el.getValue().getName()))
                .forEach(System.out::println);

    }
}

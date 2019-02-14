package org.javatraining.simplehazlecastexample.server;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.*;
import org.javatraining.simplehazlecastexample.model.Person;

import java.util.Comparator;

public class ServerNode {
    public static void main(String[] args) throws InterruptedException {
        Config nodeConfig = new Config();
        GroupConfig groupConfig = nodeConfig.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("pass");

        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(nodeConfig);
        hzInstance.getClientService().addClientListener(new ClientListener() {
            @Override
            public void clientConnected(Client client) {
                System.out.println(client.getClientType() + ", " + client.getUuid() + " connected");
            }

            @Override
            public void clientDisconnected(Client client) {
                System.out.println(client.getClientType() + ", " + client.getUuid() + " disconnected");
            }
        });


        Thread.sleep(60000);
        IMap<Integer, Person> personMap = hzInstance.getMap("data");

        personMap.entrySet().stream()
                .sorted(Comparator.comparing(el -> el.getValue().getName()))
                .forEach(System.out::println);











    }
}

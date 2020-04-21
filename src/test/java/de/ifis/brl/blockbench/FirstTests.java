package de.ifis.brl.blockbench;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.Network;

class FirstTests {

    @Test
    void testSomething() {

        Network net = Network.newNetwork();

        var node = new MultiChainContainer()
                .withNetwork(net)
                .withNetworkAliases("master");
        node.start();

        var slave = new MultiChainContainer()
                .withConnectTo("master")
                .withNetwork(net)
                .withNetworkAliases("slave");
        slave.start();

    }

}

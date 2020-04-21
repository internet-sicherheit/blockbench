package de.ifis.brl.blockbench;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.nio.file.Path;

public class MultiChainContainer extends GenericContainer<MultiChainContainer> {

    private static final ImageFromDockerfile DOCKERFILE = new ImageFromDockerfile("multichain")
            .withDockerfile(Path.of("src/main/resources/multichain/Dockerfile"));

    private static final String INIT_COMMAND = "multichain-util create chain1 -anyone-can-connect=true " +
            "&& multichaind chain1 -port=4711 -rpcport=4712 -rpcallowip=0.0.0.0/0 -rpcuser=foo -rpcpassword=bar";
    private static final String CONNECT_COMMAND = "multichaind chain1@%s:4711 -port=4711 -rpcport=4712";

    private String connectTo;

    public MultiChainContainer() {
        super(DOCKERFILE);

        withExposedPorts(4711, 4712);
    }

    @Override
    protected void configure() {
        super.configure();

        if (connectTo == null) {
            withCommand("sh", "-c", INIT_COMMAND);
        } else {
            withCommand("sh", "-c", String.format(CONNECT_COMMAND, connectTo));
        }
    }

    public MultiChainContainer withConnectTo(String connectTo) {
        this.connectTo = connectTo;
        return this;
    }
}

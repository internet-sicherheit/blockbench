package de.ifis.brl.blockbench;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.nio.file.Path;

public class MultiChainContainer extends GenericContainer {

    private static final ImageFromDockerfile DOCKERFILE = new ImageFromDockerfile("multichain")
            .withDockerfile(Path.of("src/main/resources/multichain/Dockerfile"));

    public MultiChainContainer() {
        super(DOCKERFILE);

        withExposedPorts(4711, 4712);
        withCommand("sh", "-c", "multichain-util create chain1 " +
                "&& multichaind chain1 -port=4711 -rpcport=4712 -rpcallowip=0.0.0.0/0 -rpcuser=foo -rpcpassword=bar");
    }

}

import api.SaveProfileResource;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EndToEndTest {
    MongodStarter starter = MongodStarter.getDefaultInstance();
    int port = 27018;
    MongodExecutable mongodExecutable;
    IMongodConfig mongodConfig;

    @Before
    @SneakyThrows
    public void before() {
        mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build();

        mongodExecutable = starter.prepare(mongodConfig);
        MongodProcess mongod = mongodExecutable.start();
    }

    @Test
    public void endToEnd() {
        System.out.println();
        SaveProfileResource resource = new SaveProfileResource();

    }

    private void save() {

    }

    @After
    public void after() {
        if (mongodExecutable != null)
            mongodExecutable.stop();
    }

}

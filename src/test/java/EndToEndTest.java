import api.LoadProfileResource;
import api.SaveProfileResource;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import entities.User;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.UserDAO;

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
    @SneakyThrows
    public void endToEnd() {
        //Insert a user
        User user = CreateUser.getUser();
        UserDAO userDAO = new UserDAO();
        userDAO.save(user);

        //Make sure its persisted
        Thread.sleep(1000);

        LoadProfileResource loadProfileResource = new LoadProfileResource();
        JSONObject authJson = new JSONObject();
        authJson.put("username", "jamescross91");

        String userJson = loadProfileResource.getUserJson(authJson);
        System.out.println();
    }

    private void save() {

    }

    @After
    public void after() {
        if (mongodExecutable != null)
            mongodExecutable.stop();
    }

}

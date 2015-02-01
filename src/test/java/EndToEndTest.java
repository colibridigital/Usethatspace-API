import api.LoadProfileResource;
import api.SaveProfileResource;
import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.extract.UserTempNaming;
import de.flapdoodle.embed.process.runtime.Network;
import entities.User;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import persistence.UserDAO;

import static org.junit.Assert.assertEquals;

public class EndToEndTest {
    int port = 27018;
    MongodExecutable mongodExecutable;
    IMongodConfig mongodConfig;
    Command command = Command.MongoD;
    IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
            .defaults(command)
            .artifactStore(new ArtifactStoreBuilder()
                    .defaults(command)
                    .download(new DownloadConfigBuilder()
                            .defaultsForCommand(command))
                    .executableNaming(new UserTempNaming()))
            .build();

    MongodStarter starter = MongodStarter.getInstance(runtimeConfig);

    @Before
    @SneakyThrows
    public void before() {
        mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build();

        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
    }

    @Test
    @SneakyThrows
    @Ignore
    public void endToEnd() {
        try {
            //Insert a user
            User user = CreateUser.getUser();
            UserDAO userDAO = new UserDAO();
            userDAO.save(user);

            //Make sure its persisted
            Thread.sleep(1000);

            LoadProfileResource loadProfileResource = new LoadProfileResource();
            JSONObject authJson = new JSONObject();
            authJson.put("username", "jamescross91");

            JSONObject userJson = new JSONObject(loadProfileResource.getUserJson(authJson));
            String expectedJson = IOUtils.toString(this.getClass().getResourceAsStream("/expecteduser.json"),"UTF-8");

            String id = userJson.getString("id");

            userJson.remove("id");
            String userJsonString = userJson.toString();

            assertEquals(expectedJson, userJsonString);

            //Persist the user via the api with an update, and check it saves what's expected
            SaveProfileResource saveProfileResource = new SaveProfileResource();
            userJson.put("id", id);
            userJson.remove("lastName");
            userJson.put("lastName", "blah");
            JSONObject fullWebJSON = new JSONObject();
            fullWebJSON.put("user", userJson);
            fullWebJSON.put("auth", new JSONObject().put("username", "jamescross91"));

            saveProfileResource.processJSON(fullWebJSON);

            //Make sure its persisted
            Thread.sleep(1000);

            userJson = new JSONObject(loadProfileResource.getUserJson(authJson));
            assertEquals("blah", userJson.getString("lastName"));
            assertEquals("jamescross91", userJson.getString("username"));

        } finally {
            mongodExecutable.stop();
        }
    }

    @After
    public void after() {

    }

}

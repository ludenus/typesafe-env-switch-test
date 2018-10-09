import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import qa.config.EnvName;
import qa.config.Environment;

public class RestTest {

    private Logger log = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    Environment env;

    @BeforeClass
    public void init(){
        env = loadEnvFromConfig();
    }

    @Test
    public void checkGetKEK() throws Exception {

        String kekUrl = String.format("%s://%s:%d", env.KEK.protocol, env.KEK.host, env.KEK.port);

        log.info("kekUrl={}",kekUrl);

        RestAssured.given()
                .when().log().all()
                .auth().preemptive().basic("user", "password")
                .contentType(ContentType.JSON)
                .get(kekUrl)
                .then().log().all()
                .statusCode(HttpStatus.SC_OK);
    }


    Environment loadEnvFromConfig(){
        Config config = ConfigFactory.load();
        EnvName currentEnv = EnvName.valueOf(config.getString("currentEnv"));
        Config subConfig = config.getConfig(currentEnv.name());
        return ConfigBeanFactory.create(subConfig, Environment.class);
    }

}

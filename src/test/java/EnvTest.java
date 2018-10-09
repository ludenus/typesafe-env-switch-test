import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.unitils.reflectionassert.ReflectionAssert;
import qa.config.AddressHolder;
import qa.config.EnvName;
import qa.config.Environment;

public class EnvTest {

    private Logger log = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    private Config config = ConfigFactory.load();


    @Test
    public void checkLoadConfig() {
        String currentEnvString = config.getString("currentEnv");
        // Since we do not allow any random string here,
        // valid options are restricted via EnvName enum
        EnvName currentEnv = EnvName.valueOf(currentEnvString);
        Config subConfig = config.getConfig(currentEnv.name());
        Environment environment = ConfigBeanFactory.create(subConfig, Environment.class);
        log.info("currentEnv = {}", currentEnv);
        log.info("environment = {}", environment);
        // If we are here and no exceptions raised - config was successfully loaded and parsed
    }

    @Test
    public void checkDEVconfigValues() {
        Config subConfig = config.getConfig(EnvName.DEV.name());
        Environment environment = ConfigBeanFactory.create(subConfig, Environment.class);

        log.info("DEV environment = {}", environment);
        ReflectionAssert.assertLenientEquals(expectedDEV(), environment);
    }


    @Test
    public void checkTESTconfigValues() {
        Config subConfig = config.getConfig(EnvName.TEST.name());
        Environment environment = ConfigBeanFactory.create(subConfig, Environment.class);

        log.info("TEST environment = {}", environment);
        ReflectionAssert.assertLenientEquals(expectedTEST(), environment);
    }


    private Environment expectedDEV() {

        Environment expected = new Environment();
        expected.CDN = new AddressHolder();
        expected.CDN.protocol = "http";
        expected.CDN.host = "dev-cdn.ee";
        expected.CDN.port = 123;
        expected.KEK = new AddressHolder();
        expected.KEK.protocol = "http";
        expected.KEK.host = "kek2.ipo";
        expected.KEK.port = 125;

        return expected;
    }

    private Environment expectedTEST() {

        Environment expected = new Environment();
        expected.CDN = new AddressHolder();
        expected.CDN.protocol = "http";
        expected.CDN.host = "test-cdn.ee";
        expected.CDN.port = 13;
        expected.KEK = new AddressHolder();
        expected.KEK.protocol = "http";
        expected.KEK.host = "test-kek.ee";
        expected.KEK.port = 1337;

        return expected;
    }

}

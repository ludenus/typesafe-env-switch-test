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

  @Test
  public void checkStringLocator() {
    String host = config.getString("DEV.CDN.host");
    log.info("host={}", host);

  }

  private Environment expectedDEV() {

    Environment expected = new Environment();

    expected.CDN = new AddressHolder();
    expected.CDN.protocol = config.getString("defaultProtocol");
    expected.CDN.host = config.getString("defaultHost");
    expected.CDN.port = 30086;
    expected.CDN.authHeaderValue = config.getString("defaultAuthHeaderValue");

    expected.KEK = new AddressHolder();
    expected.KEK.protocol = "https";
    expected.KEK.host = "petstore.swagger.io/v2/store/inventory";
    expected.KEK.port = 80;
    expected.KEK.authHeaderValue = config.getString("defaultAuthHeaderValue");

    expected.AMQP = new AddressHolder();
    expected.AMQP.protocol = config.getString("defaultProtocol");
    expected.AMQP.host = config.getString("defaultHost");
    expected.AMQP.port = 30073;
    expected.AMQP.authHeaderValue = config.getString("defaultAuthHeaderValue");

    expected.PVC = new AddressHolder();
    expected.PVC.protocol = config.getString("defaultProtocol");
    expected.PVC.host = config.getString("defaultHost");
    expected.PVC.port = 30092;
    expected.PVC.authHeaderValue = config.getString("defaultAuthHeaderValue");

    expected.SPM = new AddressHolder();
    expected.SPM.protocol = config.getString("defaultProtocol");
    expected.SPM.host = config.getString("defaultHost");
    expected.SPM.port = 30071;
    expected.SPM.authHeaderValue = config.getString("defaultAuthHeaderValue");

    return expected;
  }

  private Environment expectedTEST() {

    Environment expected = new Environment();

    expected.CDN = new AddressHolder();
    expected.CDN.protocol = config.getString("defaultProtocol");
    expected.CDN.host = "test-erk.ee";
    expected.CDN.port = 13;
    expected.CDN.authHeaderValue = config.getString("defaultAuthHeaderValue");

    return expected;
  }

}

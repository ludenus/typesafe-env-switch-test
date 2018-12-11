import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.config.AddressHolder;
import qa.config.EnvName;
import qa.config.ModuleName;

public class ResolveAddressHolderTest {

  private Logger log = LoggerFactory.getLogger(this.getClass().getCanonicalName());

  private Config config = ConfigFactory.load();


  AddressHolder resolveAddressHolder(EnvName currentEnv, ModuleName currentModule) {

    Config subConfig = config.getConfig(currentEnv.name() + "." + currentModule.name());
    return ConfigBeanFactory.create(subConfig, AddressHolder.class);
  }

  @DataProvider
  public Object[][] envNameModuleName() {
    return new Object[][]{
        {"DEV", "CDN"},
        {"DEV", "KEK"},
        {"TEST", "CDN"},
        {"ZZZZZ", "KEK"},
    };
  }


  // this test fails on the last iteration only because of No enum constant qa.config.EnvName.ZZZZZ
  @Test(dataProvider = "envNameModuleName")
  public void checkResolveAddressHeader(String envName, String moduleName) {
    EnvName currentEnv = EnvName.valueOf(envName);
    ModuleName currentModule = ModuleName.valueOf(moduleName);

    log.info("addressHolder: {}", resolveAddressHolder(currentEnv, currentModule));
  }


  @DataProvider
  public Object[][] addressHolder() {
    return new Object[][]{
        {resolveAddressHolder(EnvName.valueOf("DEV"), ModuleName.valueOf("CDN"))},
        {resolveAddressHolder(EnvName.valueOf("DEV"), ModuleName.valueOf("KEK"))},
        {resolveAddressHolder(EnvName.valueOf("ZZZZZZ"), ModuleName.valueOf("CDN"))},
        {resolveAddressHolder(EnvName.valueOf("TEST"), ModuleName.valueOf("KEK"))},
    };
  }

  // this test fails to start because we get exception in Data Provider
  @Test(dataProvider = "addressHolder")
  public void checkUseAddressHolder(AddressHolder addressHolder) {
    log.info("addressHolder: {}", addressHolder);
  }

}

# typesafe-env-switch-test

Example project with typesafe config that switches DEV and TEST environments.

Open `src/test/resources/application.conf`

Switch environment via currentEnv.
```
currentEnv = "TEST" // valid options: DEV, TEST. ANy other value yields java.lang.IllegalArgumentException: No enum constant qa.config.EnvName.NNNNN
```

Examine `src/test/java/EnvTest.java` for Config load and verification examples.

Examine `src/test/java/RestTest.java` for Config usage with RestAssured example. 

Run tests
```
$ gradle clean test --info
```

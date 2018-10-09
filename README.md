# typesafe-env-switch-test

Example project with typesafe config that switches DEV and TEST environments.

Open [application.conf](../src/test/resources/application.conf)

Switch environment via currentEnv.
```
currentEnv = "TEST"
currentEnv = ${?CURRENT_ENV}

```
Valid options: `DEV`, `TEST`. Any other value yields `java.lang.IllegalArgumentException: No enum constant qa.config.EnvName.NNNNN`

If `CURRENT_ENV` is defined, it overrides the above value


Examine [EnvTest.java](../src/test/java/EnvTest.java) for Config load and verification examples.

Examine [RestTest.java](../src/test/java/RestTest.java) for Config usage with RestAssured example. 

Run tests
```
$ gradle clean test --info
```

package core.configs;


public final class Configs {
    public static final String TEST_DATA_DIR = System.getenv("TEST_DATA_DIR") != null ? System.getenv("TEST_DATA_DIR") : "src/main/resources/testdata";
}
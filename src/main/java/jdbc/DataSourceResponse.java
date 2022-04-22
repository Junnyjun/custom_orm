package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DataSourceResponse {
    public static DataSourceValue dataSourceValue() throws FileNotFoundException {
        File file = new File("src/main/resources/properties.yaml");
        DataSourceProperties dataSourceProperties = new DataSourceProperties(file);

        return new DataSourceValue(
                dataSourceProperties.getProperty("url"),
                dataSourceProperties.getProperty("id"),
                dataSourceProperties.getProperty("password")
        );
    }

    public static class DataSourceProperties{
        private final Properties properties;

        public DataSourceProperties(File yaml) {
            this.properties = new Properties();
            try(FileInputStream stream =  new FileInputStream(yaml)) {
                properties.load(stream);
            } catch (IOException e) {
                throw new IllegalArgumentException("PROPERTIES MUST HAVE");
            }
        }
        public String getProperty(String key) {
            return properties.getProperty(key);
        }
    }
}

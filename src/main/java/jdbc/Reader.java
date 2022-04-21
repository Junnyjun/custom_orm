package jdbc;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class Reader {

    public static DataSourceValue getYaml() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File file = new File("src/main/resources/properties.yaml");
        try(FileInputStream stream =  new FileInputStream(file)) {
          Map<String,String> map = yaml.load(stream);
            return new DataSourceValue(map.get("url"),map.get("id"),map.get("password"));
         } catch (IOException e) {
            throw new FileNotFoundException("yaml is wrong");
        }
    }
}

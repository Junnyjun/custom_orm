package jdbc;

public class DataSourceValue {
    private String url;
    private String id;
    private String password;

    public DataSourceValue() {
    }

    public DataSourceValue(String url, String id, String password) {
        this.url = url;
        this.id = id;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}

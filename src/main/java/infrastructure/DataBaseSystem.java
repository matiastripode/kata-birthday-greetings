package infrastructure;

import java.util.List;

public interface DataBaseSystem {
    public static final String ERROR_NETWORK_FAILED = "Error network connection failed";

    public List query(String query) throws Exception;
}

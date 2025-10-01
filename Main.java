import java.util.HashMap;
import java.util.Map;

public class Main {
    String firstName;
    String lastName;

    public Main(Map<String, String> constructorData) {
        firstName = constructorData.get("firstName");
        firstName = constructorData.get("lastName");
    }

    public static void main(String[] args) {
        Map<String, String> constructorData = new HashMap<String, String>() {
            {
                put("firstName", "Alex");
                put("lastName", "Armstrong");
            }
        };

        Main myObj = new Main(constructorData);
    }

}

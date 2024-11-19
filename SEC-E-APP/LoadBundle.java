import java.util.ResourceBundle;

public class LoadBundle {
    ResourceBundle rb = ResourceBundle.getBundle("config");

    String readBundle(String key) {
        return rb.getString(key);
    }
}

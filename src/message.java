import java.io.Serializable;

/**
 * @author KYDSZ
 */
public class message implements Serializable {
    private final String name;
    private String message;

    public message(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage (String info) {
        message = info;
    }
}

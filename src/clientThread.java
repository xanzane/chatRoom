import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zane
 */
public class clientThread extends Thread {
    private final Socket socket;
    public clientThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                message msg = (message) ois.readObject();
                System.out.println(msg.getName() + ": " + msg.getMessage());

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


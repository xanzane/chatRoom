import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;


/**
 * @author Zane
 */
public class severThread extends Thread {

    private final Socket socket;
    private final Vector<Socket> vector;

    public severThread(Socket socket, Vector<Socket> vector) {
        this.socket = socket;
        this.vector = vector;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                message msg = (message) ois.readObject();
                System.out.println(msg.getName() + ": " + msg.getMessage());

                for (Socket s : vector) {
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(msg);
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


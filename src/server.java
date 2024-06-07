import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zane
 */
public class server {
    private ServerSocket ss;
//    private ConcurrentHashMap<String, Socket> userMap;
    private ObjectInputStream reader;
    private Vector<Socket> vector = new Vector<>();

    public void start() {
        try {
            System.out.println("服务器启动");
            ss = new ServerSocket(6666);
            while (true) {
                Socket socket = ss.accept();

                vector.add(socket);

                // 创建线程
                new severThread(socket, vector).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new server().start();
    }
}

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Zane
 */
public class client {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        try {
            Socket socket = new Socket("localhost", 6666);

            // 注册
            System.out.println("输入名字");
            String name = scanner.nextLine();
            message msg = new message(name, name + "进入聊天室");
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            writer.writeObject(msg);

            // 创建线程接收信息
            new clientThread(socket).start();

            // 发送信息
            String info;
            System.out.println("输入要发送的信息,回车发送");
            while (true) {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                info = scanner.nextLine();
                msg.setMessage(info);
                oos.writeObject(msg);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        new client().start();
    }
}

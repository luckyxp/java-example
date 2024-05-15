package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author climb.xu
 * @date 2022/9/5 11:39
 */
public class NIOClient {
    public static void main(String[] args) {
        try {
            SocketChannel channel = SocketChannel.open(new InetSocketAddress(8080));
            channel.configureBlocking(false);
            ByteBuffer buf = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("请输入: ");
                String msg = scanner.next();
                buf.put(msg.getBytes(StandardCharsets.UTF_8));
                buf.flip();
                channel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

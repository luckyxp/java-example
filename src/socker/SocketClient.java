package socker;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author climb.xu
 * @date 2022/8/26 12:01
 */
public class SocketClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream os = socket.getOutputStream();

        String CRLF = "\r\n";
        String info = "POST / HTTP/1.1";
        String header = "Content-Type: application/json\nContent-Length: 15";
        String body = "{\"user\":\"root\"}";
        String request = info + CRLF + header + CRLF+CRLF + body;
        os.write(request.getBytes());
        os.flush();
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        StringBuilder response = new StringBuilder();
        int c;
        while ((c= isr.read())!=-1) {
            response.append((char)c);
        }
        System.out.println(response);
    }
}

package socker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author climb.xu
 * @date 2022/8/26 9:42
 */
public class SocketServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String key = "Content-Length:".toLowerCase();
            StringBuilder request = new StringBuilder();
            String line;
            do {
                line = br.readLine();
                request.append(line).append("\n");
            } while (!line.toLowerCase().startsWith(key));
            int length = Integer.parseInt(line.substring(16).trim());
            while (length > -2) {
                request.append((char) br.read());
                length--;
            }
            System.out.println(request);
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            String CRLF = "\r\n";
            String info = "HTTP/1.1 404 ERROR";
            String header = "Content-type: text/json;charset=utf-8";
            String body = "{\"user\":\"admin\"}";
            String response = info + CRLF + header + CRLF+CRLF + body;
            osw.write(response);
            osw.flush();
            osw.close();
        }
    }
}

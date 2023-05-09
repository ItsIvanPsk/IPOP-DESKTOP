import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/* Clase muy simple que he creado simplemente para conectarme al server y ver si puede registrar conexiones,
 * se ejecuta aparte del run.bat, dandole al boton run de VS de arriba derecha
 */
public class testConnectionWS {
  public static void main(String[] args) throws URISyntaxException, InterruptedException {
    WebSocketClient client = new WebSocketClient(new URI("wss://ipopgame2-production.up.railway.app/")) {
      @Override
      public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to server.");
        send("Test message from client.");
      }

      @Override
      public void onMessage(String message) {
        System.out.println("Received message: " + message);
      }

      @Override
      public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed.");
      }

      @Override
      public void onError(Exception ex) {
        System.out.println("Error: " + ex.getMessage());
      }
    };

    client.connect();
    while (!client.isOpen()) {
      TimeUnit.MILLISECONDS.sleep(100);
    }
    // wait for the server to receive the message
    TimeUnit.SECONDS.sleep(5);
    client.close();
  }
}

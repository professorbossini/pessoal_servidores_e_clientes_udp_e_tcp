import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
  public static void main(String[] args) {
    //obtém a porta a que o servidor ficará vinculado
    int porta = Integer.parseInt(args[0]);
    //abre um socket e fica aguardando por uma conexão (método accept)
    //o resultado é um socket regular que em que podemos escrever
    //e do qual podemos ler
    //Note a construção dos objetos para leitura e escrita
    try (Socket serverSocket = new ServerSocket(porta).accept();
          PrintWriter escritor = new PrintWriter(serverSocket.getOutputStream(),true);
          BufferedReader leitor = new BufferedReader (new InputStreamReader(serverSocket.getInputStream()))){
            while (true){
              String msg = leitor.readLine();
              escritor.println("Confirmando: " + msg);
            }
    }
    catch (Exception e){
      e.printStackTrace();
    }

  }
}
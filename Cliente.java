import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Cliente {
  public static void main (String[] args){
    //obtém endereço e porta 
    String host = args[0];
    int porta = Integer.parseInt(args[1]);
    //constrói um socket que já tenta
    // se conectar a um servidor em host:porta
    // Constrói um objeto capaz de ler do socket,
    //ou seja, receber dados do servidor
    // Constrói um objeto capaz de escrever no socket
    // ou seja, enviar dados para o servidor
    try(Socket socketCliente = new Socket (host, porta);    
        PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true);
        BufferedReader leitor = new BufferedReader (new InputStreamReader(socketCliente.getInputStream()))){
          while (true){
            String msg = JOptionPane.showInputDialog("O que você deseja enviar?");
            escritor.println(msg);
            JOptionPane.showMessageDialog(null, leitor.readLine());
          }
    }
    catch (Exception e){
      e.printStackTrace();
    }

  }
}
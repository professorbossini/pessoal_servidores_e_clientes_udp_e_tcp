import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane;

public class ClienteUDP {
  public static void main(String[] args) {
    //pega host e porta da linha de comando
    String host = args[0];
    int porta = Integer.parseInt(args [1]);
    //constrói um DatagramSocket, capaz de enviar e receber pacotes
    try (DatagramSocket socketCliente = new DatagramSocket();
        ){
          while (true){
            //pega o que o usuário deseja enviar
            String msg = JOptionPane.showInputDialog ("O que deseja enviar?");
            //converte para um vetor de bytes
            byte[] bytes = msg.getBytes();
            //monta o pacote a ser enviado
            DatagramPacket pacote = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), porta);
            //o socket envia o pacote
            socketCliente.send (pacote); 
            //monta um pacote para receber a resposta do servidor
            DatagramPacket pacoteRecebido = new DatagramPacket(new byte[256], 256);
            //fica esperando a resposta
            socketCliente.receive(pacoteRecebido);
            //converte o vetor de bytes para string e mostra
            String texto = new String (pacoteRecebido.getData());
            JOptionPane.showMessageDialog(null, texto);            
          }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    

  }
}
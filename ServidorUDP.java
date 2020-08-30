import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
  public static void main(String[] args) {
    //pega a porta a que o servidor ficará vinculado
    int porta = Integer.parseInt(args[0]);
    // constrói um DatagramSocket, capaz de enviar e receber pacotes
    try (DatagramSocket socketServidor = new DatagramSocket(porta)){
      while (true){
        //vetor de bytes para guardar a resposta
        byte[] bytes = new byte[256];
        //pacote para guardar a resposta
        DatagramPacket pacote = new DatagramPacket(bytes, bytes.length);
        //fica esperando a requisição do cliente, execução bloqueia aqui
        socketServidor.receive(pacote);
        //monta a mensagem de resposta
        String msg = "Confirmando: " + new String(pacote.getData());
        //pega a porta para a qual o pacote deve ser enviado
        //a porta do cliente não é necessariamente igual à porta do servidor
        int portaCliente = pacote.getPort();
        //monta um pacote para enviar a resposta
        DatagramPacket pacoteResposta = new DatagramPacket(msg.getBytes(), 0, bytes.length, pacote.getAddress(), portaCliente);  
        //envia a resposta     
        socketServidor.send(pacoteResposta);
      }
      
    }
    catch (Exception e){
      e.printStackTrace();
    }
    
  }
}
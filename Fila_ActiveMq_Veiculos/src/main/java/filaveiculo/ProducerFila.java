package filaveiculo;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class ProducerFila {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; // "vm://localhost";
    private static String subject = "VEICULO"; //Nome da fila da mensagem
    
    private static void envia(String nome, String marca, int ano, double valor, Date datahj) throws JMSException {
        // Obtem uma conexão com o servidor JMS
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Mensagens JMS são enviadas usando uma sessão. 
        // Uma sessão não transacional é informando com "false" no primeiro parametro 
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

         //Destination é a fila para onde as mensagens serão enviadas. 
        // Se não existir,  ela sera criada automaticamente.
        Destination destination = session.createQueue(subject);
        
        // Cria o produtor para enviar os veículos para a fila
        MessageProducer producer = session.createProducer(destination);
                
        // instancia um XStream
        XStream xstream = new XStream(new StaxDriver());

        // cria um objeto de mensagem "Pessoa"
        //Pessoa mp = new Pessoa("João", 100.0D); 
        Veiculo mp = new Veiculo(nome, marca, ano, valor, datahj);
        // Producer side:
        TextMessage message = session.createTextMessage(xstream.toXML(mp));
        //message = session.createTextMessage(xstream.toXML(mp));
        producer.send(message);
        //Mensagem de confirmação de envio
        System.out.println("Mensagem enviada!");
        connection.close();
    }
    public static void main(String[] args) throws JMSException {
    	
        Boolean finalizar = false;
        //Criando entrada de dados        
        System.out.println("\n******************************************************\n");
        System.out.println("Classificados de Veículos");
        System.out.println("\n******************************************************\n");
        
        do{ 
            Scanner scan = new Scanner(System.in);
            System.out.println("Digite o nome do Cliente: ");
            String nome = scan.nextLine().toUpperCase();
            System.out.println("Digite a marca e modelo do Veiculo: ");
            String marca = scan.nextLine().toUpperCase();
            System.out.println("Digite o ano de fabricação: ");
            int ano = scan.nextInt();
            System.out.println("Digite o Valor do Veiculo: ");
            double valor = scan.nextDouble();
            scan.nextLine();
            Calendar calendar = Calendar.getInstance();
            Date datahj = calendar.getTime();
            
            envia(nome, marca, ano, valor,datahj);
            
            System.out.println("\n Você deseja continuar (sim ou não)?");
            String resposta = scan.nextLine().toLowerCase();
            if(!"sim".equals(resposta)){ finalizar = true;}
                        
        } while (finalizar == false);
          
    }
    
    
}

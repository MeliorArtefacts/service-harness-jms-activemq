/* __  __    _ _      
  |  \/  |  | (_)       
  | \  / | ___| |_  ___  _ __ 
  | |\/| |/ _ \ | |/ _ \| '__|
  | |  | |  __/ | | (_) | |   
  |_|  |_|\___|_|_|\___/|_|   
    Service Harness
*/
package org.melior.client.activemq;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.MessageCreator;

/**
 * TODO
 * @author Melior
 * @since 2.3
 */
public class TextMessageCreator implements MessageCreator{
    private String payload;

    private String correlationId;

  /**
   * Constructor.
   * @param payload The message payload
   * @param correlationId The correlation identifier
   */
  public TextMessageCreator(
    final String payload,
    final String correlationId){
        super();

        this.payload = payload;

        this.correlationId = correlationId;
  }

  /**
   * Create message.
   * @param session The session
   * @return The message
   * @throws JMSException if unable to create the message
   */
  public Message createMessage(
    final Session session) throws JMSException{
        Message message;

        message = session.createTextMessage(payload);
    message.setJMSCorrelationID(correlationId);

    return message;
  }

}

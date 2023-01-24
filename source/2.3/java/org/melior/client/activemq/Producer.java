/* __  __      _ _            
  |  \/  |    | (_)           
  | \  / | ___| |_  ___  _ __ 
  | |\/| |/ _ \ | |/ _ \| '__|
  | |  | |  __/ | | (_) | |   
  |_|  |_|\___|_|_|\___/|_|   
        Service Harness
*/
package org.melior.client.activemq;
import java.lang.reflect.Method;
import org.melior.client.core.ClientObject;
import org.melior.client.exception.RemotingException;

/**
 * A wrapper around an ActiveMQ {@code Producer} object which makes the {@code Producer} persistent.
 * @author Melior
 * @since 2.3
 */
public class Producer extends ClientObject<ActiveMQClientConfig, Session, javax.jms.MessageProducer> {

    /**
     * Constructor.
     * @param configuration The client configuration
     * @param session The session
     * @throws RemotingException if an error occurs during the construction
     */
    public Producer(
        final ActiveMQClientConfig configuration,
        final Session session) throws RemotingException {

        super("Producer", configuration, session, javax.jms.MessageProducer.class);
    }

    /**
     * Handle proxy invocation.
     * @param object The object on which the method was invoked
     * @param method The method to invoke
     * @param args The arguments to invoke with
     * @return The result of the invocation
     * @throws Throwable if the invocation fails
     */
    public Object invoke(
        final Object object,
        final Method method,
        final Object[] args) throws Throwable {

        String methodName;
        Object invocationResult;

        methodName = method.getName();

        if (methodName.equals("close") == true) {

            invocationResult = null;
        }
        else {

            invocationResult = invoke(method, args);
        }

        return invocationResult;
    }

}

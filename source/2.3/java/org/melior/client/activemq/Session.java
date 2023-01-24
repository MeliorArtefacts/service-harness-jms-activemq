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
 * A wrapper around an ActiveMQ {@code Session} object which makes the {@code Session} persistent.
 * @author Melior
 * @since 2.3
 */
public class Session extends ClientObject<ActiveMQClientConfig, Connection, javax.jms.Session> {

    private Producer producer;

    /**
     * Constructor.
     * @param configuration The client configuration
     * @param connection The connection
     * @throws RemotingException if an error occurs during the construction
     */
    public Session(
        final ActiveMQClientConfig configuration,
        final Connection connection) throws RemotingException {

        super("Session", configuration, connection, javax.jms.Session.class);
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

        if (methodName.equals("createProducer") == true) {

            if (producer == null) {

                producer = new Producer(configuration, this);
                producer.setDelegate((javax.jms.MessageProducer) invoke(method, args));
            }

            invocationResult = producer.getProxy();
        }

        else if (methodName.equals("close") == true) {

            invocationResult = null;
        }
        else {

            invocationResult = invoke(method, args);
        }

        return invocationResult;
    }

}

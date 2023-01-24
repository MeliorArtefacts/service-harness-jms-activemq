/* __  __      _ _            
  |  \/  |    | (_)           
  | \  / | ___| |_  ___  _ __ 
  | |\/| |/ _ \ | |/ _ \| '__|
  | |  | |  __/ | | (_) | |   
  |_|  |_|\___|_|_|\___/|_|   
        Service Harness
*/
package org.melior.client.activemq;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.ResourceAllocationException;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;

/**
 * Implements a manager for persistent ActiveMQ {@code Connection} objects, for connections to
 * ActiveMQ servers. The manager writes statistics from the underlying connection pool to
 * the logs whenever a {@code Connection} is borrowed from the pool.
 * @author Melior
 * @since 2.3
 */
public class ConnectionManager extends org.melior.client.pool.ConnectionManager<ActiveMQClientConfig, Connection, javax.jms.Connection> implements javax.jms.ConnectionFactory, QueueConnectionFactory, TopicConnectionFactory {

    /**
     * Constructor.
     * @param configuration The client configuration
     * @param connectionFactory The connection factory
     */
    public ConnectionManager(
        final ActiveMQClientConfig configuration,
        final ConnectionFactory connectionFactory) {

        super(configuration, connectionFactory);
    }

    /**
     * Create connection.
     * @return The connection
     * @throws JMSException if unable to create a connection
     */
    public javax.jms.Connection createConnection() throws JMSException {

        return createConnection(null, null);
    }

    /**
     * Create connection.
     * @param userName The user name
     * @param password The password
     * @return The connection
     * @throws JMSException if unable to create a connection
     */
    public javax.jms.Connection createConnection(
        final String userName,
        final String password) throws JMSException {

        try {

            return getConnection();
        }
        catch (Exception exception) {
            throw new ResourceAllocationException(exception.getMessage());
        }

    }

    /**
     * Create queue connection.
     * @return The connection
     * @throws JMSException if unable to create a connection
     */
    public QueueConnection createQueueConnection() throws JMSException {
        return (QueueConnection) createConnection();
    }

    /**
     * Create queue connection.
     * @param userName The user name
     * @param password The password
     * @return The connection
     * @throws JMSException if unable to create a connection
     */
    public QueueConnection createQueueConnection(
        final String userName,
        final String password) throws JMSException {
        return (QueueConnection) createConnection(userName, password);
    }

    /**
     * Create topic connection.
     * @return The connection
     * @throws JMSException if unable to create a connection
     */
    public TopicConnection createTopicConnection() throws JMSException {
        return (TopicConnection) createConnection();
    }

    /**
     * Create topic connection.
     * @param userName The user name
     * @param password The password
     * @return The connection
     * @throws JMSException if unable to create a connection
     */
    public TopicConnection createTopicConnection(
        final String userName,
        final String password) throws JMSException {
        return (TopicConnection) createConnection(userName, password);
    }

    public JMSContext createContext() {
        return null;
    }

    public JMSContext createContext(
        final String userName,
        final String password) {
        return null;
    }

    public JMSContext createContext(
        final String userName,
        final String password,
        final int sessionMode) {
        return null;
    }

    public JMSContext createContext(
        final int sessionMode) {
        return null;
    }

}

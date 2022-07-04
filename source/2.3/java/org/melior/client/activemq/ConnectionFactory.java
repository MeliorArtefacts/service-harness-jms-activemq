/* __  __    _ _      
  |  \/  |  | (_)       
  | \  / | ___| |_  ___  _ __ 
  | |\/| |/ _ \ | |/ _ \| '__|
  | |  | |  __/ | | (_) | |   
  |_|  |_|\___|_|_|\___/|_|   
    Service Harness
*/
package org.melior.client.activemq;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.melior.client.exception.RemotingException;
import org.melior.client.pool.ConnectionPool;

/**
 * TODO
 * @author Melior
 * @since 2.3
 */
public class ConnectionFactory implements org.melior.client.core.ConnectionFactory<ActiveMQClientConfig, Connection, javax.jms.Connection>{
    private ActiveMQConnectionFactory connectionFactory;

  /**
   * Constructor.
   * @param async The asynchronous transport indicator
   * @param configuration The client configuration
   */
  public ConnectionFactory(
    final boolean async,
    final ActiveMQClientConfig configuration){
        super();

        connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setUseAsyncSend(async);
    connectionFactory.setBrokerURL(configuration.getUrl());
    connectionFactory.setUserName(configuration.getUsername());
    connectionFactory.setPassword(configuration.getPassword());
    connectionFactory.setCloseTimeout(configuration.getConnectionTimeout());
    connectionFactory.setConnectResponseTimeout(configuration.getConnectionTimeout());
    connectionFactory.setSendTimeout(configuration.getRequestTimeout());
  }

  /**
   * Create a new connection.
   * @param configuration The client configuration
   * @param connectionPool The connection pool
   * @return The new connection
   * @throws RemotingException if unable to create a new connection
   */
  public Connection createConnection(
    final ActiveMQClientConfig configuration,
    final ConnectionPool<ActiveMQClientConfig, Connection, javax.jms.Connection> connectionPool) throws RemotingException{
        Connection connection;

        connection = new Connection(configuration, connectionPool, connectionFactory);
    connection.open();

    return connection;
  }

  /**
   * Destroy the connection.
   * @param connection The connection
   */
  public void destroyConnection(
    final Connection connection){
        connection.close();
  }

}

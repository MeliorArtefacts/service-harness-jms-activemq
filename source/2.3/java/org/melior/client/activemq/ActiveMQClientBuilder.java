/* __  __      _ _            
  |  \/  |    | (_)           
  | \  / | ___| |_  ___  _ __ 
  | |\/| |/ _ \ | |/ _ \| '__|
  | |  | |  __/ | | (_) | |   
  |_|  |_|\___|_|_|\___/|_|   
        Service Harness
*/
package org.melior.client.activemq;
import javax.net.ssl.SSLContext;

/**
 * Convenience class for building a {@code ActiveMQClient}.  Provides
 * switches for asynchronous transport and secure connections.
 * @author Melior
 * @since 2.3
 */
public class ActiveMQClientBuilder {

    private boolean async = false;

    private boolean ssl = false;

    private SSLContext sslContext;

    /**
     * Constructor.
     */
    private ActiveMQClientBuilder() {

        super();
    }

    /**
     * Create ActiveMQ client builder.
     * @return The ActiveMQ client builder
     */
    public static ActiveMQClientBuilder create() {

        return new ActiveMQClientBuilder();
    }

    /**
     * Build ActiveMQ client.
     * @return The ActiveMQ client
     */
    public ActiveMQClient build() {

        return new ActiveMQClient(async, ssl, sslContext);
    }

    /**
     * Enable asynchronous transport.
     * @return The ActiveMQ client builder
     */
    public ActiveMQClientBuilder async() {

        this.async = true;

        return this;
    }

    /**
     * Enable SSL.
     * @return The ActiveMQ client builder
     */
    public ActiveMQClientBuilder ssl() {

        this.ssl = true;

        return this;
    }

    /**
     * Set SSL context.
     * @param sslContext The SSL context
     * @return The ActiveMQ client builder
     */
    public ActiveMQClientBuilder sslContext(
        final SSLContext sslContext) {

        this.sslContext = sslContext;

        return this;
    }

}

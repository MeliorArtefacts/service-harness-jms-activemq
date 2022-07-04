/* __  __    _ _      
  |  \/  |  | (_)       
  | \  / | ___| |_  ___  _ __ 
  | |\/| |/ _ \ | |/ _ \| '__|
  | |  | |  __/ | | (_) | |   
  |_|  |_|\___|_|_|\___/|_|   
    Service Harness
*/
package org.melior.client.activemq;
import org.melior.client.core.ClientConfig;

/**
 * TODO
 * @author Melior
 * @since 2.3
 */
public class ActiveMQClientConfig extends ClientConfig{
    private String queue;

  /**
   * Constructor.
   */
  protected ActiveMQClientConfig(){
        super();
  }

  /**
   * Configure client.
   * @param clientConfig The new client configuration parameters
   * @return The client configuration parameters
   */
  public ActiveMQClientConfig configure(
    final ActiveMQClientConfig clientConfig){
    super.configure(clientConfig);
    this.queue = clientConfig.queue;

    return this;
  }

  /**
   * Get queue.
   * @return The queue
   */
  public String getQueue(){
    return queue;
  }

  /**
   * Set queue.
   * @param queue The queue
   */
  public void setQueue(
    final String queue){
    this.queue = queue;
  }

}

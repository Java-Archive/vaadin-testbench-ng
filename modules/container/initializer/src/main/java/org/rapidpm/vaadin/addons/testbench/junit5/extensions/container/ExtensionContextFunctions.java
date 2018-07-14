/**
 *
 */
package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import static org.rapidpm.vaadin.addons.junit5.extensions.ExtensionFunctions.store;
import java.util.function.Function;
import org.junit.jupiter.api.extension.ExtensionContext;

public interface ExtensionContextFunctions {
  public static Function<ExtensionContext, ContainerInfo> containerInfo() {
    return extensionContext -> {
      final int port = (int) store().apply(extensionContext).get(NetworkFunctions.SERVER_PORT);
      final String host =
          store().apply(extensionContext).get(NetworkFunctions.SERVER_IP, String.class);
      ContainerInfo containerInfo = new ContainerInfo(port, host);
      return containerInfo;
    };
  }
}

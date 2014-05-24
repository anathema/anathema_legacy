package net.sf.anathema.framework;

import net.sf.anathema.framework.extension.AnathemaExtension;
import net.sf.anathema.framework.messaging.IMessageContainer;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.lib.registry.IRegistry;

public interface IApplicationModel {

  Repository getRepository();

  IRegistry<String, AnathemaExtension> getExtensionPointRegistry();

  IMessaging getMessaging();

  IMessageContainer getMessageContainer();
}
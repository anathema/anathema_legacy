package net.sf.anathema.framework;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.messaging.IMessageContainer;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.lib.registry.IRegistry;

public interface IApplicationModel {

  Repository getRepository();

  IRegistry<String, IAnathemaExtension> getExtensionPointRegistry();

  IMessaging getMessaging();

  IMessageContainer getMessageContainer();
}
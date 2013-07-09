package net.sf.anathema.framework;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.messaging.IMessageContainer;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.registry.IRegistry;

public interface IApplicationModel {

  Repository getRepository();

  IReportRegistry getReportRegistry();

  IItemTypeRegistry getItemTypeRegistry();

  IRegistry<String, IAnathemaExtension> getExtensionPointRegistry();

  IMessaging getMessaging();

  IMessageContainer getMessageContainer();

  ResourceLoader getResourceLoader();

  ObjectFactory getObjectFactory();
}
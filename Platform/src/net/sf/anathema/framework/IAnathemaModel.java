package net.sf.anathema.framework;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.messaging.IMessageContainer;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.registry.IRegistry;

public interface IAnathemaModel {

  IRepository getRepository();

  IReportRegistry getReportRegistry();

  IItemTypeRegistry getItemTypeRegistry();

  IRegistry<String, IAnathemaExtension> getExtensionPointRegistry();

  IRegistry<IItemType, IRepositoryItemPersister> getPersisterRegistry();

  IRegistry<IItemType, IItemViewFactory> getViewFactoryRegistry();

  IItemManagementModel getItemManagement();

  IMessaging getMessaging();

  IMessageContainer getMessageContainer();

  ResourceLoader getResourceLoader();
}
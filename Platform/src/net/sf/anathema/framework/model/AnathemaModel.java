package net.sf.anathema.framework.model;

import java.io.File;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.messaging.AnathemaMessaging;
import net.sf.anathema.framework.messaging.IAnathemaMessageContainer;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IExtensibleDataSetProvider;
import net.sf.anathema.lib.resources.IResourceCollection;

public class AnathemaModel implements IAnathemaModel {

  private final IRegistry<String, IAnathemaExtension> extensionRegistry = new Registry<String, IAnathemaExtension>();
  private final IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry = new Registry<IItemType, IRepositoryItemPersister>();
  private final IItemManagementModel itemManagment = new ItemManagmentModel();
  private final IReportRegistry reportRegistry = new ReportRegistry();
  private final IRegistry<IItemType, IItemViewFactory> viewFactoryRegistry = new Registry<IItemType, IItemViewFactory>();
  private final IItemTypeRegistry itemTypes = new ItemTypeRegistry();
  private final Repository repository;
  private final AnathemaMessaging messaging;
  private final IExtensibleDataSetProvider dataSetProvider;

  public AnathemaModel(File repositoryFolder, IResourceCollection resourceData) {
    this.repository = new Repository(repositoryFolder, itemManagment);
    this.messaging = new AnathemaMessaging(resourceData.getUIResources());
    this.dataSetProvider = resourceData.getDataProvider();
  }

  public final IRepository getRepository() {
    return repository;
  }

  public final IReportRegistry getReportRegistry() {
    return reportRegistry;
  }

  public final IRegistry<String, IAnathemaExtension> getExtensionPointRegistry() {
    return extensionRegistry;
  }

  public final IRegistry<IItemType, IRepositoryItemPersister> getPersisterRegistry() {
    return persisterRegistry;
  }

  public IItemManagementModel getItemManagement() {
    return itemManagment;
  }

  public IRegistry<IItemType, IItemViewFactory> getViewFactoryRegistry() {
    return viewFactoryRegistry;
  }

  public IItemTypeRegistry getItemTypeRegistry() {
    return itemTypes;
  }

  public IAnathemaMessaging getMessaging() {
    return messaging;
  }

  public IAnathemaMessageContainer getMessageContainer() {
    return messaging;
  }

  @Override
  public IExtensibleDataSetProvider getDataSetProvider() {
	return dataSetProvider;
  }
}

package net.sf.anathema.framework.model;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.messaging.IMessageContainer;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.framework.repository.FileSystemRepository;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.Resources;

import java.io.File;

public class ApplicationModel implements IApplicationModel {

  private final IRegistry<String, IAnathemaExtension> extensionRegistry = new Registry<>();
  private final IRegistry<IItemType, RepositoryItemPersister> persisterRegistry = new Registry<>();
  private final IReportRegistry reportRegistry = new ReportRegistry();
  private final IItemTypeRegistry itemTypes = new ItemTypeRegistry();
  private final FileSystemRepository repository;
  private final Messaging messaging;
  private final ResourceLoader resourceLoader;

  public ApplicationModel(File repositoryFolder, Resources resources, ResourceLoader resourceLoader) {
    this.repository = new FileSystemRepository(repositoryFolder);
    this.messaging = new Messaging(resources);
    InformativeMessages informativeMessages = new WordsOfTheWise(resourceLoader);
    this.resourceLoader = resourceLoader;
    new WelcomeMessage(messaging, informativeMessages).show();
  }

  @Override
  public final Repository getRepository() {
    return repository;
  }

  @Override
  public final IReportRegistry getReportRegistry() {
    return reportRegistry;
  }

  @Override
  public final IRegistry<String, IAnathemaExtension> getExtensionPointRegistry() {
    return extensionRegistry;
  }

  @Override
  public final IRegistry<IItemType, RepositoryItemPersister> getPersisterRegistry() {
    return persisterRegistry;
  }

  @Override
  public IItemTypeRegistry getItemTypeRegistry() {
    return itemTypes;
  }

  @Override
  public IMessaging getMessaging() {
    return messaging;
  }

  @Override
  public IMessageContainer getMessageContainer() {
    return messaging;
  }

  @Override
  public ResourceLoader getResourceLoader() {
    return resourceLoader;
  }
}
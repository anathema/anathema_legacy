package net.sf.anathema.framework.model;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.messaging.IMessageContainer;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.framework.repository.FileSystemRepository;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.framework.environment.Resources;

import java.io.File;

public class ApplicationModel implements IApplicationModel {

  private final IRegistry<String, IAnathemaExtension> extensionRegistry = new Registry<>();
  private final FileSystemRepository repository;
  private final Messaging messaging;
  private final ResourceLoader resourceLoader;
  private final ObjectFactory objectFactory;

  public ApplicationModel(File repositoryFolder, Resources resources, ResourceLoader resourceLoader, ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
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
  public final IRegistry<String, IAnathemaExtension> getExtensionPointRegistry() {
    return extensionRegistry;
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

  @Override
  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }
}
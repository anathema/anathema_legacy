package net.sf.anathema.framework.model;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.extension.AnathemaExtension;
import net.sf.anathema.framework.messaging.IMessageContainer;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.framework.repository.FileSystemRepository;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;

import java.io.File;

public class ApplicationModel implements IApplicationModel {

  private final IRegistry<String, AnathemaExtension> extensionRegistry = new Registry<>();
  private final FileSystemRepository repository;
  private final Messaging messaging;

  public ApplicationModel(File repositoryFolder, Environment environment) {
    this.repository = new FileSystemRepository(repositoryFolder);
    this.messaging = new Messaging(environment);
    InformativeMessages informativeMessages = new WordsOfTheWise(environment);
    new WelcomeMessage(messaging, informativeMessages).show();
  }

  @Override
  public final Repository getRepository() {
    return repository;
  }

  @Override
  public final IRegistry<String, AnathemaExtension> getExtensionPointRegistry() {
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
}
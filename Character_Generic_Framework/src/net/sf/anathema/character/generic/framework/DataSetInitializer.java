package net.sf.anathema.character.generic.framework;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.initialization.AnathemaInitializer;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.character.generic.data.IExtensibleDataSetCompiler;
import net.sf.anathema.character.generic.data.IExtensibleDataSetProvider;
import net.sf.anathema.lib.resources.ResourceFile;

import java.util.Collection;
import java.util.Set;

public class DataSetInitializer {

  private static final Logger logger = Logger.getLogger(AnathemaInitializer.class);
  private final ResourceLoader resourceLoader;
  private final Instantiater instantiater;

  public DataSetInitializer(ResourceLoader resourceLoader, Instantiater instantiater) {
    this.resourceLoader = resourceLoader;
    this.instantiater = instantiater;
  }

  public IExtensibleDataSetProvider initializeExtensibleResources() throws InitializationException {
    ExtensibleDataManager manager = new ExtensibleDataManager();
    Instantiater instantiater = this.instantiater;
    Collection<IExtensibleDataSetCompiler> compilers = instantiater.instantiateAll(ExtensibleDataSetCompiler.class);
    for (IExtensibleDataSetCompiler compiler : compilers) {
      try {
        ProxySplashscreen.getInstance().displayStatusMessage("Compiling " + compiler.getName() + "...");
        getDataFilesFromReflection(compiler);
        manager.addDataSet(compiler.build());
      } catch (Exception e) {
        StringBuilder message = new StringBuilder("Could not compile ");
        message.append(compiler.getName());
        Throwable cause = e.getCause();
        while (cause != null) {
          message.append(":");
          message.append("\n");
          String messagePart = cause.getMessage();
          if (messagePart.contains("Nested")) {
            int nested = messagePart.indexOf("Nested");
            messagePart = messagePart.substring(0, nested);
          }
          message.append(messagePart);
          cause = cause.getCause();
        }
        throw new InitializationException(message.toString(), e);
      }
    }
    return manager;
  }

  private void getDataFilesFromReflection(IExtensibleDataSetCompiler compiler) throws Exception {
    Set<ResourceFile> files = resourceLoader.getResourcesMatching(compiler.getRecognitionPattern());
    logger.info(compiler.getName() + ": Found " + files.size() + " data files.");
    for (ResourceFile file : files) {
      compiler.registerFile(file);
    }
  }
}
package net.sf.anathema.character.generic.framework;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.framework.module.ICharacterModule;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.initialization.AnathemaInitializer;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.IExtensibleDataSetCompiler;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IAnathemaResourceFile;
import net.sf.anathema.lib.resources.IExtensibleDataSetRegistry;
import net.sf.anathema.lib.resources.IResourceDataManager;

import java.util.Collection;
import java.util.Set;

public class CharacterModuleContainerInitializer {

  private static final Logger logger = Logger.getLogger(AnathemaInitializer.class);
	
  private final Instantiater instantiater;
  private final AnathemaReflections reflections;

  public CharacterModuleContainerInitializer(Instantiater instantiater, AnathemaReflections reflections) {
    this.instantiater = instantiater;
    this.reflections = reflections;
  }

  public CharacterModuleContainer initContainer(IResourceDataManager resourceDataManager, IDataFileProvider dataFileProvider) throws InitializationException {
	initializeCharacterExtensibleResources(reflections, resourceDataManager.getDataSetRegistry());
    CharacterModuleContainer container = new CharacterModuleContainer(resourceDataManager, dataFileProvider, instantiater);
    Collection<ICharacterModule<ICharacterModuleObject>> modules = instantiater.instantiateAll(CharacterModule.class);
    for (ICharacterModule<ICharacterModuleObject> module : modules) {
      container.addCharacterGenericsModule(module);
    }
    return container;
  }
  
  private void initializeCharacterExtensibleResources(AnathemaReflections reflections, IExtensibleDataSetRegistry registry) throws InitializationException {
	Collection<IExtensibleDataSetCompiler> compilers =
					instantiater.instantiateAll(ExtensibleDataSetCompiler.class);
	for (IExtensibleDataSetCompiler compiler : compilers) {
	  try {
		ProxySplashscreen.getInstance().displayStatusMessage(compiler.getSplashStatusString());
		getDataFilesFromReflection(reflections, compiler);
		registry.addDataSet(compiler.build());
	  } catch (Exception e) {
		  
	    throw new InitializationException("Failed to start plugin.", e);
	  }
	}
  }
  
  private void getDataFilesFromReflection(AnathemaReflections reflections, IExtensibleDataSetCompiler compiler) throws Exception {
	Set<IAnathemaResourceFile> files = reflections.getResourcesMatching(compiler.getRecognitionPattern());
	logger.info(compiler.getName() + ": Found "+ files.size() +" data files.");
	for (IAnathemaResourceFile file : files) {
		compiler.registerFile(file);
	}
  }
}
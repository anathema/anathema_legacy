package net.sf.anathema.character.generic.framework;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.framework.module.ICharacterModule;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.framework.resources.ExtensibleDataManager;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.IExtensibleDataSetCompiler;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IAnathemaResourceFile;
import net.sf.anathema.lib.resources.IExtensibleDataSetRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.ResourceCollection;

import java.util.Collection;
import java.util.Set;

public class CharacterModuleContainerInitializer {

  private static final Logger logger = Logger.getLogger(CharacterModuleContainerInitializer.class);
  private final AnathemaReflections reflections;
  private final Instantiater instantiater;

  public CharacterModuleContainerInitializer(AnathemaReflections reflections) {
    this.reflections = reflections;
    instantiater = new ReflectionsInstantiater(reflections);
  }

  public CharacterModuleContainer initContainer(IResources resources, IDataFileProvider dataFileProvider) throws InitializationException {
	ExtensibleDataManager dataSetManager = new ExtensibleDataManager();
	initializeCharacterExtensibleResources(dataSetManager);
	  
    CharacterModuleContainer container = new CharacterModuleContainer(new ResourceCollection(resources, dataSetManager),
    									dataFileProvider, instantiater);
    Collection<ICharacterModule<ICharacterModuleObject>> modules = instantiater.instantiateAll(CharacterModule.class);
    for (ICharacterModule<ICharacterModuleObject> module : modules) {
      container.addCharacterGenericsModule(module);
    }
    return container;
  }
  
  private void initializeCharacterExtensibleResources(IExtensibleDataSetRegistry registry) throws InitializationException {
	Instantiater instantiater = new ReflectionsInstantiater(reflections);
	Collection<IExtensibleDataSetCompiler> compilers = instantiater.instantiateAll(ExtensibleDataSetCompiler.class);
	for (IExtensibleDataSetCompiler compiler : compilers) {
	  try {
		ProxySplashscreen.getInstance().displayStatusMessage("Compiling "+compiler.getName()+"...");
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
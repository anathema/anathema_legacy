package net.sf.anathema.character;

import java.util.Collection;
import java.util.Set;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.initialization.AnathemaInitializer;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.IExtensibleDataSetCompiler;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.Plugin;
import net.sf.anathema.initialization.Startable;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IAnathemaResourceFile;
import net.sf.anathema.lib.resources.IExtensibleDataSetRegistry;

@Plugin
public class CharacterPlugin implements Startable {

  private static final Logger logger = Logger.getLogger(AnathemaInitializer.class);
	
  @Override
  public void doStart(AnathemaReflections reflections, IExtensibleDataSetRegistry registry) throws Exception {
	  initializeCharacterExtensibleResources(reflections, registry);
  }
  
  private void initializeCharacterExtensibleResources(AnathemaReflections reflections, IExtensibleDataSetRegistry registry) throws InitializationException {
	Instantiater instantiater = new ReflectionsInstantiater(reflections);
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
package net.sf.anathema.initialization;

import net.disy.commons.core.exception.CentralExceptionHandling;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.exception.CentralExceptionHandler;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.framework.view.AnathemaView;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.reflections.DefaultAnathemaReflections;
import net.sf.anathema.initialization.reflections.ReflectionsInstantiater;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IExtensibleDataSetRegistry;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;
import java.util.Set;

public class AnathemaInitializer {

  private static final Logger logger = Logger.getLogger(AnathemaInitializer.class);
	
  private final IAnathemaPreferences anathemaPreferences;
  private final ItemTypeConfigurationCollection itemTypeCollection;
  private final AnathemaExtensionCollection extensionCollection;
  private final AnathemaReflections reflections;
  private final ReflectionsInstantiater instantiater;

  public AnathemaInitializer(IAnathemaPreferences anathemaPreferences)
          throws InitializationException {
    this.reflections = new DefaultAnathemaReflections(anathemaPreferences);
    this.instantiater = new ReflectionsInstantiater(reflections);
    this.itemTypeCollection = new ItemTypeConfigurationCollection(instantiater);
    this.extensionCollection = new AnathemaExtensionCollection(instantiater);
    this.anathemaPreferences = anathemaPreferences;
  }

  public IAnathemaView initialize() throws InitializationException {
	AnathemaResources resources = initResources();
    initializePlugins(reflections);
    initializeExtensibleResources(reflections, resources);
    ProxySplashscreen.getInstance().displayVersion("v" + resources.getString("Anathema.Version.Numeric")); //$NON-NLS-1$//$NON-NLS-2$
    CentralExceptionHandling.setHandler(new CentralExceptionHandler(resources));
    IAnathemaModel anathemaModel = initModel(resources);
    IAnathemaView view = initView(resources);
    new AnathemaPresenter(anathemaModel, view, resources, itemTypeCollection.getItemTypes(), instantiater).initPresentation();
    return view;
  }

  private void initializePlugins(AnathemaReflections reflections) throws InitializationException {
    Collection<Startable> startablePlugins = instantiater.instantiateAll(Plugin.class);
    for (Startable startablePlugin : startablePlugins) {
      try {
        startablePlugin.doStart(reflections);
      } catch (Exception e) {
        throw new InitializationException("Failed to start plugin.", e);
      }
    }
  }
  
  private void initializeExtensibleResources(AnathemaReflections reflections, IExtensibleDataSetRegistry registry) throws InitializationException {
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
	Set<String> files = reflections.getResourcesMatching(compiler.getRecognitionPattern());
	logger.info(compiler.getName() + ": Found "+ files.size() +" data files.");
	for (String file : files) {
		compiler.registerFile(file, reflections.getClassLoaderForResource(file));
	}
  }

  private IAnathemaModel initModel(IResources resources) throws InitializationException {
    ProxySplashscreen.getInstance().displayStatusMessage("Creating Model..."); //$NON-NLS-1$
    return new AnathemaModelInitializer(
            anathemaPreferences,
            itemTypeCollection.getItemTypes(),
            extensionCollection, instantiater).initializeModel(resources);
  }

  private IAnathemaView initView(IResources resources) {
    ProxySplashscreen.getInstance().displayStatusMessage("Building View..."); //$NON-NLS-1$
    AnathemaViewProperties viewProperties = new AnathemaViewProperties(resources, anathemaPreferences.initMaximized());
    return new AnathemaView(viewProperties);
  }

  private AnathemaResources initResources() {
    AnathemaResources resources = new AnathemaResources();
    ProxySplashscreen.getInstance().displayStatusMessage("Loading Resources..."); //$NON-NLS-1$
    Set<String> resourcesInPaths = reflections.getResourcesMatching(".*\\.properties");
    for (String resource : resourcesInPaths) {
      resources.addResourceBundle(toBundleName(resource), reflections.getClassLoaderForResource(resource));
    }
    return resources;
  }

  private String toBundleName(String input) {
      String resourceName = input.replace("/", ".").replace(".properties", "");
      boolean isInternationalizationFile = resourceName.matches(".*_..");
      if (isInternationalizationFile) {
        return resourceName.substring(0, resourceName.lastIndexOf("_"));
      } else {
        return resourceName;
      }
  }
}
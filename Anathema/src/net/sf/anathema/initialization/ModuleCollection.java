package net.sf.anathema.initialization;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.campaign.module.CampaignModule;
import net.sf.anathema.cascades.module.CharmCascadesModule;
import net.sf.anathema.character.impl.module.CharacterCoreModule;
import net.sf.anathema.character.impl.module.CharacterModule;
import net.sf.anathema.framework.module.AnathemaCoreModule;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.initialization.modules.IModuleCollection;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.logging.Logger;

public class ModuleCollection implements IModuleCollection {

  private Logger logger = Logger.getLogger(ModuleCollection.class);
  private IAnathemaModule[] allModules;

  private IAnathemaModule[] getAllModules() {
    if (allModules == null) {
      allModules = collectModules();
    }
    return allModules;
  }

  public void forAllDo(IClosure<IAnathemaModule> closure) {
    for (IAnathemaModule module : getAllModules()) {
      closure.execute(module);
    }
  }

  private IAnathemaModule[] collectModules() {
//    PluginManager manager = ObjectFactory.newInstance().createManager();
//    try {
//      DefaultPluginsCollector collector = new DefaultPluginsCollector();
//      Enumeration<URL> systemResources = ClassLoader.getSystemResources("./plugin"); //$NON-NLS-1$
//      StringBuilder builder = new StringBuilder();
//      while (systemResources.hasMoreElements()) {
//        if (builder.length() > 0) {
//          builder.append(","); //$NON-NLS-1$
//        }
//        builder.append(systemResources.nextElement().getPath());
//      }
//      Properties properties = new Properties();
//      properties.put("org.java.plugin.boot.pluginsRepositories", builder.toString()); //$NON-NLS-1$
//      collector.configure(new ExtendedProperties(properties));
//      Collection<PluginLocation> collection = collector.collectPluginLocations();
//      PluginLocation[] locations = collection.toArray(new PluginLocation[collection.size()]);
//      manager.publishPlugins(locations);
//      ExtensionPoint point = manager.getRegistry().getExtensionPoint("net.sf.anathema.main", "MainModules"); //$NON-NLS-1$ //$NON-NLS-2$
//      Collection<Extension> connectedExtensions = point.getConnectedExtensions();
//      for (Extension extension : connectedExtensions) {
//        
//      }
//    }
//    catch (JpfException e) {
//      e.printStackTrace();
//    }
//    catch (MalformedURLException e) {
//      e.printStackTrace();
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }

    String[] classNames = new String[] {
        AnathemaCoreModule.class.getName(),
        CharacterCoreModule.class.getName(),
        CharacterModule.class.getName(),
        CampaignModule.class.getName(),
        CharmCascadesModule.class.getName(),
        "net.sf.anathema.gis.platform.GisModule", //$NON-NLS-1$
        "net.sf.anathema.campaign.music.impl.module.MusicDatabaseModule", //$NON-NLS-1$
        "net.sf.anathema.namegenerator.anathema.NameGeneratorModule" }; //$NON-NLS-1$
    List<IAnathemaModule> installedModules = new ArrayList<IAnathemaModule>();
    for (String className : classNames) {
      IAnathemaModule module = instantiateModule(className);
      if (module != null) {
        installedModules.add(module);
      }
    }
    return installedModules.toArray(new IAnathemaModule[0]);
  }

  private IAnathemaModule instantiateModule(String className) {
    try {
      Class< ? > moduleClass = Class.forName(className);
      return (IAnathemaModule) moduleClass.newInstance();
    }
    catch (ClassNotFoundException e) {
      logger.info("Module not installed: " + className); //$NON-NLS-1$
    }
    catch (Exception e) {
      logger.error("Error initializing module " + className, e); //$NON-NLS-1$
    }
    return null;
  }
}
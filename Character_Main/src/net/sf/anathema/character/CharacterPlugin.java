package net.sf.anathema.character;

import java.net.URL;
import java.util.Collection;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCompiler;
import net.sf.anathema.initialization.plugin.AnathemaPluginManager;
import net.sf.anathema.initialization.plugin.PluginUtilities;

import org.java.plugin.Plugin;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class CharacterPlugin extends Plugin {

  private static final String PARAM_PATH = "path"; //$NON-NLS-1$
  private static final String PARAM_RULES = "rules"; //$NON-NLS-1$
  private static final String PARAM_TYPE = "type"; //$NON-NLS-1$
  private static final String PARAM_LIST = "list"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_CHARM_LIST = "CharmList"; //$NON-NLS-1$
  private static final String CHARACTER_PLUGIN_ID = "net.sf.anathema.character"; //$NON-NLS-1$

  @SuppressWarnings("unchecked")
  @Override
  protected void doStart() throws Exception {
    ProxySplashscreen.getInstance().displayStatusMessage("Compiling Charm Sets..."); //$NON-NLS-1$
    AnathemaPluginManager manager = new AnathemaPluginManager(getManager());
    Collection<Extension> connectedExtensions = manager.getExtension(CHARACTER_PLUGIN_ID, EXTENSION_POINT_CHARM_LIST);
    CharmCompiler charmCompiler = new CharmCompiler();
    for (Extension extension : connectedExtensions) {
      for (Parameter listParameter : PluginUtilities.getParameters(extension, PARAM_LIST)) {
        Parameter typeParameter = listParameter.getSubParameter(PARAM_TYPE);
        Parameter rules = listParameter.getSubParameter(PARAM_RULES);
        Parameter path = listParameter.getSubParameter(PARAM_PATH);
        URL resource = getClass().getClassLoader().getResource(path.valueAsString());
        charmCompiler.registerCharmFile(typeParameter.valueAsString(), rules.valueAsString(), resource);
      }
    }
    charmCompiler.buildCharms();
  }

  @Override
  protected void doStop() throws Exception {
    // nothing to do
  }
}
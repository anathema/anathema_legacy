package net.sf.anathema.character;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCompiler;
import net.sf.anathema.initialization.plugin.AnathemaPluginManager;
import org.java.plugin.Plugin;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

import java.net.URL;
import java.text.MessageFormat;

import static java.text.MessageFormat.format;

public class CharacterPlugin extends Plugin {

  private static final String PARAM_PATH = "path"; //$NON-NLS-1$
  private static final String PARAM_RULES = "rules"; //$NON-NLS-1$
  private static final String PARAM_TYPE = "type"; //$NON-NLS-1$
  private static final String PARAM_LIST = "list"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_CHARM_LIST = "CharmList"; //$NON-NLS-1$
  private static final String PLUGIN_ID = "net.sf.anathema.character"; //$NON-NLS-1$

  @Override
  protected void doStart() throws Exception {
    ProxySplashscreen.getInstance().displayStatusMessage("Compiling Charm Sets...");
    AnathemaPluginManager manager = new AnathemaPluginManager(getManager());
    CharmCompiler charmCompiler = new CharmCompiler();
    for (Extension extension : manager.getExtension(PLUGIN_ID, EXTENSION_POINT_CHARM_LIST)) {
      for (Parameter listParameter : extension.getParameters(PARAM_LIST)) {
        Parameter typeParameter = listParameter.getSubParameter(PARAM_TYPE);
        Parameter rules = listParameter.getSubParameter(PARAM_RULES);
        Parameter path = listParameter.getSubParameter(PARAM_PATH);
        String typeString = typeParameter.valueAsString();
        String ruleString = rules.valueAsString();
        String pathString = path.valueAsString();
        URL resource = getClass().getClassLoader().getResource(pathString);
        if (resource == null) {
          throw new Exception(format("No resource found at {0} for {1}, {2}.", pathString, typeString, ruleString));
        }
        charmCompiler.registerCharmFile(typeString, ruleString, resource);
      }
    }
    charmCompiler.buildCharms();
  }

  @Override
  protected void doStop() throws Exception {
    // nothing to do
  }
}
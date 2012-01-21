package net.sf.anathema.character;

import static java.text.MessageFormat.format;

import java.net.URL;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.generic.framework.reflections.DefaultCharacterReflections;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCompiler;
import net.sf.anathema.initialization.plugin.AnathemaPluginManager;

import org.java.plugin.Plugin;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class CharacterPlugin extends Plugin {

  private static final String PARAM_PATH = "path"; //$NON-NLS-1$
  private static final String PARAM_RULES = "rules"; //$NON-NLS-1$
  private static final String PARAM_TYPE = "type"; //$NON-NLS-1$
  private static final String PARAM_LIST = "list"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_CHARM_LIST = "CharmList"; //$NON-NLS-1$
  private static final String PLUGIN_ID = "net.sf.anathema.character"; //$NON-NLS-1$
  private static final String Charm_File_Recognition_Pattern = "Charms.*\\.xml";
  //matches stuff like data/charms/solar/Charms_Solar_SecondEdition_Occult.xml
  //the pattern is data/charms/REST_OF_PATH/Charms_TYPE_EDITION_ANYTHING.xml
  private static final String Charm_Data_Extraction_Pattern = ".*/Charms_(.*?)_(.*?)(?:_.*)?\\.xml";

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
        registerCharmFile(charmCompiler, typeString, ruleString, pathString);
      }
    }
    DefaultCharacterReflections reflections = new DefaultCharacterReflections();
    getCharmFilesFromReflection(reflections, charmCompiler);
    charmCompiler.buildCharms();
  }

  private void getCharmFilesFromReflection(DefaultCharacterReflections reflections, CharmCompiler charmCompiler) throws Exception {
    Set<String> charmFiles = reflections.getResourcesMatching(Charm_File_Recognition_Pattern);
    System.out.println(charmFiles);
    Pattern pattern = Pattern.compile(Charm_Data_Extraction_Pattern);
    for (String charmFile : charmFiles) {
      Matcher matcher = pattern.matcher(charmFile);
      matcher.matches();
      String typeString = matcher.group(1);
      String ruleString = matcher.group(2);
      registerCharmFile(charmCompiler, typeString, ruleString, charmFile);
    }
  }

  private void registerCharmFile(CharmCompiler charmCompiler, String typeString, String ruleString, String pathString) throws Exception {
    URL resource = getClass().getClassLoader().getResource(pathString);
    if (resource == null) {
      throw new Exception(format("No resource found at {0} for {1}, {2}.", pathString, typeString, ruleString));
    }
    charmCompiler.registerCharmFile(typeString, ruleString, resource);
  }

  @Override
  protected void doStop() throws Exception {
    // nothing to do
  }
}
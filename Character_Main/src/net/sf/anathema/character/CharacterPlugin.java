package net.sf.anathema.character;

import static java.text.MessageFormat.format;

import java.net.URL;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.xpath.internal.FoundIndex;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCompiler;
import net.sf.anathema.initialization.plugin.AnathemaPluginManager;

import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.reflections.DefaultAnathemaReflections;
import org.java.plugin.Plugin;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class CharacterPlugin extends Plugin {

  private static final String Charm_File_Recognition_Pattern = "Charms.*\\.xml";
  //matches stuff like data/charms/solar/Charms_Solar_SecondEdition_Occult.xml
  //the pattern is data/charms/REST_OF_PATH/Charms_TYPE_EDITION_ANYTHING.xml
  private static final String Charm_Data_Extraction_Pattern = ".*/Charms_(.*?)_(.*?)(?:_.*)?\\.xml";

  @Override
  protected void doStart() throws Exception {
    ProxySplashscreen.getInstance().displayStatusMessage("Compiling Charm Sets...");
    AnathemaPluginManager manager = new AnathemaPluginManager(getManager());
    CharmCompiler charmCompiler = new CharmCompiler();
    AnathemaReflections reflections = new DefaultAnathemaReflections();
    getCharmFilesFromReflection(reflections, charmCompiler);
    charmCompiler.buildCharms();
  }

  private void getCharmFilesFromReflection(AnathemaReflections reflections, CharmCompiler charmCompiler) throws Exception {
    Set<String> charmFiles = reflections.getResourcesMatching(Charm_File_Recognition_Pattern);
    System.out.println("Found "+ charmFiles.size() +" data files:");
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
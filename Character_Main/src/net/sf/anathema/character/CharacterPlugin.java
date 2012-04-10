package net.sf.anathema.character;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCompiler;
import net.sf.anathema.character.generic.impl.magic.persistence.SpellCache;
import net.sf.anathema.character.generic.impl.magic.persistence.SpellCompiler;
import net.sf.anathema.initialization.Plugin;
import net.sf.anathema.initialization.Startable;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IExtensibleDataSetRegistry;

import java.net.URL;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.text.MessageFormat.format;

@Plugin
public class CharacterPlugin implements Startable {

  private static final Logger logger = Logger.getLogger(CharacterPlugin.class);

  private static final String Charm_File_Recognition_Pattern = "Charms.*\\.xml";
  //matches stuff like data/charms/solar/Charms_Solar_SecondEdition_Occult.xml
  //the pattern is data/charms/REST_OF_PATH/Charms_TYPE_EDITION_ANYTHING.xml
  private static final String Charm_Data_Extraction_Pattern = ".*/Charms_(.*?)_(.*?)(?:_.*)?\\.xml";
  private static final String Spell_File_Recognition_Pattern = "Spells.*\\.xml";

  @Override
  public void doStart(AnathemaReflections reflections, IExtensibleDataSetRegistry registry) throws Exception {
    ProxySplashscreen.getInstance().displayStatusMessage("Compiling Charm Sets...");
    CharmCache charms = new CharmCache();
    SpellCache spells = new SpellCache();
    CharmCompiler charmCompiler = new CharmCompiler(charms);
    SpellCompiler spellCompiler = new SpellCompiler(spells);
    getCharmFilesFromReflection(reflections, charmCompiler);
    getSpellFilesFromReflection(reflections, spellCompiler);
    charmCompiler.buildCharms();
    spellCompiler.buildSpells();
    registry.addDataSet(charms);
    registry.addDataSet(spells);
  }

  private void getCharmFilesFromReflection(AnathemaReflections reflections, CharmCompiler charmCompiler) throws Exception {
    Set<String> charmFiles = reflections.getResourcesMatching(Charm_File_Recognition_Pattern);
    logger.info("Charms: Found "+ charmFiles.size() +" data files.");
    Pattern pattern = Pattern.compile(Charm_Data_Extraction_Pattern);
    for (String charmFile : charmFiles) {
      Matcher matcher = pattern.matcher(charmFile);
      matcher.matches();
      String typeString = matcher.group(1);
      String ruleString = matcher.group(2);
      registerCharmFile(charmCompiler, typeString, ruleString, charmFile);
    }
  }
  
  private void getSpellFilesFromReflection(AnathemaReflections reflections, SpellCompiler spellCompiler) throws Exception {
	Set<String> spellFiles = reflections.getResourcesMatching(Spell_File_Recognition_Pattern);
	logger.info("Spells: Found "+ spellFiles.size() +" data files.");
	for (String spellFile : spellFiles) {
	  registerSpellFile(spellCompiler, spellFile);
	}
  }

  private void registerCharmFile(CharmCompiler charmCompiler, String typeString, String ruleString, String pathString) throws Exception {
    URL resource = getClass().getClassLoader().getResource(pathString);
    if (resource == null) {
      throw new Exception(format("No resource found at {0} for {1}, {2}.", pathString, typeString, ruleString));
    }
    charmCompiler.registerCharmFile(typeString, resource);
  }
  
  private void registerSpellFile(SpellCompiler spellCompiler, String pathString) throws Exception {
	URL resource = getClass().getClassLoader().getResource(pathString);
	if (resource == null) {
	  throw new Exception(format("No resource found at {0}.", pathString));
	}
	spellCompiler.registerSpellFile(resource);
  }
}
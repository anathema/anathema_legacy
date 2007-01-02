package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.collection.Table;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.util.IIdentificate;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class CharmCache implements ICharmCache {

  private static final CharmCache instance = new CharmCache();
  private final Table<IIdentificate, IExaltedRuleSet, URL> charmFileTable = new Table<IIdentificate, IExaltedRuleSet, URL>();
  private final Map<IExaltedRuleSet, MultiEntryMap<IIdentificate, ICharm>> charmSetsByRuleSet = new HashMap<IExaltedRuleSet, MultiEntryMap<IIdentificate, ICharm>>();
  private final CharmIO charmIo = new CharmIO();
  private final ICharmSetBuilder builder = new CharmSetBuilder();

  private CharmCache() {
    for (IExaltedRuleSet ruleset : ExaltedRuleSet.values()) {
      charmSetsByRuleSet.put(ruleset, new MultiEntryMap<IIdentificate, ICharm>());
    }
  }

  public static CharmCache getInstance() {
    return instance;
  }

  private MultiEntryMap<IIdentificate, ICharm> getRulesetCharms(IExaltedRuleSet ruleset) {
    return charmSetsByRuleSet.get(ruleset);
  }

  public ICharm[] getCharms(IIdentificate type, IExaltedRuleSet ruleset) {
    List<ICharm> charmList = charmSetsByRuleSet.get(ruleset).get(type);
    return charmList.toArray(new ICharm[charmList.size()]);
  }

  // Necessary for connections between Charms from different documents/types
  public ICharm searchCharm(final String charmId, IExaltedRuleSet rules) {
    String[] idParts = charmId.split("\\."); //$NON-NLS-1$
    CharacterType characterTypeId = CharacterType.getById(idParts[0]);
    ICharm[] charms = getCharms(characterTypeId, rules);
    ICharm charm = ArrayUtilities.find(new Predicate<ICharm>() {
      public boolean evaluate(ICharm candidate) {
        return candidate.getId().equals(charmId);
      }
    }, charms);
    return charm;
  }

  public void registerCharmFile(String typeString, String ruleString, URL resource) {
    IIdentificate type;
    if (typeString.equals(MARTIAL_ARTS.getId())) {
      type = MARTIAL_ARTS;
    }
    else {
      type = CharacterType.getById(typeString);
    }
    ExaltedRuleSet ruleSet = ExaltedRuleSet.valueOf(ruleString);
    charmFileTable.add(type, ruleSet, resource);
  }

  public void buildCharms() throws PersistenceException {
    for (ExaltedRuleSet rules : ExaltedRuleSet.values()) {
      for (CharacterType type : CharacterType.values()) {
        buildCharms(type, rules);
      }
      buildCharms(MARTIAL_ARTS, rules);
    }
  }

  private void buildCharms(IIdentificate type, IExaltedRuleSet rules) throws PersistenceException {
    if (charmFileTable.contains(type, rules)) {
      buildOfficialCharms(type, rules);
    }
  }

  private void buildOfficialCharms(final IIdentificate type, IExaltedRuleSet rules) throws PersistenceException {
    try {
      Document charmDocument = new SAXReader().read(charmFileTable.get(type, rules));
      buildRulesetCharms(type, rules, charmDocument);
    }
    catch (DocumentException e) {
      throw new CharmException(e);
    }
  }

  private void buildRulesetCharms(final IIdentificate type, IExaltedRuleSet rules, Document charmDocument)
      throws PersistenceException {
    List<ICharm> existingCharms = new ArrayList<ICharm>();
    final IExaltedRuleSet basicRules = rules.getBasicRuleset();
    if (basicRules != null) {
      Collections.addAll(existingCharms, getCharms(type, basicRules));
    }
    ICharm[] charmArray = builder.buildCharms(charmDocument, existingCharms, rules);
    for (ICharm charm : charmArray) {
      addCharm(type, rules, charm);
    }
  }

  private void addCharm(IIdentificate type, IExaltedRuleSet rules, ICharm charm) {
    getRulesetCharms(rules).add(type, charm);
  }

  public void addCharm(ICharmEntryData charmData) throws IOException, DocumentException {
    ICharm charm = new Charm(charmData.getCoreData());
    addCharm(charm.getCharacterType(), charmData.getEdition().getDefaultRuleset(), charm);
    charmIo.writeCharmInternal(charmData);
  }
}
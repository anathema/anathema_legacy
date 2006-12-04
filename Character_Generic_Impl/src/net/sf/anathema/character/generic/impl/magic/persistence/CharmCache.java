package net.sf.anathema.character.generic.impl.magic.persistence;

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
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public class CharmCache implements ICharmCache {

  public static final Identificate MARTIAL_ARTS_TYPE = new Identificate("MartialArts"); //$NON-NLS-1$

  private static final CharmCache instance = new CharmCache();
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

  public ICharm[] getCharms(IIdentificate type, IExaltedRuleSet ruleset) throws PersistenceException {
    if (getRulesetCharms(ruleset).containsKey(type)) {
      return getCharmArray(type, ruleset);
    }
    buildOfficialCharms(type, ruleset);
    return getCharmArray(type, ruleset);
  }

  private ICharm[] getCharmArray(IIdentificate type, IExaltedRuleSet ruleset) {
    List<ICharm> charmList = getRulesetCharms(ruleset).get(type);
    return charmList.toArray(new ICharm[charmList.size()]);
  }

  private void buildOfficialCharms(final IIdentificate type, IExaltedRuleSet ruleset) throws PersistenceException {
    try {
      Document charmDocument = charmIo.readCharms(type, ruleset);
      buildRulesetCharms(type, ruleset, charmDocument);
    }
    catch (DocumentException e) {
      throw new CharmException(e);
    }
  }

  private void buildRulesetCharms(final IIdentificate type, IExaltedRuleSet set, Document charmDocument)
      throws PersistenceException {
    List<ICharm> existingCharms = new ArrayList<ICharm>();
    final IExaltedRuleSet basicRules = set.getBasicRuleset();
    if (basicRules != null) {
      Collections.addAll(existingCharms, getCharms(type, basicRules));
    }
    ICharm[] charmArray = builder.buildCharms(charmDocument, existingCharms, set);
    for (ICharm charm : charmArray) {
      getRulesetCharms(set).add(type, charm);
    }
  }

  // Necessary for connections between Charms from different documents/types
  public ICharm searchCharm(final String charmId, IExaltedRuleSet rules) {
    try {
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
    catch (PersistenceException e) {
      return null;
    }
  }

  public void addCharm(ICharmEntryData charmData) throws IOException, DocumentException {
    ICharm charm = new Charm(charmData.getCoreData());
    getRulesetCharms(charmData.getEdition().getDefaultRuleset()).add(charm.getCharacterType(), charm);
    charmIo.writeCharmInternal(charmData);
  }

  public void registerCharmFile(String typeString, String ruleString, URL resource) {
    IIdentificate type;
    if (typeString.equals(MARTIAL_ARTS_TYPE.getId())) {
      type = MARTIAL_ARTS_TYPE;
    }
    else {
      type = CharacterType.getById(typeString);
    }
    ExaltedRuleSet ruleSet = ExaltedRuleSet.valueOf(ruleString);
    charmIo.registerCharmFile(type, ruleSet, resource);
  }
}
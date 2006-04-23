package net.sf.anathema.character.generic.impl.magic.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.util.Identificate;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public class CharmCache implements ICharmCache {

  private static CharmCache instance = new CharmCache();
  private final Map<IExaltedRuleSet, MultiEntryMap<CharacterType, ICharm>> charmSetsByRuleSet = new HashMap<IExaltedRuleSet, MultiEntryMap<CharacterType, ICharm>>();
  private final List<ICharm> martialArtsCharms = new ArrayList<ICharm>();
  private List<ICharm> powerCombatMartialArtsCharms = new ArrayList<ICharm>();
  private final CharmIO charmIo = new CharmIO();
  private final CharmBuilder builder = new CharmBuilder();

  private CharmCache() {
    for (IExaltedRuleSet ruleset : ExaltedRuleSet.values()) {
      charmSetsByRuleSet.put(ruleset, new MultiEntryMap<CharacterType, ICharm>());
    }
  }

  public static CharmCache getInstance() {
    return instance;
  }

  private MultiEntryMap<CharacterType, ICharm> getRulesetCharms(IExaltedRuleSet ruleset) {
    return charmSetsByRuleSet.get(ruleset);
  }

  public ICharm[] getCharms(final CharacterType type, IExaltedRuleSet ruleset) throws PersistenceException {
    if (getRulesetCharms(ruleset).containsKey(type)) {
      return getCharmArray(type, ruleset);
    }
    buildOfficialCharms(type);
    buildCustomCharms(type);
    return getCharmArray(type, ruleset);
  }

  private ICharm[] getCharmArray(final CharacterType type, IExaltedRuleSet ruleset) {
    List<ICharm> charmList = getRulesetCharms(ruleset).get(type);
    return charmList.toArray(new ICharm[charmList.size()]);
  }

  private void buildOfficialCharms(final CharacterType type) throws PersistenceException {
    try {
      Document charmDocument = charmIo.readCharms(type);
      buildCharmsFromDocument(type, charmDocument);
    }
    catch (DocumentException e) {
      throw new CharmException(e);
    }
  }

  private void buildCustomCharms(final CharacterType type) throws PersistenceException {
    try {
      Document document = charmIo.readCustomCharms(type);
      if (document != null) {
        buildCharmsFromDocument(type, document);
      }
    }
    catch (DocumentException e) {
      throw new CharmException(e);
    }
  }

  private void buildCharmsFromDocument(final CharacterType type, Document charmDocument) throws PersistenceException {
    ICharm[] coreRulesCharmArray = builder.buildCharms(charmDocument, false);
    ICharm[] powerCombatCharmArray = builder.buildCharms(charmDocument, true);
    for (ICharm charm : coreRulesCharmArray) {
      getRulesetCharms(ExaltedRuleSet.CoreRules).add(type, charm);
    }
    for (ICharm charm : powerCombatCharmArray) {
      getRulesetCharms(ExaltedRuleSet.PowerCombat).add(type, charm);
    }
  }

  public ICharm[] getMartialArtsCharms(IExaltedRuleSet ruleset) throws PersistenceException {
    List<ICharm> charmList = getMartialArtsList(ruleset);
    if (charmList.isEmpty()) {
      try {
        Document charmDocument = charmIo.readCharms(new Identificate("MartialArts")); //$NON-NLS-1$
        charmList.addAll(Arrays.asList(builder.buildCharms(charmDocument, ruleset == ExaltedRuleSet.PowerCombat)));
      }
      catch (DocumentException e) {
        throw new CharmException(e);
      }
    }
    return charmList.toArray(new ICharm[charmList.size()]);
  }

  private List<ICharm> getMartialArtsList(IExaltedRuleSet ruleset) {
    if (ruleset == ExaltedRuleSet.PowerCombat) {
      return powerCombatMartialArtsCharms;
    }
    return martialArtsCharms;
  }

  public Charm searchCharm(final String charmId) {
    try {
      String[] idParts = charmId.split("\\."); //$NON-NLS-1$
      CharacterType characterTypeId = CharacterType.getById(idParts[0]);
      ICharm[] charms = getCharms(characterTypeId, ExaltedRuleSet.CoreRules);
      ICharm parentCharm = ArrayUtilities.find(new Predicate<ICharm>() {
        @Override
        public boolean evaluate(ICharm t) {
          return t.getId().equals(charmId);
        }
      }, charms);
      return (Charm) parentCharm;
    }
    catch (PersistenceException e) {
      return null;
    }
  }

  public void addCharm(ICharmData charmData, List<ICharmAttribute> keywords) throws IOException, DocumentException {
    ICharm charm = new Charm(charmData);
    getRulesetCharms(ExaltedRuleSet.CoreRules).add(charm.getCharacterType(), charm);
    charmIo.writeCharmInternal(charm, keywords);
  }
}
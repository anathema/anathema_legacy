package net.sf.anathema.character.generic.impl.magic.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.util.IIdentificate;

public class CharmCache implements ICharmCache {

  private static final CharmCache instance = new CharmCache();
  private final Map<IExaltedRuleSet, MultiEntryMap<IIdentificate, ICharm>> charmSetsByRuleSet = new HashMap<IExaltedRuleSet, MultiEntryMap<IIdentificate, ICharm>>();

  private CharmCache() {
    for (IExaltedRuleSet ruleset : ExaltedRuleSet.values()) {
      charmSetsByRuleSet.put(ruleset, new MultiEntryMap<IIdentificate, ICharm>());
    }
  }

  public static CharmCache getInstance() {
    return instance;
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

  public void addCharm(IIdentificate type, IExaltedRuleSet rules, ICharm charm) {
    charmSetsByRuleSet.get(rules).add(type, charm);
  }

  public void addCharm(ICharmEntryData charmData) {
    ICharm charm = new Charm(charmData.getCoreData());
    addCharm(charm.getCharacterType(), charmData.getEdition().getDefaultRuleset(), charm);
  }
}
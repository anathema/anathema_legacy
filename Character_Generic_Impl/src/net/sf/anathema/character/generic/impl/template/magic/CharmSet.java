package net.sf.anathema.character.generic.impl.template.magic;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

public class CharmSet implements ICharmSet {

  private final Map<IExaltedRuleSet, ICharm[]> charmMap;
  private final Map<IExaltedRuleSet, ICharm[]> martialArtsCharmMap;

  public static ICharmSet createRegularCharmSet(ICharmCache charmProvider, CharacterType characterType)
      throws PersistenceException {
    Map<IExaltedRuleSet, ICharm[]> charmMap = createCharmTreeMap(charmProvider, characterType);
    Map<IExaltedRuleSet, ICharm[]> martialArtsCharmMap = new HashMap<IExaltedRuleSet, ICharm[]>();
    martialArtsCharmMap.put(ExaltedRuleSet.CoreRules, charmProvider.getMartialArtsCharms(false));
    martialArtsCharmMap.put(ExaltedRuleSet.PowerCombat, charmProvider.getMartialArtsCharms(true));
    return new CharmSet(charmMap, martialArtsCharmMap);
  }

  private static Map<IExaltedRuleSet, ICharm[]> createCharmTreeMap(
      ICharmCache charmProvider,
      CharacterType characterType) throws PersistenceException {
    Map<IExaltedRuleSet, ICharm[]> charmMap = new HashMap<IExaltedRuleSet, ICharm[]>();
    charmMap.put(ExaltedRuleSet.CoreRules, charmProvider.getCharms(characterType, false));
    charmMap.put(ExaltedRuleSet.PowerCombat, charmProvider.getCharms(characterType, true));
    return charmMap;
  }

  private CharmSet(Map<IExaltedRuleSet, ICharm[]> charmMap, Map<IExaltedRuleSet, ICharm[]> martialArtsCharmMap) {
    this.charmMap = charmMap;
    this.martialArtsCharmMap = martialArtsCharmMap;
  }

  public ICharm[] getCharms(IExaltedRuleSet set) {
    return charmMap.get(set);
  }

  public ICharm[] getMartialArtsCharms(IExaltedRuleSet set) {
    return martialArtsCharmMap.get(set);
  }
}
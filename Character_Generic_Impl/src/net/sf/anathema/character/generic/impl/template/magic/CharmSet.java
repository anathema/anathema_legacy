package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.HashMap;
import java.util.Map;

public class CharmSet implements ICharmSet {

  private final Map<IExaltedRuleSet, ICharm[]> charmMap;
  private final Map<IExaltedRuleSet, ICharm[]> martialArtsCharmMap;

  public static ICharmSet createRegularCharmSet(
      ICharmCache charmProvider,
      ICharacterType characterType,
      IExaltedEdition edition) {
    Map<IExaltedRuleSet, ICharm[]> charmMap = createCharmTreeMap(charmProvider, characterType, edition);
    Map<IExaltedRuleSet, ICharm[]> martialArtsCharmMap = createCharmTreeMap(
        charmProvider,
        MartialArtsUtilities.MARTIAL_ARTS,
        edition);
    return new CharmSet(charmMap, martialArtsCharmMap);
  }

  private static Map<IExaltedRuleSet, ICharm[]> createCharmTreeMap(
      ICharmCache charmProvider,
      IIdentificate characterType,
      IExaltedEdition edition) {
    Map<IExaltedRuleSet, ICharm[]> charmMap = new HashMap<IExaltedRuleSet, ICharm[]>();
    for (IExaltedRuleSet set : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
      charmMap.put(set, charmProvider.getCharms(characterType));
    }
    return charmMap;
  }

  private CharmSet(Map<IExaltedRuleSet, ICharm[]> charmMap, Map<IExaltedRuleSet, ICharm[]> martialArtsCharmMap) {
    this.charmMap = charmMap;
    this.martialArtsCharmMap = martialArtsCharmMap;
  }

  @Override
  public ICharm[] getCharms(IExaltedRuleSet set) {
    return charmMap.get(set);
  }

  @Override
  public ICharm[] getMartialArtsCharms(IExaltedRuleSet set) {
    return martialArtsCharmMap.get(set);
  }
}
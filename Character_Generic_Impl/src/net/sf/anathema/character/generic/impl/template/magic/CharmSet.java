package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet.SecondEdition;

public class CharmSet implements ICharmSet {

  private final Map<IExaltedRuleSet, ICharm[]> charmMap;
  private final Map<IExaltedRuleSet, ICharm[]> uniqueCharmMap;
  private final Map<IExaltedRuleSet, ICharm[]> martialArtsCharmMap;

  public static ICharmSet createRegularCharmSet(
      ICharmCache charmProvider,
      ICharacterType characterType,
      IUniqueCharmType uniqueType,
      IExaltedEdition edition) {
    Map<IExaltedRuleSet, ICharm[]> charmMap = createCharmTreeMap(charmProvider, characterType, edition);
    Map<IExaltedRuleSet, ICharm[]> uniqueCharmMap = null;
    Map<IExaltedRuleSet, ICharm[]> martialArtsCharmMap = createCharmTreeMap(
        charmProvider,
        MartialArtsUtilities.MARTIAL_ARTS,
        edition);
    if (uniqueType != null) {
    	 uniqueCharmMap = createCharmTreeMap(
    	            charmProvider,
    	            uniqueType.getId(),
    	            edition);
    }
    return new CharmSet(charmMap, uniqueCharmMap, martialArtsCharmMap);
  }

  private static Map<IExaltedRuleSet, ICharm[]> createCharmTreeMap(ICharmCache charmProvider,
                                                                   IIdentificate characterType) {
    Map<IExaltedRuleSet, ICharm[]> charmMap = new HashMap<IExaltedRuleSet, ICharm[]>();
    charmMap.put(SecondEdition, charmProvider.getCharms(characterType));
    return charmMap;
  }

  private CharmSet(Map<IExaltedRuleSet, ICharm[]> charmMap,
		  		   Map<IExaltedRuleSet, ICharm[]> uniqueCharmMap,
		           Map<IExaltedRuleSet, ICharm[]> martialArtsCharmMap) {
    this.charmMap = charmMap;
    this.uniqueCharmMap = uniqueCharmMap;
    this.martialArtsCharmMap = martialArtsCharmMap;
  }

  @Override
  public ICharm[] getCharms(IExaltedRuleSet set) {
    return charmMap.get(set);
  }
  
  @Override
  public ICharm[] getUniqueCharms(IExaltedRuleSet set) {
    return uniqueCharmMap.get(set);
  }

  @Override
  public ICharm[] getMartialArtsCharms(IExaltedRuleSet set) {
    return martialArtsCharmMap.get(set);
  }
}
package net.sf.anathema.character.generic.impl.template.magic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharmProvider implements ICharmProvider {

  private final Map<IExaltedEdition, Map<CharacterType, ISpecialCharm[]>> charmsByTypeByRuleSet = new HashMap<IExaltedEdition, Map<CharacterType, ISpecialCharm[]>>();

  public CharmProvider() {
    for (IExaltedEdition edition : ExaltedEdition.values()) {
      charmsByTypeByRuleSet.put(edition, new HashMap<CharacterType, ISpecialCharm[]>());
    }
  }

  public ISpecialCharm[] getAllSpecialCharms(IExaltedEdition edition) {
    List<ISpecialCharm> list = new ArrayList<ISpecialCharm>();
    for (CharacterType type : CharacterType.getAllCharacterTypes()) {
      Collections.addAll(list, getSpecialCharms(type, edition));
    }
    return list.toArray(new ISpecialCharm[list.size()]);
  }

  public ISpecialCharm[] getSpecialCharms(CharacterType characterType, IExaltedEdition edition) {
    ISpecialCharm[] specialCharms = charmsByTypeByRuleSet.get(edition).get(characterType);
    if (specialCharms == null) {
      return new ISpecialCharm[0];
    }
    return specialCharms;
  }

  public void setSpecialCharms(CharacterType type, IExaltedEdition edition, ISpecialCharm[] charms) {
    charmsByTypeByRuleSet.get(edition).put(type, charms);
  }
}
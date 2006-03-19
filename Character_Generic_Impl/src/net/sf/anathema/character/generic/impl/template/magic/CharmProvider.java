package net.sf.anathema.character.generic.impl.template.magic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharmProvider implements ICharmProvider {

  private final Map<CharacterType, ISpecialCharm[]> specialCharmsByCharacterType = new HashMap<CharacterType, ISpecialCharm[]>();

  public ISpecialCharm[] getAllSpecialCharms() {
    List<ISpecialCharm> list = new ArrayList<ISpecialCharm>();
    for (CharacterType type : CharacterType.values()) {
      Collections.addAll(list, getSpecialCharms(type));
    }
    return list.toArray(new ISpecialCharm[list.size()]);
  }

  public ISpecialCharm[] getSpecialCharms(CharacterType characterType) {
    ISpecialCharm[] specialCharms = specialCharmsByCharacterType.get(characterType);
    if (specialCharms == null) {
      return new ISpecialCharm[0];
    }
    return specialCharms;
  }

  public void setSpecialCharms(CharacterType type, ISpecialCharm[] charms) {
    specialCharmsByCharacterType.put(type, charms);
  }
}
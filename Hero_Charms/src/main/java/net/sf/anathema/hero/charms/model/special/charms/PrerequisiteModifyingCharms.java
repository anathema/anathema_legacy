package net.sf.anathema.hero.charms.model.special.charms;

import com.google.common.collect.Lists;

import java.util.List;

public class PrerequisiteModifyingCharms {
  private final List<IPrerequisiteModifyingCharm> prerequisiteModifyingCharms = Lists.newArrayList();

  public PrerequisiteModifyingCharms(ISpecialCharm[] specialCharms) {
    addAllPrerequisiteModifiersForCharacterType(specialCharms);
  }

  public Iterable<IPrerequisiteModifyingCharm> getPrerequisiteModifyingCharms() {
    return prerequisiteModifyingCharms;
  }

  private void addAllPrerequisiteModifiersForCharacterType(ISpecialCharm[] specialCharms) {
    for (ISpecialCharm charm : specialCharms) {
      if (!(charm instanceof IPrerequisiteModifyingCharm)) {
        continue;
      }
      prerequisiteModifyingCharms.add((IPrerequisiteModifyingCharm) charm);
    }
  }
}
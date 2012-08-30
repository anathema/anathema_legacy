package net.sf.anathema.character.impl.model.charm;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.type.ICharacterType;

import java.util.List;

public class PrerequisiteModifyingCharms {
  private final ICharacterModelContext context;
  private final ICharmIdMap charmIdMap;
  private final ISpecialCharm[] specialCharms;
  private final List<IPrerequisiteModifyingCharm> prerequisiteModifyingCharms = Lists.newArrayList();

  public PrerequisiteModifyingCharms(ICharacterModelContext context, ICharmIdMap charmIdMap,
                                     ISpecialCharm[] specialCharms) {
    this.context = context;
    this.charmIdMap = charmIdMap;
    this.specialCharms = specialCharms;
    addAllPrerequisiteModifiersForCharacterType();
  }

  public Iterable<IPrerequisiteModifyingCharm> getPrerequisiteModifyingCharms() {
    return prerequisiteModifyingCharms;
  }

  private void addAllPrerequisiteModifiersForCharacterType() {
    for (ISpecialCharm charm : specialCharms) {
      if (!(charm instanceof IPrerequisiteModifyingCharm)) {
        continue;
      }
      if (!isCharmForCharacterType(charm)) {
        continue;
      }
      prerequisiteModifyingCharms.add((IPrerequisiteModifyingCharm) charm);
    }
  }

  private boolean isCharmForCharacterType(ISpecialCharm charm) {
    ICharm originalCharm = charmIdMap.getCharmById(charm.getCharmId());
    ICharacterType actualCharacterType = context.getBasicCharacterContext().getCharacterType();
    return originalCharm.getCharacterType() == actualCharacterType;
  }
}
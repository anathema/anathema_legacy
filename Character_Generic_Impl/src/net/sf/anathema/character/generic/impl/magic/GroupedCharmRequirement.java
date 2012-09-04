package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.impl.magic.charm.prerequisite.SelectiveCharmGroup;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;

public class GroupedCharmRequirement implements IndirectCharmRequirement{
  private final SelectiveCharmGroup charmGroup;

  public GroupedCharmRequirement(SelectiveCharmGroup charmGroup) {
    this.charmGroup = charmGroup;
  }

  @Override
  public String getStringRepresentation() {
    return charmGroup.getLabel();
  }

  @Override
  public boolean isFulfilled(ICharm[] learnedCharms) {
    ICharmLearnArbitrator arbitrator = new StaticCharmLearnArbitrator(learnedCharms);
    return charmGroup.holdsThreshold(arbitrator);
  }
}
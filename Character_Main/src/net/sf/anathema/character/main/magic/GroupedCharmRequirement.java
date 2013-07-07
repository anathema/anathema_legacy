package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.magic.charm.prerequisite.SelectiveCharmGroup;
import net.sf.anathema.character.main.magic.charms.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;

public class GroupedCharmRequirement implements IndirectCharmRequirement {
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
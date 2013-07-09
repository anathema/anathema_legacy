package net.sf.anathema.character.main.magic.model.charmtree;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.prerequisite.SelectiveCharmGroup;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;

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
  public boolean isFulfilled(Charm[] learnedCharms) {
    ICharmLearnArbitrator arbitrator = new StaticCharmLearnArbitrator(learnedCharms);
    return charmGroup.holdsThreshold(arbitrator);
  }
}
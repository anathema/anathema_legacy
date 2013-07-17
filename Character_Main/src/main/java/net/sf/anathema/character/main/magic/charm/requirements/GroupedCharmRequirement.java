package net.sf.anathema.character.main.magic.charm.requirements;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;

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
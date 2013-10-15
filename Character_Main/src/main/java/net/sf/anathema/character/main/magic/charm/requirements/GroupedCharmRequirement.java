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
  public boolean isFulfilled(Charm[] charms) {
    ICharmLearnArbitrator arbitrator = new StaticCharmLearnArbitrator(charms);
    return charmGroup.holdsThreshold(arbitrator);
  }
  
  public Charm[] getGroupCharms() {
	  return charmGroup.getAllGroupCharms();
  }
  
  public int getThreshold() {
	  return charmGroup.getThreshold();
  }
  
  public String getLabel() {
	  return charmGroup.getLabel();
  }
  
  public boolean hasLabel() {
	  return getLabel() != null;
  }
}
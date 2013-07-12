package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.model.magic.Magic;

import java.util.Set;

public interface Charm extends CharmData, Magic {

  Set<Charm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator);

  Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator);

  boolean isBlockedByAlternative(ICharmLearnArbitrator  learnArbitrator);

  Set<Charm> getLearnChildCharms();

  Set<Charm> getMergedCharms();
  
  boolean isTreeRoot();

  Set<Charm> getRenderingPrerequisiteCharms();
  
  Set<IndirectCharmRequirement> getIndirectRequirements();
}
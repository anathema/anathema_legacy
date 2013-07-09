package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.MagicCollection;
import net.sf.anathema.lib.util.Identifier;

import java.util.Set;

public interface Charm extends CharmData, Magic {

  Set<Charm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator);
  
  Set<Charm> getLearnChildCharms();

  Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator);

  boolean isBlockedByAlternative(MagicCollection magicCollection);
  
  Set<Charm> getMergedCharms();
  
  boolean isTreeRoot();

  Set<Charm> getRenderingPrerequisiteCharms();
  
  Set<IndirectCharmRequirement> getIndirectRequirements();

  boolean hasAttribute(Identifier attribute);
  
  String getAttributeValue(Identifier attribute);
}
package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.lib.util.Identified;

import java.util.Set;

public interface ICharm extends ICharmData, IMagic {

  Set<ICharm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator);
  
  Set<ICharm> getLearnChildCharms();

  Set<ICharm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator);

  boolean isBlockedByAlternative(IMagicCollection magicCollection);
  
  Set<ICharm> getMergedCharms();
  
  boolean isTreeRoot();

  Set<ICharm> getRenderingPrerequisiteCharms();
  
  Set<IndirectCharmRequirement> getIndirectRequirements();

  boolean hasAttribute(Identified attribute);
  
  String getAttributeValue(Identified attribute);
}
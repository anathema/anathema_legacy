package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.MagicCollection;
import net.sf.anathema.lib.util.Identifier;

import java.util.Set;

public interface ICharm extends ICharmData, IMagic {

  Set<ICharm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator);
  
  Set<ICharm> getLearnChildCharms();

  Set<ICharm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator);

  boolean isBlockedByAlternative(MagicCollection magicCollection);
  
  Set<ICharm> getMergedCharms();
  
  boolean isTreeRoot();

  Set<ICharm> getRenderingPrerequisiteCharms();
  
  Set<IndirectCharmRequirement> getIndirectRequirements();

  boolean hasAttribute(Identifier attribute);
  
  String getAttributeValue(Identifier attribute);
}
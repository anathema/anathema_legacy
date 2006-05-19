package net.sf.anathema.character.generic.magic;

import java.util.Set;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharm extends ICharmData, IMagic {

  public Set<ICharm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator);

  public Set<ICharm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator);

  public boolean isBlockedByAlternative(IMagicCollection magicCollection);

  public boolean isTreeRoot();

  public Set<ICharm> getRenderingPrerequisiteCharms();

  public boolean hasAttribute(IIdentificate attribute);

  public boolean hasChildren();
}
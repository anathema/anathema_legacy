package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharmConfiguration extends IExtendedCharmLearnableArbitrator, ICharmGroupArbitrator, ICharmIdMap {

  public void addLearnableListener(IChangeListener listener);

  public ILearningCharmGroup[] getAllGroups();

  public ICharacterType[] getCharacterTypes(boolean includeAlienTypes);

  public ICharmIdMap getCharmIdMap();

  public ICharm[] getCreationLearnedCharms();

  public ICharm[] getExperienceLearnedCharms();

  public ILearningCharmGroup[] getCharmGroups(IIdentificate type);

  /**
   * @param experienced If false, only charms learned at creation are returned. If true, the method returns the entirety
   *            of charms learned.
   */
  public ICharm[] getLearnedCharms(boolean experienced);

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(String charmId);

  public String[] getUncompletedCelestialMartialArtsGroups();

  public boolean isFullfilled(ICharmAttributeRequirement requirement);

  public boolean isLearnable(String charmId);

  public boolean isLearned(String charmId);

  public boolean isUnlearnable(String charmId);

  public void unlearnAllAlienCharms();

  public boolean isAlienCharm(ICharm charm);

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm);

  public ILearningCharmGroup getGroup(ICharm charm);

  public ILearningCharmGroup getGroup(String characterType, String groupName);

  public ISpecialCharm[] getSpecialCharms();
}
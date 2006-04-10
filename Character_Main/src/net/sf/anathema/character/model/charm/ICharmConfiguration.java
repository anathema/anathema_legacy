package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface ICharmConfiguration extends ICharmLearnableArbitrator {

  public void addLearnableListener(IChangeListener listener);

  public ILearningCharmGroup[] getAllGroups();

  public CharacterType[] getCharacterTypes(boolean includeAlienTypes);

  public ICharm getCharmById(String charmId);

  public ICharmTree getCharmTree(CharacterType type);

  public ICharm[] getCreationLearnedCharms();

  public ICharm[] getExperienceLearnedCharms();

  public ILearningCharmGroup[] getNonMartialArtsGroups(CharacterType characterType);

  /**
   * @param experienced If false, only charms learned at creation are returned. If true, the method returns the entirety
   *          of charms learned.
   */
  public ICharm[] getLearnedCharms(boolean experienced);

  public ILearningCharmGroup[] getMartialArtsGroups();

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(String charmId);

  public String[] getUncompletedCelestialMartialArtsGroups();

  public boolean isCelestialMartialArtsGroupCompleted();

  public boolean isFullfilled(ICharmAttributeRequirement requirement);

  public boolean isLearnable(String charmId);

  public boolean isLearned(String charmId);

  public boolean isUnlearnable(String charmId);

  public void unlearnAllAlienCharms();

  public boolean isAlienCharm(ICharm charm);

  public ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm);

  public ILearningCharmGroup getGroup(ICharm charm);

  public ILearningCharmGroup getGroup(String characterType, String groupName);

  public ICharmProvider getCharmProvider();
}
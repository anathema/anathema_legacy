package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.CharmFilterContainer;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identified;

public interface ICharmConfiguration extends CharmFilterContainer, IExtendedCharmLearnableArbitrator, ICharmGroupArbitrator, ICharmIdMap, SpecialCharmLearnArbitrator {

  void addLearnableListener(IChangeListener listener);

  ILearningCharmGroup[] getAllGroups();

  ICharacterType[] getCharacterTypes(boolean includeAlienTypes);

  ICharmIdMap getCharmIdMap();
  
  String getCharmTrueName(String charmName);

  ICharm[] getCreationLearnedCharms();

  ILearningCharmGroup[] getCharmGroups(Identified type);

  ICharm[] getLearnedCharms(boolean experienced);

  ISpecialCharmConfiguration getSpecialCharmConfiguration(String charmId);

  void unlearnAllAlienCharms();

  boolean isAlienCharm(ICharm charm);

  ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm);

  ILearningCharmGroup getGroup(ICharm charm);

  ILearningCharmGroup getGroup(String characterType, String groupName);

  ISpecialCharm[] getSpecialCharms();
}
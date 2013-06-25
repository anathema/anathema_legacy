package net.sf.anathema.character.main.model.charms;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.charm.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.charm.SpecialCharmLearnArbitrator;
import net.sf.anathema.charmtree.view.ICharmGroupArbitrator;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface CharmsModel extends HeroModel, IExtendedCharmLearnableArbitrator, ICharmGroupArbitrator, ICharmIdMap,
        SpecialCharmLearnArbitrator {

  Identifier ID = new SimpleIdentifier("Charms");

  void addLearnableListener(ChangeListener listener);

  ILearningCharmGroup[] getAllGroups();

  ICharacterType[] getCharacterTypes(boolean includeAlienTypes);

  ICharmIdMap getCharmIdMap();

  String getCharmTrueName(String charmName);

  ICharm[] getCreationLearnedCharms();

  ILearningCharmGroup[] getCharmGroups(Identifier type);

  ICharm[] getLearnedCharms(boolean experienced);

  ISpecialCharmConfiguration getSpecialCharmConfiguration(String charmId);

  void unlearnAllAlienCharms();

  boolean isAlienCharm(ICharm charm);

  ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm);

  ILearningCharmGroup getGroup(ICharm charm);

  ILearningCharmGroup getGroup(String characterType, String groupName);

  ISpecialCharm[] getSpecialCharms();
}
package net.sf.anathema.hero.charms;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.CharmIdMap;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.charm.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.character.main.charm.ILearningCharmGroup;
import net.sf.anathema.character.main.charm.SpecialCharmLearnArbitrator;
import net.sf.anathema.character.main.charmtree.view.CharmGroupArbitrator;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface CharmsModel extends HeroModel, IExtendedCharmLearnableArbitrator, CharmGroupArbitrator, CharmIdMap,
        SpecialCharmLearnArbitrator {

  Identifier ID = new SimpleIdentifier("Charms");

  void addLearnableListener(ChangeListener listener);

  ILearningCharmGroup[] getAllGroups();

  ICharacterType[] getCharacterTypes(boolean includeAlienTypes);

  CharmIdMap getCharmIdMap();

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
package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.charms.display.presenter.CharmGroupArbitrator;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charms.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.SpecialCharmLearnArbitrator;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface CharmsModel extends HeroModel, IExtendedCharmLearnableArbitrator, CharmGroupArbitrator, CharmIdMap,
        SpecialCharmLearnArbitrator, PrintMagicProvider {

  Identifier ID = new SimpleIdentifier("Charms");

  void addLearnableListener(ChangeListener listener);

  ILearningCharmGroup[] getAllGroups();

  CharacterType[] getCharacterTypes(boolean includeAlienTypes);

  CharmIdMap getCharmIdMap();

  ILearningCharmGroup[] getCharmGroups(Identifier type);

  Charm[] getLearnedCharms(boolean experienced);

  CharmSpecialsModel getSpecialCharmConfiguration(String charmId);

  void unlearnAllAlienCharms();

  boolean isAlienCharm(Charm charm);

  CharmSpecialsModel getCharmSpecialsModel(Charm charm);

  ILearningCharmGroup getGroup(Charm charm);

  ISpecialCharm[] getSpecialCharms();

  void addPrintProvider(PrintMagicProvider provider);

  void addLearnProvider(MagicLearner provider);

  MagicCreationCostEvaluator getMagicCostEvaluator();

  MartialArtsLevel getStandardMartialArtsLevel();
}
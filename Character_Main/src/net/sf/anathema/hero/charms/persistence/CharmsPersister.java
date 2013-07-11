package net.sf.anathema.hero.charms.persistence;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.persistence.charm.IdentifiedComparator;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.persistence.special.SpecialCharmListPersister;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.HeroModelPersisterCollected;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@HeroModelPersisterCollected
public class CharmsPersister extends AbstractModelJsonPersister<CharmListPto, CharmsModel> {

  public CharmsPersister() {
    super("charms", CharmListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return CharmsModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, CharmsModel model, CharmListPto pto) {
    CharmsModel charms = CharmsModelFetcher.fetch(hero);
    //    for (ComboPto comboPto : pto.combos) {
    //      loadCombo(charms, model, comboPto);
    //    }
  }

  @Override
  protected CharmListPto saveModelToPto(CharmsModel model) {
    CharmListPto pto = new CharmListPto();
    for (ILearningCharmGroup group : model.getAllGroups()) {
      saveGroupCharms(group, pto);
      saveGroupCharmSpecials(model, group, pto);
    }
    return pto;
  }

  private void saveGroupCharms(ILearningCharmGroup group, CharmListPto pto) {
    if (!group.hasLearnedCharms()) {
      return;
    }
    pto.groups.add(createGroupPto(group));
  }

  private CharmGroupPto createGroupPto(ILearningCharmGroup group) {
    CharmGroupPto groupPto = new CharmGroupPto();
    groupPto.name = group.getId();
    groupPto.type = group.getCharacterType().getId();
    saveCharms(group, groupPto);
    return groupPto;
  }

  private void saveCharms(ILearningCharmGroup group, CharmGroupPto groupPto) {
    Map<String, Boolean> isExperiencedLearned = getExperiencedLearnedMap(group);
    List<Charm> sortedCharmList = getSortedCharmList(group);
    for (Charm charm : sortedCharmList) {
      saveCharm(charm, isExperiencedLearned.get(charm.getId()), groupPto);
    }
  }

  private void saveCharm(Charm charm, boolean isExperienceLearned, CharmGroupPto groupPto) {
    CharmPto charmPto = new CharmPto();
    charmPto.name = charm.getId();
    charmPto.isExperienceLearned = isExperienceLearned;
    groupPto.charms.add(charmPto);
  }

  private void saveGroupCharmSpecials(CharmsModel model, ILearningCharmGroup group, CharmListPto pto) {
    for (Charm charm : getSortedCharmList(group)) {
      saveCharmSpecials(model, charm, pto);
    }
  }

  private void saveCharmSpecials(CharmsModel charmsModel, Charm charm, CharmListPto charmListPto) {
    SpecialCharmListPersister persister = new SpecialCharmListPersister(charmsModel);
    persister.saveCharmSpecials(charmsModel, charm, charmListPto);
  }

  private List<Charm> getSortedCharmList(ILearningCharmGroup group) {
    List<Charm> charms = new ArrayList<>();
    for (Charm charm : group.getCreationLearnedCharms()) {
      charms.add(charm);
    }
    for (Charm charm : group.getExperienceLearnedCharms()) {
      charms.add(charm);
    }
    Collections.sort(charms, new IdentifiedComparator());
    return charms;
  }

  private Map<String, Boolean> getExperiencedLearnedMap(ILearningCharmGroup group) {
    HashMap<String, Boolean> isExperiencedLearned = new HashMap<>();
    for (Charm charm : group.getCreationLearnedCharms()) {
      isExperiencedLearned.put(charm.getId(), false);
    }
    for (Charm charm : group.getExperienceLearnedCharms()) {
      isExperiencedLearned.put(charm.getId(), true);
    }
    return isExperiencedLearned;
  }
}

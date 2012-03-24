package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CharacterCharmModel {
  private ICharacterStatistics statistics;
  private MagicDescriptionProvider magicDescriptionProvider;

  public CharacterCharmModel(ICharacterStatistics statistics, MagicDescriptionProvider magicDescriptionProvider) {
    this.statistics = statistics;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public boolean isAllowedAlienCharms() {
    ICharmTemplate charmTemplate = statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate();
    return charmTemplate.isAllowedAlienCharms(getCaste().getType());
  }

  public IUniqueCharmType getUniqueCharmType() {
    ICharmTemplate charmTemplate = statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate();
    return charmTemplate.getUniqueCharmType();
  }

  public void addCasteChangeListener(IChangeListener listener) {
    ITypedDescription<ICasteType> caste = getCaste();
    caste.addChangeListener(listener);
  }

  public ICharmConfiguration getCharmConfiguration() {
    return statistics.getCharms();
  }

  private ITypedDescription<ICasteType> getCaste() {
    return statistics.getCharacterConcept().getCaste();
  }

  public void toggleLearned(String charmId) {
    ICharmConfiguration charms = getCharmConfiguration();
    ILearningCharmGroup charmGroup = getCharmGroupByCharmId(charmId);
    charmGroup.toggleLearned(charms.getCharmById(charmId));
  }

  private ILearningCharmGroup getCharmGroupByCharmId(String charmId) {
    ICharmConfiguration charms = getCharmConfiguration();
    ICharm charm = charms.getCharmById(charmId);
    return charms.getGroup(charm);
  }

  public boolean hasUniqueCharmType() {
    ICharmTemplate charmTemplate = statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate();
    return charmTemplate.hasUniqueCharms();
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }
}

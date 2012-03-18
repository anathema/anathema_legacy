package net.sf.anathema.character.presenter.charm.combo;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.description.CharmDescriptionProvider;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;

public class ComboConfigurationModel {

  private final ICharacterStatistics statistics;
  private final CharmDescriptionProvider charmDescriptionProvider;

  public ComboConfigurationModel(ICharacterStatistics statistics, CharmDescriptionProvider charmDescriptionProvider) {
    this.statistics = statistics;
    this.charmDescriptionProvider = charmDescriptionProvider;
  }

  public boolean isAlienCharmsAllowed() {
    ICasteType caste = statistics.getCharacterConcept().getCaste().getType();
    return statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(caste);
  }

  public ICharmConfiguration getCharmConfiguration() {
    return statistics.getCharms();
  }

  public IComboConfiguration getCombos() {
    return statistics.getCombos();
  }

  public CharmDescriptionProvider getCharmDescriptionProvider() {
    return charmDescriptionProvider;
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    statistics.getCharacterContext().getCharacterListening().addChangeListener(listener);
  }

  public ICharm[] getLearnedCharms() {
    return statistics.getCharms().getLearnedCharms(statistics.isExperienced());
  }

  public boolean isExperienced() {
    return statistics.isExperienced();
  }
}

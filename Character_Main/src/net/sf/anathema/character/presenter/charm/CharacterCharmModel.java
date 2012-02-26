package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CharacterCharmModel {
  private ICharacterStatistics statistics;

  public CharacterCharmModel(ICharacterStatistics statistics) {
    this.statistics = statistics;
  }

  public boolean isAllowedAlienCharms() {
    ICharmTemplate charmTemplate = statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate();
    return charmTemplate.isAllowedAlienCharms(getCaste().getType());
  }

  public void addCasteChangeListener(IChangeListener listener) {
    ITypedDescription<ICasteType> caste = getCaste();
    caste.addChangeListener(listener);
  }

  public IExaltedEdition getEdition() {
    return statistics.getRules().getEdition();
  }

  public ICharmConfiguration getCharmConfiguration() {
    return statistics.getCharms();
  }

  private ITypedDescription<ICasteType> getCaste() {
    return statistics.getCharacterConcept().getCaste();
  }
}
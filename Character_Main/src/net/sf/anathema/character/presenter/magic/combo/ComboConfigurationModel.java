package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.charm.CharmModel;
import net.sf.anathema.character.model.charm.IComboConfiguration;

public class ComboConfigurationModel {

  private final ICharacter character;
  private final MagicDescriptionProvider magicDescriptionProvider;

  public ComboConfigurationModel(ICharacter character, MagicDescriptionProvider magicDescriptionProvider) {
    this.character = character;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public boolean isAlienCharmsAllowed() {
    CasteType caste = HeroConceptFetcher.fetch(character).getCaste().getType();
    return character.getTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(caste);
  }

  public CharmModel getCharmConfiguration() {
    return character.getCharms();
  }

  public IComboConfiguration getCombos() {
    return character.getCombos();
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    character.getCharacterContext().getCharacterListening().addChangeListener(listener);
  }

  public ICharm[] getLearnedCharms() {
    return character.getCharms().getLearnedCharms(ExperienceModelFetcher.fetch(character).isExperienced());
  }

  public boolean isExperienced() {
    return ExperienceModelFetcher.fetch(character).isExperienced();
  }
}

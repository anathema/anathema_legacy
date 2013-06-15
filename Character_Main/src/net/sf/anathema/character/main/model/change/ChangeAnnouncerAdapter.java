package net.sf.anathema.character.main.model.change;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.main.concept.model.ConceptChange;
import net.sf.anathema.character.main.experience.model.ExperienceChange;
import net.sf.anathema.character.main.experience.model.ExperienceModel;
import net.sf.anathema.character.model.Hero;

public class ChangeAnnouncerAdapter implements ChangeAnnouncer {

  private CharacterListening listening;
  private Hero hero;

  public ChangeAnnouncerAdapter(CharacterListening listening, Hero hero) {
    this.listening = listening;
    this.hero = hero;
  }

  @Override
  public void announceChangeOf(ChangeFlavor flavor) {
    if (flavor == ConceptChange.FLAVOR_CASTE) {
      listening.fireCasteChanged();
    } else if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
      ExperienceModel experienceModel = hero.getModel(ExperienceModel.ID);
      listening.fireExperiencedChanged(experienceModel.isExperienced());
    } else {
      listening.fireCharacterChanged();
    }
  }
}

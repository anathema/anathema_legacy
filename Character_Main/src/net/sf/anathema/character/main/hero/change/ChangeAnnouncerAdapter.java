package net.sf.anathema.character.main.hero.change;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.model.concept.ConceptChange;
import net.sf.anathema.character.main.model.experience.ExperienceChange;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.traits.TraitChangeFlavor;
import org.jmock.example.announcer.Announcer;

public class ChangeAnnouncerAdapter implements ChangeAnnouncer {

  private final Announcer<FlavoredChangeListener> changeControl = Announcer.to(FlavoredChangeListener.class);
  private CharacterListening listening;
  private Hero hero;

  public ChangeAnnouncerAdapter(CharacterListening listening, Hero hero) {
    this.listening = listening;
    this.hero = hero;
  }

  @Override
  public void addListener(FlavoredChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public void announceChangeOf(ChangeFlavor flavor) {
    if (flavor == ConceptChange.FLAVOR_CASTE) {
      listening.fireCasteChanged();
    } else if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
      ExperienceModel experienceModel = (ExperienceModel) hero.getModel(ExperienceModel.ID);
      listening.fireExperiencedChanged(experienceModel.isExperienced());
    } else if (flavor instanceof TraitChangeFlavor) {
      TraitChangeFlavor traitChangeFlavor = (TraitChangeFlavor) flavor;
      listening.fireTraitChanged(traitChangeFlavor.getTraitType());
    }
    listening.fireCharacterChanged();
    changeControl.announce().changeOccurred(flavor);
  }
}

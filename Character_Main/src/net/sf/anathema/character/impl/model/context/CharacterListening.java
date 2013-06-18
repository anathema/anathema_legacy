package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.change.FlavoredChangeListener;
import net.sf.anathema.character.main.model.concept.ConceptChange;
import net.sf.anathema.character.main.model.experience.ExperienceChange;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.traits.TraitChangeFlavor;
import org.jmock.example.announcer.Announcer;

public class CharacterListening implements ICharacterListening, ChangeAnnouncer {

  private final Announcer<FlavoredChangeListener> changeControl = Announcer.to(FlavoredChangeListener.class);
  private Hero hero;

  public CharacterListening(Hero hero) {
    this.hero = hero;
  }

  @Override
  public void addChangeListener(final ICharacterChangeListener changeListener) {
    changeControl.addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ConceptChange.FLAVOR_CASTE) {
          changeListener.casteChanged();
        } else if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          ExperienceModel experienceModel = (ExperienceModel) hero.getModel(ExperienceModel.ID);
          changeListener.experiencedChanged(experienceModel.isExperienced());
        } else if (flavor instanceof TraitChangeFlavor) {
          TraitChangeFlavor traitChangeFlavor = (TraitChangeFlavor) flavor;
          changeListener.traitChanged(traitChangeFlavor.getTraitType());
        }
        changeListener.changeOccurred();
      }
    });
  }

  public void fireCharacterChanged() {
    changeControl.announce().changeOccurred(ChangeFlavor.UNSPECIFIED);
  }

  public void fireCasteChanged() {
    changeControl.announce().changeOccurred(ConceptChange.FLAVOR_CASTE);
  }

  public void fireExperiencedChanged(boolean isExperienced) {
    changeControl.announce().changeOccurred(ExperienceChange.FLAVOR_EXPERIENCE_STATE);
  }

  public void fireTraitChanged(TraitType traitType) {
    changeControl.announce().changeOccurred(new TraitChangeFlavor(traitType));
  }

  @Override
  public void addListener(FlavoredChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public void announceChangeOf(ChangeFlavor flavor) {
    changeControl.announce().changeOccurred(flavor);
  }
}
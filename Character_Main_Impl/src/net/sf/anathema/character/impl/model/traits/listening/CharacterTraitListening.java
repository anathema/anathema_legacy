package net.sf.anathema.character.impl.model.traits.listening;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.specialty.ISpecialty;
import net.sf.anathema.character.library.trait.specialty.ISpecialtyListener;
import net.sf.anathema.character.model.background.IBackgroundListener;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class CharacterTraitListening {

  private final CharacterListening listening;
  private final ICoreTraitConfiguration traitConfiguration;

  public CharacterTraitListening(ICoreTraitConfiguration traitConfiguration, CharacterListening listening) {
    this.traitConfiguration = traitConfiguration;
    this.listening = listening;
  }

  public void initListening() {
    for (ITrait attribute : traitConfiguration.getTraits(AttributeType.values())) {
      listening.addTraitListening(attribute);
    }
    initAbilityListening();
    initBackgroundListening();
    for (ITrait virtue : traitConfiguration.getTraits(VirtueType.values())) {
      listening.addTraitListening(virtue);
    }
    listening.addTraitListening(traitConfiguration.getTrait(OtherTraitType.Willpower));
    listening.addTraitListening(traitConfiguration.getTrait(OtherTraitType.Essence));
  }

  private void initBackgroundListening() {
    traitConfiguration.getBackgrounds().addBackgroundListener(new IBackgroundListener() {
      public void backgroundRemoved(ITrait background) {
        listening.removeTraitListening(background);
        listening.fireCharacterChanged();
      }

      public void backgroundAdded(ITrait background) {
        listening.addTraitListening(background);
        listening.fireCharacterChanged();
      }
    });
  }

  private void initAbilityListening() {
    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
    for (IIdentifiedTraitTypeGroup group : traitConfiguration.getAbilityTypeGroups()) {
      Collections.addAll(abilityTypes, group.getAllGroupTypes());
    }
    for (IFavorableTrait ability : traitConfiguration.getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]))) {
      listening.addTraitListening(ability);
      ability.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
        public void favorableStateChanged(FavorableState state) {
          listening.fireCharacterChanged();
        }
      });
      ability.getSpecialtiesContainer().addSpecialtyListener(new ISpecialtyListener() {
        public void specialtyRemoved(ISpecialty specialty) {
          listening.fireCharacterChanged();
        }

        public void specialtyAdded(ISpecialty specialty) {
          listening.fireCharacterChanged();
        }

        public void specialtyValueChanged() {
          listening.fireCharacterChanged();
        }
      });
    }
  }
}
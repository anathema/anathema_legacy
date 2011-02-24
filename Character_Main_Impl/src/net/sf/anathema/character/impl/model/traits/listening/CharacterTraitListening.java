package net.sf.anathema.character.impl.model.traits.listening;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
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
    initAttributeListening();
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
      public void backgroundRemoved(IDefaultTrait background) {
        listening.removeTraitListening(background);
        listening.fireCharacterChanged();
      }

      public void backgroundAdded(IDefaultTrait background) {
        listening.addTraitListening(background);
        listening.fireCharacterChanged();
      }
    });
  }

  private void initAbilityListening() {
    ITraitTypeGroup[] groups = traitConfiguration.getAbilityTypeGroups();
    ITraitType[] allAbilityTypes = TraitTypeGroup.getAllTraitTypes(groups);
    ISpecialtiesConfiguration specialtyConfiguration = traitConfiguration.getSpecialtyConfiguration();
    for (ITraitType traitType : allAbilityTypes) {
      IFavorableTrait ability = traitConfiguration.getFavorableTrait(traitType);
      listening.addTraitListening(ability);
      ability.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
        public void favorableStateChanged(FavorableState state) {
          listening.fireCharacterChanged();
        }
      });
      specialtyConfiguration.getSpecialtiesContainer(traitType).addSubTraitListener(new ISubTraitListener() {
        public void subTraitRemoved(ISubTrait specialty) {
          listening.fireCharacterChanged();
        }

        public void subTraitAdded(ISubTrait specialty) {
          listening.fireCharacterChanged();
        }

        public void subTraitValueChanged() {
          listening.fireCharacterChanged();
        }
      });
    }
  }
  
  private void initAttributeListening()
  {
	  ITraitTypeGroup[] groups = traitConfiguration.getAttributeTypeGroups();
	  ITraitType[] allAttributeTypes = TraitTypeGroup.getAllTraitTypes(groups);
	  ISpecialtiesConfiguration specialtyConfiguration = traitConfiguration.getSpecialtyConfiguration();
	  for (ITraitType traitType : allAttributeTypes)
	  {
	      IFavorableTrait attribute = traitConfiguration.getFavorableTrait(traitType);
	      listening.addTraitListening(attribute);
	      attribute.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
	        public void favorableStateChanged(FavorableState state) {
	          listening.fireCharacterChanged();
	        }
	      });
	      
	      specialtyConfiguration.getSpecialtiesContainer(traitType).addSubTraitListener(new ISubTraitListener() {
	          public void subTraitRemoved(ISubTrait specialty) {
	            listening.fireCharacterChanged();
	          }

	          public void subTraitAdded(ISubTrait specialty) {
	            listening.fireCharacterChanged();
	          }

	          public void subTraitValueChanged() {
	            listening.fireCharacterChanged();
	          }
	        });
	  }
  }
}
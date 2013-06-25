package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface SpecialtiesModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Specialties");

  ISubTraitContainer getSpecialtiesContainer(ITraitReference reference);

  ISubTraitContainer getSpecialtiesContainer(TraitType traitType);

  ITraitReference[] getAllTraits();

  ITraitReference[] getAllEligibleTraits();

  void setCurrentTrait(ITraitReference newValue);

  void setCurrentSpecialtyName(String newSpecialtyName);

  void commitSelection();

  void clear();

  boolean isEntryComplete();

  boolean isExperienced();

  void addSelectionChangeListener(ChangeListener listener);

  void addTraitListChangeListener(ITraitReferencesChangeListener listener);
}
package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;

import java.util.List;

public interface IGenericCharacter {

  IGenericTraitCollection getTraitCollection();

  boolean isLearned(IMagic magic);

  Specialty[] getSpecialties(TraitType traitType);

  List<IMagic> getAllLearnedMagic();

  int getLearnCount(ICharm charm);

  IGenericCombo[] getCombos();

  String[] getLearnedEffects(ICharm charm);

  boolean isMultipleEffectCharm(ICharm magic);

  ICharm[] getGenericCharms();
}
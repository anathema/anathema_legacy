package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;

import java.util.List;

public interface IGenericCharacter {

  ITraitLimitation getEssenceLimitation();

  CasteType getCasteType();

  IGenericTraitCollection getTraitCollection();

  boolean isLearned(IMagic magic);

  IGenericDescription getDescription();

  boolean isAlienCharm(ICharm charm);

  HeroTemplate getTemplate();

  Specialty[] getSpecialties(TraitType traitType);

  int getHealthLevelTypeCount(HealthLevelType type);

  String getPeripheralPool();

  String getPersonalPool();

  IConcept getConcept();

  List<IMagic> getAllLearnedMagic();

  int getLearnCount(ICharm charm);

  IGenericCombo[] getCombos();

  boolean isExperienced();

  int getPainTolerance();

  String[] getLearnedEffects(ICharm charm);

  boolean isMultipleEffectCharm(ICharm magic);

  boolean isSubeffectCharm(ICharm magic);

  ICharm[] getGenericCharms();
}
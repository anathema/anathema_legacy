package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.TraitMap;

public interface IPointCostCalculator {

  int getAbilityCosts(Trait ability, boolean favored);

  int getAttributeCosts(Trait attribute, boolean favored);

  int getEssenceCosts(Trait essence);

  int getVirtueCosts(Trait virtue);

  int getWillpowerCosts(Trait willpower);

  double getSpecialtyCosts(boolean favored);

  int getSpellCosts(ISpell spell, IBasicCharacterData basicCharacter, TraitMap traitMap);

  int getCharmCosts(ICharm charm, IBasicCharacterData basicCharacter, TraitMap traitMap);
}
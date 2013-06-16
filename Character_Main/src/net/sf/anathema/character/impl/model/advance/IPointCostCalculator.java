package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.library.trait.Trait;

public interface IPointCostCalculator {

  int getAbilityCosts(Trait ability, boolean favored);

  int getAttributeCosts(Trait attribute, boolean favored);

  int getEssenceCosts(Trait essence);

  int getVirtueCosts(Trait virtue);

  int getWillpowerCosts(Trait willpower);

  double getSpecialtyCosts(boolean favored);

  int getSpellCosts(ISpell spell, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection);

  int getCharmCosts(ICharm charm, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection);
}
package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.library.trait.ITrait;

public interface IPointCostCalculator {

  int getAbilityCosts(ITrait ability, boolean favored);

  int getAttributeCosts(ITrait attribute, boolean favored);

  int getEssenceCosts(IBasicTrait essence);

  int getVirtueCosts(IBasicTrait virtue);

  int getWillpowerCosts(IBasicTrait willpower);

  double getSpecialtyCosts(boolean favored);

  int getSpellCosts(ISpell spell, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection);

  int getCharmCosts(ICharm charm, IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection, FavoringTraitType type);
}
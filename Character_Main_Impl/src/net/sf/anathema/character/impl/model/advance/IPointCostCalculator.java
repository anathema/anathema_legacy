package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.library.trait.ITrait;

public interface IPointCostCalculator {

  public int getAbilityCosts(ITrait ability, boolean favored);

  public int getAttributeCosts(IBasicTrait attribute);

  public int getEssenceCosts(IBasicTrait essence);

  public int getVirtueCosts(IBasicTrait virtue);

  public int getWillpowerCosts(IBasicTrait willpower);

  public int getComboCosts(ICharm[] charms);

  public double getSpecialtyCosts(boolean favored);

  public int getSpellCosts(
      ISpell spell,
      IBasicCharacterData basicCharacter,
      IGenericTraitCollection traitCollection,
      FavoringTraitType type);

  public int getCharmCosts(
      ICharm charm,
      IBasicCharacterData basicCharacter,
      IGenericTraitCollection traitCollection,
      FavoringTraitType type);
}
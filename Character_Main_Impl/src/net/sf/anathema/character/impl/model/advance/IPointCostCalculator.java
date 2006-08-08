package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;

public interface IPointCostCalculator {

  public int getAbilityCosts(IModifiableBasicTrait ability, boolean favored);

  public int getAttributeCosts(IModifiableBasicTrait attribute);

  public int getEssenceCosts(IModifiableBasicTrait essence);

  public int getVirtueCosts(IModifiableBasicTrait virtue);

  public int getWillpowerCosts(IModifiableBasicTrait willpower);

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
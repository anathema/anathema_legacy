package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;

public interface IAdditionalEssencePool {

  public int getAdditionaPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection);

  public int getAdditionaPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection);
}
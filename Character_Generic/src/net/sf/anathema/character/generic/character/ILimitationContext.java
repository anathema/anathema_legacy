package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.ITraitLimitation;

public interface ILimitationContext {

  public ITraitLimitation getEssenceLimitation();

  public ICasteType getCasteType();

  public IGenericTraitCollection getTraitCollection();
  
  public int getAge();
  
  public int getEssenceCap(boolean modified);
}
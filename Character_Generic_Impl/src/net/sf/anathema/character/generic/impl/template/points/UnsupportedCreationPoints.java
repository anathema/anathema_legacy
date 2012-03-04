package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public class UnsupportedCreationPoints implements ICreationPoints {

  @Override
  public IAttributeCreationPoints getAttributeCreationPoints() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  @Override
  public int getBonusPointCount() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  @Override
  public int getBackgroundPointCount() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  @Override
  public IAbilityCreationPoints getAbilityCreationPoints() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  @Override
  public int getVirtueCreationPoints() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }
  
  @Override
  public int getSpecialtyCreationPoints() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  @Override
  public int getFavoredCreationCharmCount() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  @Override
  public int getDefaultCreationCharmCount() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }
  
  @Override
  public int getUniqueRequiredCreationCharmCount() {
	    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
	  }
}
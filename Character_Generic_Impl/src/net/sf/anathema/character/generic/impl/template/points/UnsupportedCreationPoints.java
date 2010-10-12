package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public class UnsupportedCreationPoints implements ICreationPoints {

  public IAttributeCreationPoints getAttributeCreationPoints() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  public int getBonusPointCount() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  public int getBackgroundPointCount() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  public IFavorableTraitCreationPoints getAbilityCreationPoints() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  public int getVirtueCreationPoints() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }
  
  public int getSpecialtyCreationPoints() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  public int getFavoredCreationCharmCount() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }

  public int getDefaultCreationCharmCount() {
    throw new UnsupportedOperationException("Not supported."); //$NON-NLS-1$
  }
}
package net.sf.anathema.character.generic.framework.xml.creation;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.FavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericCreationPoints extends ReflectionCloneableObject<GenericCreationPoints> implements ICreationPoints {

  private IFavorableTraitCreationPoints abilityCreationPoints = new FavorableTraitCreationPoints(0, 0, 0);
  private IAttributeCreationPoints attributeCreationPoints = new AttributeCreationPoints(0, 0, 0);
  private int backgroundPointCount = 0;
  private int bonusPointCount = 0;
  private int defaultCreationCharmCount = 0;
  private int favoredCreationCharmCount = 0;
  private int virtueCreationPoints = 0;

  public IFavorableTraitCreationPoints getAbilityCreationPoints() {
    return abilityCreationPoints;
  }

  public IAttributeCreationPoints getAttributeCreationPoints() {
    return attributeCreationPoints;
  }

  public int getBackgroundPointCount() {
    return backgroundPointCount;
  }

  public int getBonusPointCount() {
    return bonusPointCount;
  }

  public int getDefaultCreationCharmCount() {
    return defaultCreationCharmCount;
  }

  public int getFavoredCreationCharmCount() {
    return favoredCreationCharmCount;
  }

  public int getVirtueCreationPoints() {
    return virtueCreationPoints;
  }

  public void setAbilityCreationPoints(IFavorableTraitCreationPoints abiltyCreationPoints) {
    Ensure.ensureNotNull(abiltyCreationPoints);
    this.abilityCreationPoints = abiltyCreationPoints;
  }

  public void setAttributeCreationPoints(IAttributeCreationPoints attributeCreationPoints) {
    Ensure.ensureNotNull(attributeCreationPoints);
    this.attributeCreationPoints = attributeCreationPoints;
  }

  public void setBackgroundPointCount(int backgroundPointCount) {
    Ensure.ensureArgumentTrue("Background point count must be positive.", backgroundPointCount >= 0); //$NON-NLS-1$
    this.backgroundPointCount = backgroundPointCount;
  }

  public void setBonusPointCount(int bonusPointCount) {
    Ensure.ensureArgumentTrue("Bonus point count must be positive.", bonusPointCount >= 0); //$NON-NLS-1$
    this.bonusPointCount = bonusPointCount;
  }

  public void setGeneralCreationCharmCount(int charmCount) {
    Ensure.ensureArgumentTrue("Default charm count must be positive.", charmCount >= 0); //$NON-NLS-1$
    this.defaultCreationCharmCount = charmCount;
  }

  public void setFavoredCreationCharmCount(int charmCount) {
    Ensure.ensureArgumentTrue("Favored charm count must be positive.", charmCount >= 0); //$NON-NLS-1$
    this.favoredCreationCharmCount = charmCount;
  }

  public void setVirtueCreationPoints(int virtueCreationPoints) {
    Ensure.ensureArgumentTrue("Virtue creation points must be positive.", virtueCreationPoints >= 0); //$NON-NLS-1$
    this.virtueCreationPoints = virtueCreationPoints;
  }

  @Override
  public GenericCreationPoints clone() {
    GenericCreationPoints clone = super.clone();
    clone.attributeCreationPoints = clone.attributeCreationPoints.clone();
    clone.abilityCreationPoints = clone.abilityCreationPoints.clone();
    return clone;
  }
}
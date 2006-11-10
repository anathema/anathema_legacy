package net.sf.anathema.character.generic.framework.xml.trait.alternate;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.IClonableTraitTemplate;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericRestrictedTraitTemplate extends ReflectionCloneableObject<IClonableTraitTemplate> implements
    IClonableTraitTemplate {

  private final IMinimumRestriction restriction;
  private final ITraitType traitType;
  private IClonableTraitTemplate traitTemplate;

  public GenericRestrictedTraitTemplate(
      IClonableTraitTemplate traitTemplate,
      IMinimumRestriction restriction,
      ITraitType traitType) {
    Ensure.ensureArgumentNotNull(traitTemplate);
    Ensure.ensureArgumentNotNull(restriction);
    Ensure.ensureArgumentNotNull(traitType);
    this.traitType = traitType;
    this.traitTemplate = traitTemplate;
    this.restriction = restriction;
    restriction.addTraitType(traitType);
  }

  public ITraitLimitation getLimitation() {
    return traitTemplate.getLimitation();
  }

  public LowerableState getLowerableState() {
    return traitTemplate.getLowerableState();
  }

  public int getStartValue() {
    return traitTemplate.getStartValue();
  }

  public int getZeroLevelValue() {
    return traitTemplate.getZeroLevelValue();
  }

  public boolean isRequiredFavored() {
    return traitTemplate.isRequiredFavored();
  }

  public int getMinimumValue(ILimitationContext limitationContext) {
    if (restriction.isFullfilledWithout(limitationContext.getTraitCollection(), traitType)) {
      return traitTemplate.getMinimumValue(limitationContext);
    }
    return restriction.getStrictMinimumValue();
  }

  public ITraitType getTraitType() {
    return traitType;
  }

  @Override
  public GenericRestrictedTraitTemplate clone() {
    GenericRestrictedTraitTemplate clone = (GenericRestrictedTraitTemplate) super.clone();
    clone.traitTemplate = traitTemplate.clone();
    return clone;
  }
}
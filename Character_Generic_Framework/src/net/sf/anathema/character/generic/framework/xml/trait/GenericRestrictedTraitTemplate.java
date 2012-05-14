package net.sf.anathema.character.generic.framework.xml.trait;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericRestrictedTraitTemplate extends ReflectionCloneableObject<IClonableTraitTemplate> implements
    IClonableTraitTemplate {

  private final List<IMinimumRestriction> restrictions = new ArrayList<IMinimumRestriction>();
  private final ITraitType traitType;
  private IClonableTraitTemplate traitTemplate;

  public GenericRestrictedTraitTemplate(
      IClonableTraitTemplate traitTemplate,
      IMinimumRestriction restriction,
      ITraitType traitType) {
    Preconditions.checkNotNull(traitTemplate);
    Preconditions.checkNotNull(restriction);
    Preconditions.checkNotNull(traitType);
    this.traitType = traitType;
    this.traitTemplate = traitTemplate;
    restrictions.add(restriction);
    restriction.addTraitType(traitType);
  }
  
  public void addRestriction(IMinimumRestriction restriction)
  {
	  restrictions.add(restriction);
  }

  @Override
  public ITraitLimitation getLimitation() {
    return traitTemplate.getLimitation();
  }

  @Override
  public LowerableState getLowerableState() {
    return traitTemplate.getLowerableState();
  }

  @Override
  public int getStartValue() {
    return traitTemplate.getStartValue();
  }

  @Override
  public int getZeroLevelValue() {
    return traitTemplate.getZeroLevelValue();
  }

  @Override
  public boolean isRequiredFavored() {
    return traitTemplate.isRequiredFavored();
  }

  @Override
  public int getMinimumValue(ILimitationContext limitationContext)
  {
	  boolean restricted = false;
	  int minimum = 0;
	  for (IMinimumRestriction restriction : restrictions)
		  restriction.clear();
	  for (IMinimumRestriction restriction : restrictions)
	  	  if (!restriction.isFullfilledWithout(limitationContext, traitType))
	  	  {
	  		  int newMin = restriction.getStrictMinimumValue();
	  		  minimum = newMin > minimum ? newMin : minimum;	  			  
	  		  restricted = true;
	  	  }
	  if (restricted)
		  return minimum;
      return traitTemplate.getMinimumValue(limitationContext);
  }
  
  @Override
  public int getCalculationMinValue(ILimitationContext limitationContext, ITraitType type)
  {
	  int minimum = 0;
	  for (IMinimumRestriction restriction : restrictions)
		  restriction.clear();
	  for (IMinimumRestriction restriction : restrictions)
	  {
	  	  int newMin = restriction.getCalculationMinValue(limitationContext, type);
	  	  minimum = newMin > minimum ? newMin : minimum;	  			  
	  }
      return Math.max(minimum, traitTemplate.getCalculationMinValue(limitationContext, type));
  }

  public ITraitType getTraitType() {
    return traitType;
  }
  
  public List<IMinimumRestriction> getRestrictions()
  {
	  return restrictions;
  }
  
  public void setTemplate(IClonableTraitTemplate template)
  {
	  traitTemplate = template;
  }
  
  public IClonableTraitTemplate getTemplate()
  {
	  return traitTemplate;
  }

  @Override
  public GenericRestrictedTraitTemplate clone() {
    GenericRestrictedTraitTemplate clone = (GenericRestrictedTraitTemplate) super.clone();
    clone.traitTemplate = traitTemplate.clone();
    return clone;
  }
  
  @Override
  public String getTag()
  {
	  return null;
  }
}
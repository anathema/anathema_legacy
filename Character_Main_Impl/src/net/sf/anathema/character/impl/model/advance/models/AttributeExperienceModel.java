package net.sf.anathema.character.impl.model.advance.models;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class AttributeExperienceModel extends AbstractIntegerValueModel {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;
  private final ICharacterStatistics statistics;

  public AttributeExperienceModel(ICoreTraitConfiguration traitConfiguration,
		  IPointCostCalculator calculator,
	      ICharacterStatistics statistics) {
    super("Experience", "Attributes"); //$NON-NLS-1$//$NON-NLS-2$
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
    this.statistics = statistics;
  }

  public Integer getValue() {
    return getAttributeCosts();
  }

  private int getAttributeCosts() {
	  int experienceCosts = 0;
	    for (IFavorableTrait attribute : getAllAttributes())
	      experienceCosts += calculator.getAttributeCosts(attribute, attribute.getFavorization().isCaste()
	          || attribute.getFavorization().isFavored());
	    return experienceCosts;
  }
  
  private IFavorableTrait[] getAllAttributes() {
	    List<ITraitType> attributeTypes = new ArrayList<ITraitType>();
	    for (IGroupedTraitType type : statistics.getCharacterTemplate().getAttributeGroups()) {
	      attributeTypes.add(type.getTraitType());
	    }
	    return traitConfiguration.getFavorableTraits(attributeTypes.toArray(new ITraitType[attributeTypes.size()]));
	  }
}
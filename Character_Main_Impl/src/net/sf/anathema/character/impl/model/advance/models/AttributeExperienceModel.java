package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

import java.util.ArrayList;
import java.util.List;

public class AttributeExperienceModel extends AbstractIntegerValueModel {

  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;
  private final ICharacter character;

  public AttributeExperienceModel(ICoreTraitConfiguration traitConfiguration, IPointCostCalculator calculator, ICharacter character) {
    super("Experience", "Attributes");
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
    this.character = character;
  }

  @Override
  public Integer getValue() {
    return getAttributeCosts();
  }

  private int getAttributeCosts() {
    int experienceCosts = 0;
    for (IFavorableTrait attribute : getAllAttributes()) {
      experienceCosts += calculator.getAttributeCosts(attribute, attribute.getFavorization().isCaste() || attribute.getFavorization().isFavored());
    }
    return experienceCosts;
  }

  private IFavorableTrait[] getAllAttributes() {
    List<ITraitType> attributeTypes = new ArrayList<>();
    for (IGroupedTraitType type : character.getCharacterTemplate().getAttributeGroups()) {
      attributeTypes.add(type.getTraitType());
    }
    return traitConfiguration.getFavorableTraits(attributeTypes.toArray(new ITraitType[attributeTypes.size()]));
  }
}
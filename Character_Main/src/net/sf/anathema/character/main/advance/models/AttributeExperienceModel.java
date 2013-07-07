package net.sf.anathema.character.main.advance.models;

import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.advance.IPointCostCalculator;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class AttributeExperienceModel extends AbstractIntegerValueModel {

  private final TraitMap traitMap;
  private final IPointCostCalculator calculator;
  private final Hero hero;

  public AttributeExperienceModel(TraitMap traitMap, IPointCostCalculator calculator, Hero hero) {
    super("Experience", "Attributes");
    this.traitMap = traitMap;
    this.calculator = calculator;
    this.hero = hero;
  }

  @Override
  public Integer getValue() {
    return getAttributeCosts();
  }

  private int getAttributeCosts() {
    int experienceCosts = 0;
    for (Trait attribute : getAllAttributes()) {
      experienceCosts += calculator.getAttributeCosts(attribute, attribute.getFavorization().isCaste() || attribute.getFavorization().isFavored());
    }
    return experienceCosts;
  }

  private Trait[] getAllAttributes() {
    List<TraitType> attributeTypes = new ArrayList<>();
    for (GroupedTraitType type : hero.getTemplate().getAttributeGroups()) {
      attributeTypes.add(type.getTraitType());
    }
    return traitMap.getTraits(attributeTypes.toArray(new TraitType[attributeTypes.size()]));
  }
}
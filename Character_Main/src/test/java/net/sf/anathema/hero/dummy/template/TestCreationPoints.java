package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.hero.template.points.AbilityCreationPoints;
import net.sf.anathema.hero.template.points.AbstractCreationPoints;
import net.sf.anathema.hero.template.points.AttributeCreationPoints;
import net.sf.anathema.hero.template.points.IAbilityCreationPoints;
import net.sf.anathema.hero.template.points.IAttributeCreationPoints;

public class TestCreationPoints extends AbstractCreationPoints {

  @Override
  public IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(8, 6, 4);
  }

  @Override
  public int getBonusPointCount() {
    return 15;
  }

  @Override
  public IAbilityCreationPoints getAbilityCreationPoints() {
    return new AbilityCreationPoints(5, 10, 15);
  }

  @Override
  public int getFavoredCreationMagicCount() {
    return 5;
  }

  @Override
  public int getDefaultCreationMagicCount() {
    return 5;
  }
}
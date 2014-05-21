package net.sf.anathema.hero.template;

import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.creation.ICreationPoints;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;

import java.util.List;

public interface HeroTemplate {

  TemplateType getTemplateType();

  List<ConfiguredModel> getModels();

  BonusPointCosts getBonusPointCosts();

  ICreationPoints getCreationPoints();

  IExperiencePointCosts getExperienceCost();
}
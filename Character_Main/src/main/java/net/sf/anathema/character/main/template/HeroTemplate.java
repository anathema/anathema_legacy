package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;

import java.util.List;

public interface HeroTemplate {

  TemplateType getTemplateType();

  List<ConfiguredModel> getModels();

  BonusPointCosts getBonusPointCosts();

  ICreationPoints getCreationPoints();

  IExperiencePointCosts getExperienceCost();
}
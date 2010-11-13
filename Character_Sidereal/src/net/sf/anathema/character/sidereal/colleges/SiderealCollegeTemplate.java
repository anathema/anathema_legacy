package net.sf.anathema.character.sidereal.colleges;

import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.sidereal.colleges.model.ICollegeBonusPointCosts;
import net.sf.anathema.character.sidereal.template.ICollegeExperienceCosts;

public class SiderealCollegeTemplate implements IAdditionalTemplate {
  public static final String ID = "SiderealColleges"; //$NON-NLS-1$
  private final IFavorableTraitCreationPoints points;
  private final ICollegeBonusPointCosts bonusPointCosts;
  private final ICollegeExperienceCosts experiencePointCosts;

  public String getId() {
    return ID;
  }

  public SiderealCollegeTemplate(
      IFavorableTraitCreationPoints points,
      ICollegeBonusPointCosts bonusPointCosts,
      ICollegeExperienceCosts experiencePointCosts) {
    this.points = points;
    this.bonusPointCosts = bonusPointCosts;
    this.experiencePointCosts = experiencePointCosts;
  }

  public IFavorableTraitCreationPoints getPoints() {
    return points;
  }

  public ICollegeBonusPointCosts getBonusCosts() {
    return bonusPointCosts;
  }

  public ICollegeExperienceCosts getExperienceCosts() {
    return experiencePointCosts;
  }
}
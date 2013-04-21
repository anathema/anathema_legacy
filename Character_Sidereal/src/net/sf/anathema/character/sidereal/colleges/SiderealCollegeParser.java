package net.sf.anathema.character.sidereal.colleges;

import net.sf.anathema.character.generic.framework.module.CharacterTemplateParser;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.impl.template.experience.DefaultExperienceCosts;
import net.sf.anathema.character.generic.impl.template.points.AbilityCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.sidereal.colleges.model.ICollegeBonusPointCosts;
import net.sf.anathema.character.sidereal.template.ICollegeExperienceCosts;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

@CharacterTemplateParser(modelId = SiderealCollegeTemplate.ID)
public class SiderealCollegeParser implements IAdditionalTemplateParser {
  private static final String ATTRIB_GENERAL = "general";
  private static final String ATTRIB_FAVORED = "favored";
  private static final String ATTRIB_VALUE = "value";
  private static final String TAG_BONUS_GENERAL = "bonusGeneral";
  private static final String TAG_BONUS_FAVORED = "bonusFavored";
  private static final String TAG_XP_NEW_FAVORED = "experienceNewFavored";
  private static final String TAG_XP_NEW_GENERAL = "experienceNewGeneral";
  private static final String TAG_XP_ADVANCE_FAVORED = "experienceAdvanceFavored";
  private static final String TAG_XP_ADVANCE_GENERAL = "experienceAdvanceGeneral";

  @Override
  public IAdditionalTemplate parse(Element element) {
    IFavorableTraitCreationPoints points;
    ICollegeBonusPointCosts bonusCosts;
    ICollegeExperienceCosts experienceCosts;
    try {
      int generalDots = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_GENERAL);
      int favoredDots = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_FAVORED);
      points = new AbilityCreationPoints(0, favoredDots, generalDots);

      Element bonusGeneralElement = ElementUtilities.getRequiredElement(element, TAG_BONUS_GENERAL);
      Element bonusFavoredElement = ElementUtilities.getRequiredElement(element, TAG_BONUS_FAVORED);
      Element xpNewFavoredElement = ElementUtilities.getRequiredElement(element, TAG_XP_NEW_FAVORED);
      Element xpNewGeneralElement = ElementUtilities.getRequiredElement(element, TAG_XP_NEW_GENERAL);
      Element xpAdvanceFavoredElement = ElementUtilities.getRequiredElement(element, TAG_XP_ADVANCE_FAVORED);
      Element xpAdvanceGeneralElement = ElementUtilities.getRequiredElement(element, TAG_XP_ADVANCE_GENERAL);

      bonusCosts = new CollegeBonusPointCost(
              ElementUtilities.getRequiredIntAttrib(bonusFavoredElement, ATTRIB_VALUE),
              ElementUtilities.getRequiredIntAttrib(bonusGeneralElement, ATTRIB_VALUE));

      experienceCosts = new CollegeExperienceCost(
              ElementUtilities.getRequiredIntAttrib(xpNewFavoredElement, ATTRIB_VALUE),
              ElementUtilities.getRequiredIntAttrib(xpNewGeneralElement, ATTRIB_VALUE),
              ElementUtilities.getRequiredIntAttrib(xpAdvanceFavoredElement, ATTRIB_VALUE),
              ElementUtilities.getRequiredIntAttrib(xpAdvanceGeneralElement, ATTRIB_VALUE));
    } catch (PersistenceException e) {
      return null;
    }
    return new SiderealCollegeTemplate(points, bonusCosts, experienceCosts);
  }

  private static class CollegeBonusPointCost extends DefaultBonusPointCosts implements ICollegeBonusPointCosts {
    final int favoredCost, generalCost;

    public CollegeBonusPointCost(int favoredCost, int generalCost) {
      this.favoredCost = favoredCost;
      this.generalCost = generalCost;
    }

    @Override
    public int getCollegeCosts(boolean favored) {
      return favored ? favoredCost : generalCost;
    }
  }

  private static class CollegeExperienceCost extends DefaultExperienceCosts implements ICollegeExperienceCosts {
    final int newFavoredCost;
    final int newGeneralCost;
    final int advanceFavoredCost;
    final int advanceGeneralCost;

    public CollegeExperienceCost(int newFavored, int newGeneral,
                                 int advanceFavored, int advanceGeneral) {
      this.newFavoredCost = newFavored;
      this.newGeneralCost = newGeneral;
      this.advanceFavoredCost = advanceFavored;
      this.advanceGeneralCost = advanceGeneral;
    }

    @Override
    public CurrentRatingCosts getCollegeExperienceCost(boolean favored) {
      return favored ? new MultiplyRatingCosts(advanceFavoredCost, newFavoredCost) :
              new MultiplyRatingCosts(advanceGeneralCost, newGeneralCost);
    }
  }
}
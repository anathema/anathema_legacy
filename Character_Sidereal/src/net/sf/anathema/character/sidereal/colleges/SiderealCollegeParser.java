package net.sf.anathema.character.sidereal.colleges;

import org.dom4j.Element;

import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.impl.template.experience.DefaultExperienceCosts;
import net.sf.anathema.character.generic.impl.template.points.AbilityCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.sidereal.colleges.model.ICollegeBonusPointCosts;
import net.sf.anathema.character.sidereal.template.ICollegeExperienceCosts;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

public class SiderealCollegeParser implements IAdditionalTemplateParser
{
	private static final String ATTRIB_GENERAL = "general";
	private static final String ATTRIB_FAVORED = "favored";
	private static final String ATTRIB_VALUE = "value";
	private static final String TAG_BONUS_GENERAL = "bonusGeneral";
	private static final String TAG_BONUS_FAVORED = "bonusFavored";
	private static final String TAG_XP_BASE = "experienceBase";
	private static final String TAG_XP_MULTIPLE = "experienceMultiple";

	@Override
	public IAdditionalTemplate parse(Element element)
	{
		IFavorableTraitCreationPoints points = null;
		ICollegeBonusPointCosts bonusCosts = null;
		ICollegeExperienceCosts experienceCosts = null;
		try
		{
			int generalDots = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_GENERAL);
			int favoredDots = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_FAVORED);
			points = new AbilityCreationPoints(0, favoredDots, generalDots);

			Element bonusGeneralElement = ElementUtilities.getRequiredElement(element, TAG_BONUS_GENERAL);
			Element bonusFavoredElement = ElementUtilities.getRequiredElement(element, TAG_BONUS_FAVORED);
			Element xpBaseElement = ElementUtilities.getRequiredElement(element, TAG_XP_BASE);
			Element xpMultipleElement = ElementUtilities.getRequiredElement(element, TAG_XP_MULTIPLE);

			bonusCosts = new CollegeBonusPointCost(
					ElementUtilities.getRequiredIntAttrib(bonusFavoredElement, ATTRIB_VALUE),
					ElementUtilities.getRequiredIntAttrib(bonusGeneralElement, ATTRIB_VALUE));

			experienceCosts = new CollegeExperienceCost(
					ElementUtilities.getRequiredIntAttrib(xpBaseElement, ATTRIB_VALUE),
					ElementUtilities.getRequiredIntAttrib(xpMultipleElement, ATTRIB_VALUE));
		}
		catch (PersistenceException e)
		{
			return null;
		}
		return new SiderealCollegeTemplate(points, bonusCosts, experienceCosts);
	}

	private static class CollegeBonusPointCost extends DefaultBonusPointCosts implements ICollegeBonusPointCosts
	{
		final int favoredCost, generalCost;

		public CollegeBonusPointCost(int favoredCost, int generalCost)
		{
			this.favoredCost = favoredCost;
			this.generalCost = generalCost;
		}

		@Override
		public int getCollegeCosts(boolean favored)
		{
			return favored ? favoredCost : generalCost;
		}

	}

	private static class CollegeExperienceCost extends DefaultExperienceCosts implements ICollegeExperienceCosts
	{
		final int baseCost, multiple;

		public CollegeExperienceCost(int baseCost, int multiple)
		{
			this.baseCost = baseCost;
			this.multiple = multiple;
		}

		@Override
		public ICurrentRatingCosts getCollegeExperienceCost() {
			return new MultiplyRatingCosts(multiple, baseCost);
		}
	}

}

package net.sf.anathema.character.generic.framework.xml.experience;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.util.CostParser;
import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class ExperienceTemplateParser extends AbstractXmlTemplateParser<GenericExperiencePointCosts> {

  private static final String ATTRIB_FAVORED = "favored"; //$NON-NLS-1$
  private static final String ATTRIB_GENERAL = "general"; //$NON-NLS-1$
  private static final String ATTRIB_INITIALCOST = "initialCosts"; //$NON-NLS-1$
  private static final String ATTRIB_MULTIPLIER = "multiplier"; //$NON-NLS-1$
  private static final String ATTRIB_SUMMAND = "summand"; //$NON-NLS-1$
  private static final String ATTRIB_COST = "cost";

  private static final String TAG_ATTRIBUTES = "attributes"; //$NON-NLS-1$
  private static final String TAG_GENERAL_COSTS = "generalCosts"; //$NON-NLS-1$
  private static final String TAG_CURRENT_RATING_COSTS = "currentRating"; //$NON-NLS-1$
  private static final String TAG_ABILITIES = "abilities"; //$NON-NLS-1$
  private static final String TAG_FAVORED_COSTS = "favoredCosts"; //$NON-NLS-1$
  private static final String TAG_SPECIALTIES = "specialties"; //$NON-NLS-1$
  private static final String TAG_ADVANTAGES = "advantages"; //$NON-NLS-1$
  private static final String TAG_WILLPOWER = "willpower"; //$NON-NLS-1$
  private static final String TAG_VIRTUE = "virtues"; //$NON-NLS-1$
  private static final String TAG_ESSENCE = "essence"; //$NON-NLS-1$
  private static final String TAG_MAGIC = "magic"; //$NON-NLS-1$
  private static final String TAG_CHARMS = "charms"; //$NON-NLS-1$
  private static final String TAG_MARTIAL_ARTS = "highLevelMartialArts"; //$NON-NLS-1$
  private static final String TAG_BACKGROUNDS = "backgrounds";
  private final CostParser costParser = new CostParser();
  private final MartialArtsLevel standardLevel;

  public ExperienceTemplateParser(
      IXmlTemplateRegistry<GenericExperiencePointCosts> templateRegistry,
      MartialArtsLevel standardLevel) {
    super(templateRegistry);
    this.standardLevel = standardLevel;
  }

  @Override
  protected GenericExperiencePointCosts createNewBasicTemplate() {
    return new GenericExperiencePointCosts();
  }

  public GenericExperiencePointCosts parseTemplate(Element element) throws PersistenceException {
    GenericExperiencePointCosts costs = getBasicTemplate(element);
    costs.setStandardMartialArtsLevel(standardLevel);
    setAttributeCosts(element, costs);
    setAbilityCosts(element, costs);
    setAdvantageCosts(element, costs);
    setMagicCosts(element, costs);
    return costs;
  }

  private void setMagicCosts(Element element, GenericExperiencePointCosts costs) throws PersistenceException {
    Element magic = element.element(TAG_MAGIC);
    if (magic == null) {
      return;
    }
    Element charms = magic.element(TAG_CHARMS);
    int favoredCost = ElementUtilities.getRequiredIntAttrib(charms, ATTRIB_FAVORED);
    int generalCost = ElementUtilities.getRequiredIntAttrib(charms, ATTRIB_GENERAL);
    costs.setCharmCosts(favoredCost, generalCost);
    Element martialArts = charms.element(TAG_MARTIAL_ARTS);
    int favoredMartialArtsCost = ElementUtilities.getRequiredIntAttrib(martialArts, ATTRIB_FAVORED);
    int generalMartialArtsCost = ElementUtilities.getRequiredIntAttrib(martialArts, ATTRIB_GENERAL);
    costs.setMartialArtsCosts(favoredMartialArtsCost, generalMartialArtsCost);
  }

  private void setAdvantageCosts(Element element, GenericExperiencePointCosts costs) throws PersistenceException {
    Element advantages = element.element(TAG_ADVANTAGES);
    if (advantages == null) {
      return;
    }
    setBackgroundCosts(costs, advantages);
    setWillpowerCosts(costs, advantages);
    setVirtueCosts(costs, advantages);
    setEssenceCosts(costs, advantages);
  }

 private void setBackgroundCosts(GenericExperiencePointCosts costs, Element advantages) throws PersistenceException {
   Element element = advantages.element(TAG_BACKGROUNDS);
   if (element == null) {
     return;
   }
   costs.setBackgroundCosts(ElementUtilities.getRequiredIntAttrib(element, ATTRIB_COST));
  }

 private void setEssenceCosts(GenericExperiencePointCosts costs, Element advantages) throws PersistenceException {
    Element element = advantages.element(TAG_ESSENCE);
    if (element == null) {
      return;
    }
    ICurrentRatingCosts ratingCosts = getCurrentRatingCosts(element);
    costs.setEssenceCosts(ratingCosts);
  }

  private void setVirtueCosts(GenericExperiencePointCosts costs, Element advantages) throws PersistenceException {
    Element element = advantages.element(TAG_VIRTUE);
    if (element == null) {
      return;
    }
    ICurrentRatingCosts ratingCosts = getCurrentRatingCosts(element);
    costs.setVirtueCosts(ratingCosts);
  }

  private void setWillpowerCosts(GenericExperiencePointCosts costs, Element advantages) throws PersistenceException {
    Element element = advantages.element(TAG_WILLPOWER);
    if (element == null) {
      return;
    }
    ICurrentRatingCosts ratingCosts = getCurrentRatingCosts(element);
    costs.setWillpowerCosts(ratingCosts);
  }

  private void setAbilityCosts(Element element, GenericExperiencePointCosts costs) throws PersistenceException {
    Element abilities = element.element(TAG_ABILITIES);
    if (abilities == null) {
      return;
    }
    costs.setGeneralAbilityCosts(getGeneralCost(abilities));
    costs.setFavoredAbilityCosts(getFavoredCost(abilities));
    setSpecialtyCosts(abilities, costs);
  }

  private void setSpecialtyCosts(Element abilities, GenericExperiencePointCosts costs) throws PersistenceException {
    int specialtyCost = costParser.getFixedCostFromRequiredElement(abilities, TAG_SPECIALTIES);
    costs.setSpecialtyCosts(specialtyCost);
  }

  private void setAttributeCosts(Element element, GenericExperiencePointCosts costs) throws PersistenceException {
    Element attributes = element.element(TAG_ATTRIBUTES);
    if (attributes == null) {
      return;
    }
    costs.setGeneralAttributeCosts(getGeneralCost(attributes));
  }

  protected final ICurrentRatingCosts getFavoredCost(Element attributes) throws PersistenceException {
    return getCurrentRatingCosts(attributes, TAG_FAVORED_COSTS);
  }

  protected final ICurrentRatingCosts getGeneralCost(Element attributes) throws PersistenceException {
    return getCurrentRatingCosts(attributes, TAG_GENERAL_COSTS);
  }

  protected final ICurrentRatingCosts getCurrentRatingCosts(Element element) throws PersistenceException {
    return getMultiplyRatingCosts(element);
  }

  protected final ICurrentRatingCosts getCurrentRatingCosts(Element parentElement, String tagName)
      throws PersistenceException {
    Element element = ElementUtilities.getRequiredElement(parentElement, tagName);
    return getMultiplyRatingCosts(element);
  }

  private MultiplyRatingCosts getMultiplyRatingCosts(Element parentElement) throws PersistenceException {
    Element element = ElementUtilities.getRequiredElement(parentElement, TAG_CURRENT_RATING_COSTS);
    int multiplier = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_MULTIPLIER);
    int summand = ElementUtilities.getIntAttrib(element, ATTRIB_SUMMAND, 0);
    int initialCost = ElementUtilities.getIntAttrib(element, ATTRIB_INITIALCOST, Integer.MIN_VALUE);
    return new MultiplyRatingCosts(multiplier, initialCost, summand);
  }
}
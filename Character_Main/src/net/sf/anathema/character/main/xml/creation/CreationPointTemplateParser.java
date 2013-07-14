package net.sf.anathema.character.main.xml.creation;

import net.sf.anathema.character.main.template.points.AbilityCreationPoints;
import net.sf.anathema.character.main.template.points.AttributeCreationPoints;
import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class CreationPointTemplateParser extends AbstractXmlTemplateParser<GenericCreationPoints> {

  private static final String ATTRIB_COUNT = "count";
  private static final String ATTRIB_FAVORED = "favored";
  private static final String ATTRIB_FAVORED_PICKS = "favoredPicks";
  private static final String ATTRIB_FAVORED_DOTS = "favoredDots";
  private static final String ATTRIB_GENERIC_DOTS = "genericDots";
  private static final String ATTRIB_GENERAL = "general";
  private static final String ATTRIB_PRIMARY = "primary";
  private static final String ATTRIB_SECONDARY = "secondary";
  private static final String ATTRIB_TERTIARY = "tertiary";
  private static final String TAG_ABILITY_DOTS = "abilityDots";
  private static final String TAG_CHARM_PICKS = "charmPicks";
  private static final String TAG_ATTRIBUTE_DOTS = "attributeDots";
  private static final String TAG_SPECIALTY_DOTS = "specialtyDots";
  private static final String TAG_VIRTUE_DOTS = "virtueDots";
  private static final String TAG_BONUS_POINTS = "bonusPoints";

  public CreationPointTemplateParser(IXmlTemplateRegistry<GenericCreationPoints> templateRegistry) {
    super(templateRegistry);
  }

  @Override
  public GenericCreationPoints parseTemplate(Element element) throws PersistenceException {
    GenericCreationPoints creationPoints = new GenericCreationPoints();
    parseAttributeCreationPoints(element.element(TAG_ATTRIBUTE_DOTS), creationPoints);
    parseAbilityCreationPoints(element.element(TAG_ABILITY_DOTS), creationPoints);
    parseCharmCreationPoints(element.element(TAG_CHARM_PICKS), creationPoints);
    Element virtueElement = element.element(TAG_VIRTUE_DOTS);
    if (virtueElement != null) {
      creationPoints.setVirtueCreationPoints(getCountAttribute(virtueElement));
    }
    Element bonusPointsElement = element.element(TAG_BONUS_POINTS);
    if (bonusPointsElement != null) {
      creationPoints.setBonusPointCount(getCountAttribute(bonusPointsElement));
    }
    Element specialityElement = element.element(TAG_SPECIALTY_DOTS);
    if (specialityElement != null) {
      creationPoints.setSpecialityPoints(getCountAttribute(specialityElement));
    }
    return creationPoints;
  }

  private void parseAbilityCreationPoints(Element element, GenericCreationPoints creationPoints) throws PersistenceException {
    if (element == null) {
      return;
    }
    int generalDots = ElementUtilities.getIntAttrib(element, ATTRIB_GENERAL, 0);
    int favoredDots = ElementUtilities.getIntAttrib(element, ATTRIB_FAVORED, 0);
    int favoredPicks = ElementUtilities.getIntAttrib(element, ATTRIB_FAVORED_PICKS, 0);
    creationPoints.setAbilityCreationPoints(new AbilityCreationPoints(favoredPicks, favoredDots, generalDots));
  }

  private void parseAttributeCreationPoints(Element element, GenericCreationPoints creationPoints) throws PersistenceException {
    if (element == null) {
      return;
    }
    int primaryDots = ElementUtilities.getIntAttrib(element, ATTRIB_PRIMARY, 0);
    int secondaryDots = ElementUtilities.getIntAttrib(element, ATTRIB_SECONDARY, 0);
    int tertiaryDots = ElementUtilities.getIntAttrib(element, ATTRIB_TERTIARY, 0);
    int favoredPicks = ElementUtilities.getIntAttrib(element, ATTRIB_FAVORED_PICKS, 0);
    int favoredDots = ElementUtilities.getIntAttrib(element, ATTRIB_FAVORED_DOTS, 0);
    int genericDots = ElementUtilities.getIntAttrib(element, ATTRIB_GENERIC_DOTS, 0);
    creationPoints.setAttributeCreationPoints(
            new AttributeCreationPoints(primaryDots, secondaryDots, tertiaryDots, favoredPicks, favoredDots, genericDots));
  }

  private int getCountAttribute(Element element) throws PersistenceException {
    return ElementUtilities.getIntAttrib(element, ATTRIB_COUNT, 0);
  }

  private void parseCharmCreationPoints(Element element, GenericCreationPoints creationPoints) throws PersistenceException {
    if (element == null) {
      return;
    }
    int generalPicks = ElementUtilities.getIntAttrib(element, ATTRIB_GENERAL, 0);
    int favoredPicks = ElementUtilities.getIntAttrib(element, ATTRIB_FAVORED, 0);
    creationPoints.setGeneralCreationCharmCount(generalPicks);
    creationPoints.setFavoredCreationCharmCount(favoredPicks);
  }

  @Override
  protected GenericCreationPoints createNewBasicTemplate() {
    return new GenericCreationPoints();
  }
}
package net.sf.anathema.character.generic.framework.xml.creation;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.FavorableTraitCreationPoints;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CreationPointTemplateParser extends AbstractXmlTemplateParser<GenericCreationPoints> {

  private static final String ATTRIB_COUNT = "count"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORED = "favored"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORED_PICKS = "favoredPicks"; //$NON-NLS-1$  
  private static final String ATTRIB_GENERAL = "general"; //$NON-NLS-1$
  private static final String ATTRIB_PRIMARY = "primary"; //$NON-NLS-1$
  private static final String ATTRIB_SECONDARY = "secondary"; //$NON-NLS-1$
  private static final String ATTRIB_TERTIARY = "tertiary"; //$NON-NLS-1$
  private static final String TAG_ABILITY_DOTS = "abilityDots"; //$NON-NLS-1$
  private static final String TAG_CHARM_PICKS = "charmPicks"; //$NON-NLS-1$
  private static final String TAG_ATTRIBUTE_DOTS = "attributeDots"; //$NON-NLS-1$
  private static final String TAG_BACKGROUND_DOTS = "backgroundDots"; //$NON-NLS-1$
  private static final String TAG_SPECIALTY_DOTS = "specialtyDots"; //$NON-NLS-1$
  private static final String TAG_VIRTUE_DOTS = "virtueDots"; //$NON-NLS-1$
  private static final String TAG_BONUS_POINTS = "bonusPoints"; //$NON-NLS-1$

  public CreationPointTemplateParser(IXmlTemplateRegistry<GenericCreationPoints> templateRegistry) {
    super(templateRegistry);
  }

  public GenericCreationPoints parseTemplate(Element element) throws PersistenceException {
    GenericCreationPoints creationPoints = new GenericCreationPoints();
    parseAttributeCreationPoints(element.element(TAG_ATTRIBUTE_DOTS), creationPoints);
    parseAbilityCreationPoints(element.element(TAG_ABILITY_DOTS), creationPoints);
    parseCharmCreationPoints(element.element(TAG_CHARM_PICKS), creationPoints);
    Element backgroundElement = element.element(TAG_BACKGROUND_DOTS);
    if (backgroundElement != null) {
      creationPoints.setBackgroundPointCount(getCountAttribute(backgroundElement));
    }
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

  private void parseAbilityCreationPoints(Element element, GenericCreationPoints creationPoints)
      throws PersistenceException {
    if (element == null) {
      return;
    }
    int generalDots = ElementUtilities.getIntAttrib(element, ATTRIB_GENERAL, 0);
    int favoredDots = ElementUtilities.getIntAttrib(element, ATTRIB_FAVORED, 0);
    int favoredPicks = ElementUtilities.getIntAttrib(element, ATTRIB_FAVORED_PICKS, 0);
    creationPoints.setAbilityCreationPoints(new FavorableTraitCreationPoints(favoredPicks, favoredDots, generalDots));
  }

  private void parseAttributeCreationPoints(Element element, GenericCreationPoints creationPoints)
      throws PersistenceException {
    if (element == null) {
      return;
    }
    int primaryDots = ElementUtilities.getIntAttrib(element, ATTRIB_PRIMARY, 0);
    int secondaryDots = ElementUtilities.getIntAttrib(element, ATTRIB_SECONDARY, 0);
    int tertiaryDots = ElementUtilities.getIntAttrib(element, ATTRIB_TERTIARY, 0);
    creationPoints.setAttributeCreationPoints(new AttributeCreationPoints(primaryDots, secondaryDots, tertiaryDots));
  }

  private int getCountAttribute(Element element) throws PersistenceException {
    return ElementUtilities.getIntAttrib(element, ATTRIB_COUNT, 0);
  }

  private void parseCharmCreationPoints(Element element, GenericCreationPoints creationPoints)
      throws PersistenceException {
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
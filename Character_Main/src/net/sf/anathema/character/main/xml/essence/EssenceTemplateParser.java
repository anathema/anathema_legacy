package net.sf.anathema.character.main.xml.essence;

import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class EssenceTemplateParser extends AbstractXmlTemplateParser<GenericEssenceTemplate> {

  private static final String ATTRIB_COUNT = "count";
  private static final String ATTRIB_IS_ESSENCE_USER = "isEssenceUser";
  private static final String ATTRIB_MULTIPLIER = "multiplier";
  private static final String ATTRIB_TRAIT_TYPE = "traitType";
  private static final String TAG_ESSENCE_PART = "essencePart";
  private static final String TAG_PERIPHERAL_POOL = "peripheralPool";
  private static final String TAG_PERSONAL_POOL = "personalPool";
  private static final String TAG_VIRTUE_PART = "virtuePart";
  private static final String TAG_WILLPOWER_PART = "willpowerPart";
  private static final String TAG_VIRTUE_RANKS = "virtueRanks";
  private static final String TAG_VIRTUE_TYPE = "virtueType";

  public EssenceTemplateParser(IXmlTemplateRegistry<GenericEssenceTemplate> templateRegistry) {
    super(templateRegistry);
  }

  @Override
  public GenericEssenceTemplate parseTemplate(Element element) throws PersistenceException {
    GenericEssenceTemplate genericEssenceTemplate = getBasicTemplate(element);
    boolean isEssenceUser = ElementUtilities.getBooleanAttribute(element, ATTRIB_IS_ESSENCE_USER, genericEssenceTemplate.isEssenceUser());
    genericEssenceTemplate.setEssenceUser(isEssenceUser);
    setPersonalPool(element, genericEssenceTemplate);
    setPeripheralPool(element, genericEssenceTemplate);
    return genericEssenceTemplate;
  }

  private void setPeripheralPool(Element element, GenericEssenceTemplate genericEssenceTemplate) throws PersistenceException {
    Element poolElement = element.element(TAG_PERIPHERAL_POOL);
    if (poolElement == null) {
      return;
    }
    genericEssenceTemplate.setPeripheralPoolConfiguration(parsePoolTemplate(poolElement));
  }

  private void setPersonalPool(Element element, GenericEssenceTemplate genericEssenceTemplate) throws PersistenceException {
    Element poolElement = element.element(TAG_PERSONAL_POOL);
    if (poolElement == null) {
      return;
    }
    genericEssenceTemplate.setPersonalPoolConfiguration(parsePoolTemplate(poolElement));
  }

  @Override
  protected GenericEssenceTemplate createNewBasicTemplate() {
    return new GenericEssenceTemplate();
  }

  private IEssencePoolConfiguration parsePoolTemplate(Element element) throws PersistenceException {
    if (element == null) {
      return null;
    }
    int essenceMultiplier = parseMultiplier(element.element(TAG_ESSENCE_PART));
    int willpowerMultiplier = parseMultiplier(element.element(TAG_WILLPOWER_PART));
    EssencePoolConfiguration configuration = new EssencePoolConfiguration(essenceMultiplier, willpowerMultiplier);
    addVirtuePoolParts(configuration, element.element(TAG_VIRTUE_PART));
    return configuration;
  }

  private void addVirtuePoolParts(EssencePoolConfiguration configuration, Element virtuePartElement) throws PersistenceException {
    if (virtuePartElement == null) {
      return;
    }
    for (Element rankElement : ElementUtilities.elements(virtuePartElement, TAG_VIRTUE_RANKS)) {
      int ranks = ElementUtilities.getIntAttrib(rankElement, ATTRIB_COUNT, 0);
      int virtueMultiplier = parseMultiplier(rankElement);
      IVirtuePoolPart virtuePoolPart = new RankVirtuePoolPart(ranks, virtueMultiplier);
      configuration.addVirtuePoolPart(virtuePoolPart);
    }
    for (Element typeElement : ElementUtilities.elements(virtuePartElement, TAG_VIRTUE_TYPE)) {
      VirtueType type = VirtueType.valueOf(ElementUtilities.getRequiredAttrib(typeElement, ATTRIB_TRAIT_TYPE));
      int virtueMultiplier = parseMultiplier(typeElement);
      IVirtuePoolPart virtuePoolPart = new TypeVirtuePoolPart(type, virtueMultiplier);
      configuration.addVirtuePoolPart(virtuePoolPart);
    }
  }

  private int parseMultiplier(Element element) throws PersistenceException {
    if (element == null) {
      return 0;
    }
    return ElementUtilities.getIntAttrib(element, ATTRIB_MULTIPLIER, 0);
  }
}
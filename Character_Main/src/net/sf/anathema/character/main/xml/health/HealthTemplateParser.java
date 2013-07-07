package net.sf.anathema.character.main.xml.health;

import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.traits.TraitTypeUtils;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class HealthTemplateParser extends AbstractXmlTemplateParser<GenericHealthTemplate> {

  private static final String TAG_TOUGHNESS_TRAIT = "toughnessControllingTrait";
  private static final String TAG_BASE_PROVIDER = "usesBaseProvider";
  private static final String ATTRIB_TYPE = "type";
  private static final String ATTRIB_PATH = "path";

  private final TraitTypeUtils traitTypeUtils = new TraitTypeUtils();

  public HealthTemplateParser(IXmlTemplateRegistry<GenericHealthTemplate> registry) {
    super(registry);
  }

  @Override
  protected GenericHealthTemplate createNewBasicTemplate() {
    return new GenericHealthTemplate();
  }

  @Override
  public GenericHealthTemplate parseTemplate(Element element) throws PersistenceException {
    GenericHealthTemplate basicTemplate = getBasicTemplate(element);
    setToughnessControllingTrait(element, basicTemplate);
    setBaseProviders(element, basicTemplate);
    return basicTemplate;
  }

  private void setToughnessControllingTrait(Element generalElement, GenericHealthTemplate basicTemplate) {
    List<TraitType> traits = new ArrayList<>();
    for (Object element : generalElement.elements(TAG_TOUGHNESS_TRAIT)) {
      Element toughnessElement = (Element) element;
      String traitTypeString = toughnessElement.attributeValue(ATTRIB_TYPE);
      traits.add(traitTypeUtils.getTraitTypeById(traitTypeString));
    }
    TraitType[] traitArray = new TraitType[traits.size()];
    traits.toArray(traitArray);
    basicTemplate.setToughnessControllingTraitTypes(traitArray);
  }

  private void setBaseProviders(Element generalElement, GenericHealthTemplate template) {
    List<String> baseHealthProviders = new ArrayList<>();
    try {
      for (Object element : generalElement.elements(TAG_BASE_PROVIDER)) {
        baseHealthProviders.add(ElementUtilities.getRequiredAttrib((Element) element, ATTRIB_PATH));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    String[] providerArray = new String[baseHealthProviders.size()];
    baseHealthProviders.toArray(providerArray);
    template.setBaseProviders(providerArray);
  }
}
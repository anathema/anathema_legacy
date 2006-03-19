package net.sf.anathema.character.generic.framework.xml.health;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.traits.TraitTypeUtils;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class HealthTemplateParser extends AbstractXmlTemplateParser<GenericHealthTemplate> {

  private static final String TAG_TOUGHNESS_TRAIT = "toughnessControllingTrait"; //$NON-NLS-1$
  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$

  public HealthTemplateParser(IXmlTemplateRegistry<GenericHealthTemplate> registry) {
    super(registry);
  }

  @Override
  protected GenericHealthTemplate createNewBasicTemplate() {
    return new GenericHealthTemplate();
  }

  public GenericHealthTemplate parseTemplate(Element element) throws PersistenceException {
    GenericHealthTemplate basicTemplate = getBasicTemplate(element);
    setToughnessControllingTrait(element, basicTemplate);
    return basicTemplate;
  }

  private void setToughnessControllingTrait(Element generalElement, GenericHealthTemplate basicTemplate) {
    Element toughnessElement = generalElement.element(TAG_TOUGHNESS_TRAIT);
    if (toughnessElement == null) {
      return;
    }
    String traitTypeString = toughnessElement.attributeValue(ATTRIB_TYPE);
    basicTemplate.setToughnessControllingTraitType(new TraitTypeUtils().getTraitTypeById(traitTypeString));
  }
}
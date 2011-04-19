package net.sf.anathema.character.generic.framework.xml.health;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.traits.TraitTypeUtils;
import net.sf.anathema.character.generic.traits.ITraitType;
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
	List<ITraitType> traits = new ArrayList<ITraitType>();
	for (Object element : generalElement.elements(TAG_TOUGHNESS_TRAIT))
	{
		Element toughnessElement = (Element)element;
		String traitTypeString = toughnessElement.attributeValue(ATTRIB_TYPE);
		traits.add(new TraitTypeUtils().getTraitTypeById(traitTypeString));
	}
	ITraitType[] traitArray = new ITraitType[traits.size()];
	traits.toArray(traitArray);
    basicTemplate.setToughnessControllingTraitTypes(traitArray);
  }
}
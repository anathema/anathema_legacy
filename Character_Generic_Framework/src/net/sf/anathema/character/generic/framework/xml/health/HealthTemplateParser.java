package net.sf.anathema.character.generic.framework.xml.health;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.traits.TraitTypeUtils;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class HealthTemplateParser extends AbstractXmlTemplateParser<GenericHealthTemplate> {

  private static final String TAG_TOUGHNESS_TRAIT = "toughnessControllingTrait"; //$NON-NLS-1$
  private static final String TAG_BASE_PROVIDER = "usesBaseProvider";
  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$
  private static final String ATTRIB_PATH = "path";

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
    setBaseProviders(element, basicTemplate);
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
  
  private void setBaseProviders(Element generalElement, GenericHealthTemplate template)
  {
	  List<String> baseHealthProviders = new ArrayList<String>();
	  
	  try
	  {
		  for (Object element : generalElement.elements(TAG_BASE_PROVIDER))
			  baseHealthProviders.add(ElementUtilities.getRequiredAttrib((Element)element, ATTRIB_PATH));
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  String[] providerArray = new String[baseHealthProviders.size()];
	  baseHealthProviders.toArray(providerArray);
	  template.setBaseProviders(providerArray);
  }
}
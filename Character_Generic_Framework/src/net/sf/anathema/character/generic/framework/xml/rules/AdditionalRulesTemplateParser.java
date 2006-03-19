package net.sf.anathema.character.generic.framework.xml.rules;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

public class AdditionalRulesTemplateParser extends AbstractXmlTemplateParser<GenericAdditionalRules> {

  private static final String TAG_REQUIRED_MAGIC = "requiredMagic"; //$NON-NLS-1$
  private static final String TAG_MAGIC = "magic"; //$NON-NLS-1$
  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$
  private static final Object VALUE_CHARM = "charm"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$

  public AdditionalRulesTemplateParser(IXmlTemplateRegistry<GenericAdditionalRules> registry) {
    super(registry);
  }

  @Override
  protected GenericAdditionalRules createNewBasicTemplate() {
    return new GenericAdditionalRules();
  }

  public GenericAdditionalRules parseTemplate(Element element) throws PersistenceException {
    GenericAdditionalRules basicTemplate = getBasicTemplate(element);
    setCompulsiveCharms(element, basicTemplate);
    return basicTemplate;
  }

  private void setCompulsiveCharms(Element element, GenericAdditionalRules basicTemplate) throws PersistenceException {
    Element requiredMagicElement = element.element(TAG_REQUIRED_MAGIC);
    if (requiredMagicElement == null) {
      return;
    }
    List<Element> magicElements = ElementUtilities.elements(requiredMagicElement, TAG_MAGIC);
    List<String> compulsiveCharms = new ArrayList<String>();
    for (Element magic : magicElements) {
      String type = ElementUtilities.getRequiredAttrib(magic, ATTRIB_TYPE);
      if (type.equals(VALUE_CHARM)) {
        compulsiveCharms.add(ElementUtilities.getRequiredAttrib(magic, ATTRIB_ID));
      }
    }
    basicTemplate.setCompulsiveCharmIds(compulsiveCharms.toArray(new String[compulsiveCharms.size()]));
  }
}
package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class TemplateTypeParser {

  public ITemplateType parse(Element element) throws PersistenceException {
    String characterTypeId = ElementUtilities.getRequiredAttrib(element, "characterType"); //$NON-NLS-1$
    CharacterType characterType = CharacterType.getById(characterTypeId);
    String subtemplate = element.attributeValue("subtemplate"); //$NON-NLS-1$
    IIdentificate subtemplateIdentificate = createSubtemplateIdentificate(subtemplate);
    return new TemplateType(characterType, subtemplateIdentificate);
  }

  private IIdentificate createSubtemplateIdentificate(String subtemplate) {
    if (subtemplate == null || subtemplate.equals("default")) { //$NON-NLS-1$
      return TemplateType.DEFAULT_SUB_TYPE;
    }
    return new Identificate(subtemplate);
  }
}
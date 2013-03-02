package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class TemplateTypeParser {

  private CharacterTypes characterTypes;

  public TemplateTypeParser(CharacterTypes characterTypes) {
    this.characterTypes = characterTypes;
  }

  public ITemplateType parse(Element element) throws PersistenceException {
    String characterTypeId = ElementUtilities.getRequiredAttrib(element, "characterType"); //$NON-NLS-1$
    ICharacterType characterType = characterTypes.findById(characterTypeId);
    String subtemplate = element.attributeValue("subtemplate"); //$NON-NLS-1$
    Identified subtemplateIdentificate = createSubtemplateIdentificate(subtemplate);
    return new TemplateType(characterType, subtemplateIdentificate);
  }

  private Identified createSubtemplateIdentificate(String subtemplate) {
    if (subtemplate == null || subtemplate.equals("default")) { //$NON-NLS-1$
      return TemplateType.DEFAULT_SUB_TYPE;
    }
    return new Identifier(subtemplate);
  }
}
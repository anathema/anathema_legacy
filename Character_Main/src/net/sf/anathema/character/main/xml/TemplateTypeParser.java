package net.sf.anathema.character.main.xml;

import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class TemplateTypeParser {

  private CharacterTypes characterTypes;

  public TemplateTypeParser(CharacterTypes characterTypes) {
    this.characterTypes = characterTypes;
  }

  public ITemplateType parse(Element element) throws PersistenceException {
    String characterTypeId = ElementUtilities.getRequiredAttrib(element, "characterType");
    ICharacterType characterType = characterTypes.findById(characterTypeId);
    String subtemplate = element.attributeValue("subtemplate");
    Identifier subtemplateIdentificate = createSubtemplateIdentificate(subtemplate);
    return new TemplateType(characterType, subtemplateIdentificate);
  }

  private Identifier createSubtemplateIdentificate(String subtemplate) {
    if (subtemplate == null || subtemplate.equals("default")) {
      return TemplateType.DEFAULT_SUB_TYPE;
    }
    return new SimpleIdentifier(subtemplate);
  }
}
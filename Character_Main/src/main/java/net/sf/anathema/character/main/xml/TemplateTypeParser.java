package net.sf.anathema.character.main.xml;

import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.template.TemplateTypeImpl;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;
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

  public TemplateType parse(Element element) throws PersistenceException {
    String characterTypeId = ElementUtilities.getRequiredAttrib(element, "characterType");
    CharacterType characterType = characterTypes.findById(characterTypeId);
    String subtemplateValue = element.attributeValue("subtemplate");
    Identifier subtemplate = new SimpleIdentifier(subtemplateValue);
    return new TemplateTypeImpl(characterType, subtemplate);
  }
}
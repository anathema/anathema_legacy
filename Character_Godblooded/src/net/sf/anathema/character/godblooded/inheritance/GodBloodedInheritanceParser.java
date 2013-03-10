package net.sf.anathema.character.godblooded.inheritance;

import net.sf.anathema.character.generic.framework.module.CharacterTemplateParser;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import org.dom4j.Element;

@CharacterTemplateParser(modelId = GodBloodedInheritanceTemplate.ID)
public class GodBloodedInheritanceParser implements IAdditionalTemplateParser {
  @Override
  public IAdditionalTemplate parse(Element element) {
    return new GodBloodedInheritanceTemplate();
  }
}
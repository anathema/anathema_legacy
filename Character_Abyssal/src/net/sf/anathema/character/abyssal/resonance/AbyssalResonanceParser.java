package net.sf.anathema.character.abyssal.resonance;

import net.sf.anathema.character.generic.framework.module.CharacterTemplateParser;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import org.dom4j.Element;

@CharacterTemplateParser(modelId = AbyssalResonanceTemplate.ID)
public class AbyssalResonanceParser implements IAdditionalTemplateParser {

  @Override
  public IAdditionalTemplate parse(Element element) {
    return new AbyssalResonanceTemplate();
  }
}
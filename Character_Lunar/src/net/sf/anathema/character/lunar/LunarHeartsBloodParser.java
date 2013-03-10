package net.sf.anathema.character.lunar;

import net.sf.anathema.character.generic.framework.module.CharacterTemplateParser;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import org.dom4j.Element;

@CharacterTemplateParser(modelId = HeartsBloodTemplate.TEMPLATE_ID)
public class LunarHeartsBloodParser implements IAdditionalTemplateParser {

  @Override
  public IAdditionalTemplate parse(Element element) {
    return new HeartsBloodTemplate();
  }
}
package net.sf.anathema.character.solar;

import net.sf.anathema.character.generic.framework.module.CharacterTemplateParser;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import org.dom4j.Element;

@CharacterTemplateParser(modelId = SolarVirtueFlawTemplate.ID)
public class SolarVirtueFlawParser implements IAdditionalTemplateParser {

  @Override
  public IAdditionalTemplate parse(Element element) {
    return new SolarVirtueFlawTemplate();
  }
}
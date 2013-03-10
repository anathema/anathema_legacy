package net.sf.anathema.character.db.virtueflaw;

import net.sf.anathema.character.generic.framework.module.CharacterTemplateParser;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import org.dom4j.Element;

@CharacterTemplateParser(modelId = DbVirtueFlawTemplate.TEMPLATE_ID)
public class DbVirtueFlawParser implements IAdditionalTemplateParser {

  @Override
  public IAdditionalTemplate parse(Element element) {
    return new DbVirtueFlawTemplate();
  }
}
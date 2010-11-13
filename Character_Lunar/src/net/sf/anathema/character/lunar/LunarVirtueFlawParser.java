package net.sf.anathema.character.lunar;

import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;

import org.dom4j.Element;

public class LunarVirtueFlawParser implements IAdditionalTemplateParser {

  public IAdditionalTemplate parse(Element element) {
    return new LunarVirtueFlawTemplate();
  }
}
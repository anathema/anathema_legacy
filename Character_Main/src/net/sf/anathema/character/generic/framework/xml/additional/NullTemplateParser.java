package net.sf.anathema.character.generic.framework.xml.additional;

import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import org.dom4j.Element;

public class NullTemplateParser implements IAdditionalTemplateParser {

  private String id;

  public NullTemplateParser(String id) {
    this.id = id;
  }

  @Override
  public IAdditionalTemplate parse(Element element) {
    return new NullModelTemplate(id);
  }
}

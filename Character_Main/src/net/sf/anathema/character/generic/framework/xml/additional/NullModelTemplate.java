package net.sf.anathema.character.generic.framework.xml.additional;

import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;

public class NullModelTemplate implements IAdditionalTemplate {

  private final String id;

  public NullModelTemplate(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}

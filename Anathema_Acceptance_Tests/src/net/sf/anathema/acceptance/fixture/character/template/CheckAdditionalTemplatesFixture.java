package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;

public class CheckAdditionalTemplatesFixture extends AbstractTemplateColumnFixture {

  public String id;

  public boolean isTemplateRegistered() {
    for (IAdditionalTemplate template : getTemplate().getAdditionalTemplates()) {
      if (template.getId().equals(id)) {
        return true;
      }
    }
    return false;
  }
}
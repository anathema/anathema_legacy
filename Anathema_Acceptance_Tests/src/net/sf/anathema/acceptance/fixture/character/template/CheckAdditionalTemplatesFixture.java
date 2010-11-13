package net.sf.anathema.acceptance.fixture.character.template;

import java.util.Collection;

import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;

public class CheckAdditionalTemplatesFixture extends AbstractTemplateColumnFixture {

  public String id;

  public boolean isTemplateRegistered() {
    for (IAdditionalTemplate template : getTemplate().getAdditionalTemplates()) {
      if (template.getId().equals(id)) {
        return true;
      }
    }
    Collection<IGlobalAdditionalTemplate> globalTemplates = new CharacterTemplateSummary(summary).getCharacterGenerics()
        .getGlobalAdditionalTemplateRegistry()
        .getAll();
    for (IAdditionalTemplate template : globalTemplates) {
      if (template.getId().equals(id)) {
        return true;
      }
    }
    return false;
  }
}
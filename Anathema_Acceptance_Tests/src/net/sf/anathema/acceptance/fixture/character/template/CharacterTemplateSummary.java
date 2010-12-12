package net.sf.anathema.acceptance.fixture.character.template;

import java.util.Map;

import net.sf.anathema.acceptance.fixture.character.CharacterGenericsSummary;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public class CharacterTemplateSummary extends CharacterGenericsSummary {

  private static final String TEMPLATE_KEY = "template"; //$NON-NLS-1$

  public CharacterTemplateSummary(Map<Object, Object> summary) {
    super(summary);
  }

  public void setCharacterTemplate(ICharacterTemplate template) {
    summary.put(TEMPLATE_KEY, template);
  }

  public ICharacterTemplate getTemplate() {
    return (ICharacterTemplate) summary.get(TEMPLATE_KEY);
  }

  public final ITraitTemplate getTraitTemplate(ITraitType type) {
    ITraitTemplateCollection traitTemplateCollection = getTemplate().getTraitTemplateCollection();
    return traitTemplateCollection.getTraitTemplate(type);
  }
}
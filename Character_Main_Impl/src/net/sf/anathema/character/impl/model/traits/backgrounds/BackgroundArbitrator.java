package net.sf.anathema.character.impl.model.traits.backgrounds;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;

public class BackgroundArbitrator implements IBackgroundArbitrator {

  private ITemplateType templateType;
  private IAdditionalRules additionalRules;

  public BackgroundArbitrator(ICharacterTemplate template) {
    this.templateType = template.getTemplateType();
    this.additionalRules = template.getAdditionalRules();
  }

  @Override
  public boolean accepts(IBackgroundTemplate backgroundTemplate) {
    return backgroundTemplate.acceptsTemplate(templateType) && !additionalRules.isRejected(backgroundTemplate);
  }
}
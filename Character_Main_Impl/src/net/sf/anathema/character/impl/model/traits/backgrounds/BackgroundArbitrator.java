package net.sf.anathema.character.impl.model.traits.backgrounds;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;

public class BackgroundArbitrator implements IBackgroundArbitrator {

  private ITemplateType templateType;
  private IAdditionalRules additionalRules;
  private IExaltedEdition edition;

  public BackgroundArbitrator(ICharacterTemplate template, IExaltedEdition edition) {
    this.templateType = template.getTemplateType();
    this.additionalRules = template.getAdditionalRules();
    this.edition = edition;
  }

  @Override
  public boolean accepts(IBackgroundTemplate backgroundTemplate) {
    return backgroundTemplate.acceptsTemplate(templateType, edition) && !additionalRules.isRejected(backgroundTemplate);
  }
}
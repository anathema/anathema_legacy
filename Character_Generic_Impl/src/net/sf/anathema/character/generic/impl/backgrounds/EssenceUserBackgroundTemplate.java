package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.TemplateType;

public class EssenceUserBackgroundTemplate extends CalculatedLowerableBackground {

  private ITemplateRegistry templateRegistry;

  public EssenceUserBackgroundTemplate(String id, ITemplateRegistry templateRegistry) {
    super(id);
    this.templateRegistry = templateRegistry;
  }

  public boolean acceptsTemplate(TemplateType templateType, IExaltedEdition edition) {
    return templateRegistry.getTemplate(templateType, edition).getEssenceTemplate().isEssenceUser();
  }
}
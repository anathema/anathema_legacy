package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;

public class EssenceUserBackgroundTemplate extends CalculatedLowerableBackground {

  private ITemplateRegistry templateRegistry;

  public EssenceUserBackgroundTemplate(String id, ITemplateRegistry templateRegistry) {
    super(id);
    this.templateRegistry = templateRegistry;
  }

  @Override
  public boolean acceptsTemplate(ITemplateType templateType) {
    return templateRegistry.getTemplate(templateType).getEssenceTemplate().isEssenceUser();
  }
}
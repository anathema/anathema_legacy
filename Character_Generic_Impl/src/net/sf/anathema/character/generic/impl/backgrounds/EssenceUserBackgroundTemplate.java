package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;

public class EssenceUserBackgroundTemplate extends CalculatedLowerableBackground {

  private ITemplateRegistry templateRegistry;

  public EssenceUserBackgroundTemplate(String id, ITemplateRegistry templateRegistry) {
    super(id);
    this.templateRegistry = templateRegistry;
  }

  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
    return templateRegistry.getTemplate(templateType, edition).getEssenceTemplate().isEssenceUser();
  }
  
  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
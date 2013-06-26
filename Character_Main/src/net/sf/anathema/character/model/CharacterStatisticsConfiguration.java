package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

public class CharacterStatisticsConfiguration implements IDialogModelTemplate {

  private HeroTemplate template;

  public HeroTemplate getTemplate() {
    return template;
  }

  public void setTemplate(HeroTemplate template) {
    this.template = template;
  }
}
package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

public class CharacterStatisticsConfiguration implements IDialogModelTemplate {

  private ICharacterTemplate template;

  public ICharacterTemplate getTemplate() {
    return template;
  }

  public void setTemplate(ICharacterTemplate template) {
    this.template = template;
  }
}
package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public class CharacterStatisticsConfiguration implements IAnathemaWizardModelTemplate {

  private ICharacterTemplate template;
  private IExaltedEdition edition = ExaltedEdition.SecondEdition;

  public ICharacterTemplate getTemplate() {
    return template;
  }

  public IExaltedEdition getEdition() {
    return edition;
  }

  public void setTemplate(ICharacterTemplate template) {
    this.template = template;
  }
}
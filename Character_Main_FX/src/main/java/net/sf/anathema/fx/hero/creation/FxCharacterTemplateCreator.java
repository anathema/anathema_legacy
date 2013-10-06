package net.sf.anathema.fx.hero.creation;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.lib.workflow.wizard.selection.CharacterTemplateCreator;
import net.sf.anathema.lib.workflow.wizard.selection.IItemOperator;

public class FxCharacterTemplateCreator implements CharacterTemplateCreator {
  private Resources resources;

  @Override
  public void createTemplate(IItemOperator operator, ICharacterItemCreationModel creationModel) {
    boolean canceled = true; //createView;
    if (canceled) {
      return;
    }
    operator.operate(creationModel.getSelectedTemplate());
  }

  @Override
  public void useResources(Resources resources) {
    this.resources = resources;
  }
}

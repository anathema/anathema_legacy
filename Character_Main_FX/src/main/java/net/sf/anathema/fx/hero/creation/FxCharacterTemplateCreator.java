package net.sf.anathema.fx.hero.creation;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.creation.CharacterCreationPageProperties;
import net.sf.anathema.hero.creation.CharacterCreationPresenter;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.workflow.wizard.selection.CharacterTemplateCreator;
import net.sf.anathema.lib.workflow.wizard.selection.IItemOperator;

public class FxCharacterTemplateCreator implements CharacterTemplateCreator {
  private Resources resources;

  @Override
  public void createTemplate(final IItemOperator operator, final ICharacterItemCreationModel creationModel) {
    final FxCharacterCreationView view = initView(creationModel);
    view.whenCanceled(new Command() {
      @Override
      public void execute() {
        view.close();
      }
    });
    view.whenConfirmed(new Command() {
      @Override
      public void execute() {
        view.close();
        operator.operate(creationModel.getSelectedTemplate());
      }
    });
    view.show();
  }

  private FxCharacterCreationView initView(ICharacterItemCreationModel model) {
    FxCharacterCreationView view = new FxCharacterCreationView();
    new CharacterCreationPresenter(view, new CharacterCreationPageProperties(resources), model).initPresentation();
    return view;
  }

  @Override
  public void useResources(Resources resources) {
    this.resources = resources;
  }
}
package net.sf.anathema.fx.hero.creation;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.workflow.wizard.selection.CharacterTemplateCreator;
import net.sf.anathema.lib.workflow.wizard.selection.IItemOperator;

public class FxCharacterTemplateCreator implements CharacterTemplateCreator {
  private Resources resources;

  @Override
  public void createTemplate(final IItemOperator operator, final ICharacterItemCreationModel creationModel) {
    final Stage stage = new Stage();
    FxCharacterCreationView view = new FxCharacterCreationView();
    Parent parent = view.getNode();
    Scene scene = new Scene(parent);
    stage.setScene(scene);
    view.whenCanceled(new Command() {
      @Override
      public void execute() {
        stage.close();
      }
    });
    view.whenConfirmed(new Command() {
      @Override
      public void execute() {
        stage.close();
        operator.operate(creationModel.getSelectedTemplate());
      }
    });
  }

  @Override
  public void useResources(Resources resources) {
    this.resources = resources;
  }
}
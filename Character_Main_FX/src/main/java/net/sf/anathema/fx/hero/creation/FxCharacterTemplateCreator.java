package net.sf.anathema.fx.hero.creation;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.creation.CharacterCreationPageProperties;
import net.sf.anathema.hero.creation.CharacterCreationPresenter;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.lib.workflow.wizard.selection.CharacterTemplateCreator;
import net.sf.anathema.lib.workflow.wizard.selection.IItemOperator;

public class FxCharacterTemplateCreator implements CharacterTemplateCreator {
  private Environment environment;

  @Override
  public void createTemplate(IItemOperator operator, ICharacterItemCreationModel creationModel) {
    FxCharacterCreationView view = new FxCharacterCreationView();
    CharacterCreationPageProperties properties = new CharacterCreationPageProperties(environment);
    new CharacterCreationPresenter(view, properties, creationModel, operator).initPresentation();
  }

  @Override
  public void useEnvironment(Environment environment) {
    this.environment = environment;
  }
}
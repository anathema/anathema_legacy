package net.sf.anathema.fx.hero.creation;

import javafx.stage.Window;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.creation.CharacterCreationPageProperties;
import net.sf.anathema.hero.creation.CharacterCreationPresenter;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.hero.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.creation.IItemOperator;

public class FxCharacterTemplateCreator implements CharacterTemplateCreator {
  private final Window parent;
  private Environment environment;

  public FxCharacterTemplateCreator(Window parent) {
    this.parent = parent;
  }

  @Override
  public void createTemplate(IItemOperator operator, ICharacterItemCreationModel creationModel) {
    FxCharacterCreationView view = new FxCharacterCreationView(parent);
    CharacterCreationPageProperties properties = new CharacterCreationPageProperties(environment);
    new CharacterCreationPresenter(view, properties, creationModel, operator).initPresentation();
  }

  @Override
  public void useEnvironment(Environment environment) {
    this.environment = environment;
  }
}
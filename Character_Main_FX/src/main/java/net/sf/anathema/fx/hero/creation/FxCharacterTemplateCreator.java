package net.sf.anathema.fx.hero.creation;

import javafx.stage.Window;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.fx.DialogFactory;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.hero.creation.CharacterCreationPageProperties;
import net.sf.anathema.hero.creation.CharacterCreationPresenter;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.hero.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.creation.IItemOperator;

public class FxCharacterTemplateCreator implements CharacterTemplateCreator {
  private Environment environment;
  private final DialogFactory factory;

  public FxCharacterTemplateCreator(DialogFactory factory) {
    this.factory = factory;
  }

  @Override
  public void createTemplate(IItemOperator operator, ICharacterItemCreationModel creationModel) {
    FxCharacterCreationView view = new FxCharacterCreationView(factory);
    CharacterCreationPageProperties properties = new CharacterCreationPageProperties(environment);
    new CharacterCreationPresenter(view, properties, creationModel, operator).initPresentation();
  }

  @Override
  public void useEnvironment(Environment environment) {
    this.environment = environment;
  }
}
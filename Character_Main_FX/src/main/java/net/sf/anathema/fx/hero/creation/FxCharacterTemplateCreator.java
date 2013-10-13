package net.sf.anathema.fx.hero.creation;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.creation.CharacterCreationPageProperties;
import net.sf.anathema.hero.creation.CharacterCreationPresenter;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.lib.workflow.wizard.selection.CharacterTemplateCreator;
import net.sf.anathema.lib.workflow.wizard.selection.IItemOperator;

public class FxCharacterTemplateCreator implements CharacterTemplateCreator {
  private Resources resources;

  @Override
  public void createTemplate(IItemOperator operator, ICharacterItemCreationModel creationModel) {
    FxCharacterCreationView view = new FxCharacterCreationView();
    CharacterCreationPageProperties properties = new CharacterCreationPageProperties(resources);
    new CharacterCreationPresenter(view, properties, creationModel, operator).initPresentation();
  }

  @Override
  public void useResources(Resources resources) {
    this.resources = resources;
  }
}
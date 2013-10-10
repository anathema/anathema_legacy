package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.framework.perspective.model.CharacterItemModel;
import net.sf.anathema.hero.framework.perspective.model.ItemSelectionModel;
import net.sf.anathema.hero.framework.perspective.model.NewCharacterListener;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.wizard.selection.CharacterTemplateCreator;
import net.sf.anathema.swing.hero.creation.SwingCharacterTemplateCreator;

public class NewInteractionPresenter {

  private final ItemSelectionModel model;
  private final Tool interaction;
  private final Environment environment;
  private CharacterGridView view;
  private Selector<CharacterIdentifier> selector;

  public NewInteractionPresenter(ItemSelectionModel model, Tool interaction, Environment environment, CharacterGridView view,
                                 Selector<CharacterIdentifier> selector) {
    this.model = model;
    this.interaction = interaction;
    this.environment = environment;
    this.view = view;
    this.selector = selector;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeCommand();
    model.whenNewCharacterIsAdded(new NewCharacterListener() {
      @Override
      public void added(CharacterItemModel character) {
        new CharacterButtonPresenter(environment, selector, character, view).initPresentation();
        view.selectButton(character.getDescriptiveFeatures().getIdentifier());
        selector.selected(character.getDescriptiveFeatures().getIdentifier());
      }
    });
  }

  private void initializeAppearance() {
    interaction.setTooltip(environment.getString("AnathemaCore.Tools.New.Name"));
    interaction.setIcon(new BasicUi().getNewIconPathForTaskbar());
  }

  private void initializeCommand() {
    interaction.setCommand(new CreateNewCommand());
  }

  private class CreateNewCommand implements Command {
    @Override
    public void execute() {
      //TODO (Swing->FX): Instantiate Creator in the view to decouple the presenter from Swing.
      CharacterTemplateCreator creator = new SwingCharacterTemplateCreator();
      creator.useResources(environment);
      model.createNew(creator, environment);
    }
  }
}
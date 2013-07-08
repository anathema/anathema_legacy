package net.sf.anathema.hero.perspective;

import net.sf.anathema.character.main.perspective.CharacterButtonPresenter;
import net.sf.anathema.character.main.perspective.CharacterGridView;
import net.sf.anathema.character.main.perspective.Selector;
import net.sf.anathema.character.main.perspective.model.CharacterIdentifier;
import net.sf.anathema.character.main.perspective.model.CharacterItemModel;
import net.sf.anathema.character.main.perspective.model.ItemSelectionModel;
import net.sf.anathema.character.main.perspective.model.NewCharacterListener;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.resources.Resources;

public class NewInteractionPresenter {

  private final ItemSelectionModel model;
  private final Tool interaction;
  private Resources resources;
  private CharacterGridView view;
  private Selector<CharacterIdentifier> selector;

  public NewInteractionPresenter(ItemSelectionModel model, Tool interaction, Resources resources, CharacterGridView view,
                                 Selector<CharacterIdentifier> selector) {
    this.model = model;
    this.interaction = interaction;
    this.resources = resources;
    this.view = view;
    this.selector = selector;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeCommand();
    model.whenNewCharacterIsAdded(new NewCharacterListener() {

      @Override
      public void added(CharacterItemModel character) {
        new CharacterButtonPresenter(resources, selector, character, view).initPresentation();
        view.selectButton(character.getDescriptiveFeatures().getIdentifier());
        selector.selected(character.getDescriptiveFeatures().getIdentifier());
      }
    });
  }

  private void initializeAppearance() {
    interaction.setTooltip(resources.getString("AnathemaCore.Tools.New.Name"));
    interaction.setIcon(new BasicUi().getNewIconPathForTaskbar());
  }

  private void initializeCommand() {
    interaction.setCommand(new CreateNewCommand());
  }

  private class CreateNewCommand implements Command {
    @Override
    public void execute() {
      model.createNew(resources);
    }
  }
}

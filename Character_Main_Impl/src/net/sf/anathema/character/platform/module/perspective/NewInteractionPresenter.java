package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.character.perspective.DistinctiveFeatures;
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.character.perspective.ToCharacterButtonDto;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.character.perspective.model.model.NewCharacterListener;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

public class NewInteractionPresenter {

  private final ItemSelectionModel model;
  private final Interaction interaction;
  private IResources resources;
  private CharacterGridView gridViewView;
  private Selector<CharacterIdentifier> selector;

  public NewInteractionPresenter(ItemSelectionModel model, Interaction interaction, IResources resources, CharacterGridView gridViewView,
                                 Selector<CharacterIdentifier> selector) {
    this.model = model;
    this.interaction = interaction;
    this.resources = resources;
    this.gridViewView = gridViewView;
    this.selector = selector;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeCommand();
    model.whenNewCharacterIsAdded(new NewCharacterListener() {

      @Override
      public void added(DistinctiveFeatures distinctiveFeatures) {
        CharacterButtonDto dto = new ToCharacterButtonDto(resources).apply(distinctiveFeatures);
        gridViewView.addAndSelectButton(dto, selector);
        selector.selected(dto.identifier);
      }
    });
  }

  private void initializeAppearance() {
    interaction.setTooltip("AnathemaCore.Tools.New.Name");
    interaction.setIcon("TaskBarNew24.png");
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

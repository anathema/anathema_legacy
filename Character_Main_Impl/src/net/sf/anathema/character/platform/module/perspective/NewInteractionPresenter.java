package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.character.perspective.DescriptiveFeatures;
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.character.perspective.ToCharacterButtonDto;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.CharacterModel;
import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.character.perspective.model.model.NewCharacterListener;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.IChangeListener;
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
      public void added(final CharacterModel character) {
        final DescriptiveFeatures features = character.getDescriptiveFeatures();
        CharacterButtonDto dto = new ToCharacterButtonDto(resources).apply(features);
        gridViewView.addButton(dto, selector);
        gridViewView.selectButton(dto.identifier);
        selector.selected(dto.identifier);
        character.whenFeaturesChange(new IChangeListener() {
          @Override
          public void changeOccurred() {
            gridViewView.setName(features.getIdentifier(), character.getDescriptiveFeatures().getPrintName());
          }
        });
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

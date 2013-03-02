package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.character.perspective.ToCharacterButtonDto;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.character.perspective.model.model.NewCharacterListener;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

import static net.sf.anathema.character.generic.caste.ICasteType.NULL_CASTE_TYPE;

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
      public void added(CharacterIdentifier identifier, String printName, ITemplateType templateType) {
        String pathToImage = ToCharacterButtonDto.getPathToImage(templateType, NULL_CASTE_TYPE);
        String details = ToCharacterButtonDto.getDetails(resources, templateType);
        CharacterButtonDto dto = new CharacterButtonDto(identifier, printName, details, pathToImage);
        gridViewView.addAndSelectButton(dto, selector);
        selector.selected(identifier);
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

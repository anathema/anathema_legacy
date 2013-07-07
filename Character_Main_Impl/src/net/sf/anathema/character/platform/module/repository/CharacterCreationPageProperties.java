package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class CharacterCreationPageProperties {

  private final Resources resources;
  private final CharacterUI iconProvider;

  public CharacterCreationPageProperties(Resources resources) {
    this.resources = resources;
    this.iconProvider = new CharacterUI();
  }

  public String getTitle() {
    return resources.getString("CharacterDialog.Title");
  }

  public IBasicMessage getConfirmMessage() {
    return new BasicMessage(resources.getString("CharacterDialog.Message.Confirm"), MessageType.NORMAL);
  }

  public String getTypeString(ICharacterType type) {
    return resources.getString("CharacterGenerator.NewCharacter." + type.getId() + ".Name");
  }

  public Icon getTypeIcon(ICharacterType type) {
    return iconProvider.getSmallTypeIcon(type);
  }

  public AgnosticUIConfiguration<ITemplateTypeAggregation> getTemplateUI() {
    return new AbstractUIConfiguration<ITemplateTypeAggregation>() {
      @Override
      public String getLabel(ITemplateTypeAggregation value) {
        return resources.getString(value.getPresentationProperties().getNewActionResource());
      }
    };
  }
}
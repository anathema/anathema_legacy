package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;

public class CharacterCreationPageProperties {

  private final IResources resources;
  private final CharacterUI iconProvider;

  public CharacterCreationPageProperties(IResources resources) {
    this.resources = resources;
    this.iconProvider = new CharacterUI(resources);
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

  public ListCellRenderer getTemplateRenderer() {
    return new DefaultListCellRenderer() {

      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String printname = resources.getString(((ITemplateTypeAggregation) value).getPresentationProperties().getNewActionResource());
        return super.getListCellRendererComponent(list, printname, index, isSelected, cellHasFocus);
      }
    };
  }
}
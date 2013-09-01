package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.framework.environment.Resources;

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

  public String getTypeString(CharacterType type) {
    return new CharacterTypeUi(resources).getLabel(type);
  }

  public Icon getTypeIcon(CharacterType type) {
    return iconProvider.getSmallTypeIcon(type);
  }

  public AgnosticUIConfiguration<ITemplateTypeAggregation> getTemplateUI() {
    return new TemplateTypeUiConfiguration(resources);
  }

}
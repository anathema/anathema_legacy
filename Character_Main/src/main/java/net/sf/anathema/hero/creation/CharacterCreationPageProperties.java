package net.sf.anathema.hero.creation;

import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

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

  public String getTypeString(CharacterType type) {
    return new CharacterTypeUi(resources).getLabel(type);
  }

  public RelativePath getTypeIcon(CharacterType type) {
    return iconProvider.getSmallTypeIconPath(type);
  }

  public AgnosticUIConfiguration<HeroTemplate> getTemplateUI() {
    return new TemplateTypeUiConfiguration(resources);
  }

}
package net.sf.anathema.hero.platform;

import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.framework.item.CharacterPrintNameFileScanner;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.resources.Resources;

public class CharacterViewProperties implements IItemTypeViewProperties {

  private final RelativePath icon;
  private final IItemType type;
  private final CharacterFileUi ui;

  public CharacterViewProperties(IItemType type, Resources resources, CharacterPrintNameFileScanner scanner) {
    this.type = type;
    this.icon = new CharacterUI().getCharacterDescriptionTabIcon();
    this.ui = new CharacterFileUi(resources, scanner);
  }

  @Override
  public RelativePath getIcon() {
    return icon;
  }

  @Override
  public AgnosticUIConfiguration<PrintNameFile> getItemTypeUI() {
    return ui;
  }

  @Override
  public String getLabelKey() {
    return "ItemType." + type.getId() + ".PrintName";
  }
}
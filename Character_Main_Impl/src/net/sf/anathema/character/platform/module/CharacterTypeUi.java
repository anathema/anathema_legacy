package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.main.item.CharacterPrintNameFileScanner;
import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class CharacterTypeUi extends AbstractUIConfiguration<PrintNameFile> {

  private final Resources resources;
  private final CharacterPrintNameFileScanner scanner;

  public CharacterTypeUi(Resources resources, CharacterPrintNameFileScanner scanner) {
    this.resources = resources;
    this.scanner = scanner;
  }

  @Override
  public RelativePath getIconsRelativePath(PrintNameFile value) {
    ICharacterType characterType = scanner.getCharacterType(value);
    return new CharacterUI().getSmallTypeIconPath(characterType);
  }

  @Override
  public String getLabel(PrintNameFile value) {
    String printName = value.getPrintName();
    ICharacterType characterType = scanner.getCharacterType(value);
    String characterString = resources.getString("CharacterGenerator.NewCharacter." + characterType.getId() + ".Name");
    Identifier casteType = scanner.getCasteType(value);
    String casteTypeString = resources.getString("Caste." + casteType.getId());
    String casteString = resources.getString(characterType.getId() + ".Caste.Label");
    return resources.getString("LoadCharacter.PrintNameFile.Message", printName, characterString, casteTypeString, casteString);
  }
}
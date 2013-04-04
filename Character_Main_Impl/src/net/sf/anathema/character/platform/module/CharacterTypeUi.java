package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import javax.swing.Icon;

public class CharacterTypeUi implements ObjectUi<Object> {

  private final Resources resources;
  private final CharacterPrintNameFileScanner scanner;

  public CharacterTypeUi(Resources resources, CharacterPrintNameFileScanner scanner) {
    this.resources = resources;
    this.scanner = scanner;
  }

  @Override
  public String getLabel(Object value) {
    PrintNameFile file = (PrintNameFile) value;
    String printName = file.getPrintName();
    ICharacterType characterType = scanner.getCharacterType(file);
    String characterString = resources.getString("CharacterGenerator.NewCharacter." + characterType.getId() + ".Name"); //$NON-NLS-1$//$NON-NLS-2$
    Identified casteType = scanner.getCasteType(file);
    if (casteType == ICasteType.NULL_CASTE_TYPE) {
      return resources.getString("LoadCharacter.PrintNameFile.ShortMessage", printName, characterString);
    }
    String casteTypeString = resources.getString("Caste." + casteType.getId());
    String casteString = resources.getString(characterType.getId() + ".Caste.Label");
    return resources.getString("LoadCharacter.PrintNameFile.Message", printName, characterString, casteTypeString, casteString);
  }

  @Override
  public Icon getIcon(Object value) {
    PrintNameFile file = (PrintNameFile) value;
    ICharacterType characterType = scanner.getCharacterType(file);
    return new CharacterUI().getSmallTypeIcon(characterType);
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}
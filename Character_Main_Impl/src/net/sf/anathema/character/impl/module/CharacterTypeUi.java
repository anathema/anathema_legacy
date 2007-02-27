package net.sf.anathema.character.impl.module;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class CharacterTypeUi implements IObjectUi {

  private final IResources resources;
  private final CharacterPrintNameFileScanner scanner;

  public CharacterTypeUi(IResources resources, CharacterPrintNameFileScanner scanner) {
    this.resources = resources;
    this.scanner = scanner;
  }

  public String getLabel(Object value) {
    PrintNameFile file = (PrintNameFile) value;
    String printName = file.getPrintName();
    ICharacterType characterType = scanner.getCharacterType(file);
    String characterString = resources.getString("CharacterGenerator.NewCharacter." + characterType.getId() + ".Name"); //$NON-NLS-1$//$NON-NLS-2$
    IIdentificate casteType = scanner.getCasteType(file);
    if (casteType == null) {
      return resources.getString("LoadCharacter.PrintNameFile.ShortMessage", new Object[] { //$NON-NLS-1$
          printName, characterString });
    }
    String casteTypeString = resources.getString("Caste." + casteType.getId()); //$NON-NLS-1$
    String casteString = resources.getString(characterType.getId() + ".Caste.Label"); //$NON-NLS-1$
    return resources.getString("LoadCharacter.PrintNameFile.Message", new Object[] { //$NON-NLS-1$
        printName, characterString, casteTypeString, casteString });
  }

  public Icon getIcon(Object value) {
    PrintNameFile file = (PrintNameFile) value;
    ICharacterType characterType = scanner.getCharacterType(file);
    return new CharacterUI(resources).getSmallTypeIcon(characterType);

  }
}
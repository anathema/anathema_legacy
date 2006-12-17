package net.sf.anathema.character.impl.module;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.repository.access.printname.PrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import org.apache.commons.io.FileUtils;

public class CharacterTypeUi implements IObjectUi {

  private final IResources resources;
  private final CharacterPrintNameFileScanner scanner;

  public CharacterTypeUi(IResources resources, CharacterPrintNameFileScanner scanner) {
    this.resources = resources;
    this.scanner = scanner;
  }

  public String getLabel(Object value) {
    PrintNameFile file = (PrintNameFile) value;
    String id = file.getRepositoryId();
    String printName = file.getPrintName();
    CharacterType characterType = getCharacterType(file);
    String characterString = resources.getString("CharacterGenerator.NewCharacter." + characterType.getId() + ".Name"); //$NON-NLS-1$//$NON-NLS-2$
    IIdentificate casteType = scanner.getCasteType(id);
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
    CharacterType characterType = getCharacterType(file);
    return new CharacterUI(resources).getSmallTypeIcon(characterType);

  }

  private CharacterType getCharacterType(PrintNameFile file) {
    try {
      String id = file.getRepositoryId();
      CharacterType characterType = scanner.getCharacterType(id);
      if (characterType == null) {
        String string = FileUtils.readFileToString(new File(file.getFile(), "head.ecg"), PrintNameFileAccess.ENCODING);
        scanner.scan(string, id);
        characterType = scanner.getCharacterType(id);
      }
      return characterType;
    }
    catch (IOException e) {
      return null;
    }
  }
}
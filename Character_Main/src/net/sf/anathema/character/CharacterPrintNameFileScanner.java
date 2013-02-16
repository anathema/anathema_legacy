package net.sf.anathema.character;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.util.Identified;

public interface CharacterPrintNameFileScanner {
  ICharacterType getCharacterType(PrintNameFile file);

  Identified getCasteType(PrintNameFile file);

  ITemplateType getTemplateType(PrintNameFile file);
}
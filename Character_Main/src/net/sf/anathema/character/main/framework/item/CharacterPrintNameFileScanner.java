package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.util.Identifier;

public interface CharacterPrintNameFileScanner {
  CharacterType getCharacterType(PrintNameFile file);

  Identifier getCasteType(PrintNameFile file);

  ITemplateType getTemplateType(PrintNameFile file);
}
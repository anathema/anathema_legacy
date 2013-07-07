package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.util.Identifier;

public interface CharacterPrintNameFileScanner {
  ICharacterType getCharacterType(PrintNameFile file);

  Identifier getCasteType(PrintNameFile file);

  ITemplateType getTemplateType(PrintNameFile file);
}
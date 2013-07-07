package net.sf.anathema.character.main.item;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.util.Identifier;

public interface CharacterPrintNameFileScanner {
  ICharacterType getCharacterType(PrintNameFile file);

  Identifier getCasteType(PrintNameFile file);

  ITemplateType getTemplateType(PrintNameFile file);
}
package net.sf.anathema.hero.repositorytree;

import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.framework.item.CharacterReferenceScanner;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.repository.access.printname.SimpleRepositoryId;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.framework.perspective.model.CharacterReference;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.hero.creation.CharacterTypeUi;

public class CharacterFileUi extends AbstractUIConfiguration<PrintNameFile> {

  private final Resources resources;
  private final CharacterReferenceScanner scanner;

  public CharacterFileUi(Resources resources, CharacterReferenceScanner scanner) {
    this.resources = resources;
    this.scanner = scanner;
  }

  @Override
  public RelativePath getIconsRelativePath(PrintNameFile value) {
    CharacterType characterType = scanner.getCharacterType(createReference(value));
    return new CharacterUI().getSmallTypeIconPath(characterType);
  }

  @Override
  public String getLabel(PrintNameFile value) {
    String printName = value.getPrintName();
    CharacterReference reference = createReference(value);
    CharacterType characterType = scanner.getCharacterType(reference);
    String characterString = new CharacterTypeUi(resources).getLabel(characterType);
    Identifier casteType = scanner.getCasteType(reference);
    if (casteType == CasteType.NULL_CASTE_TYPE) {
      return resources.getString("LoadCharacter.PrintNameFile.ShortMessage", printName, characterString);
    }
    String casteTypeString = resources.getString("Caste." + casteType.getId());
    String casteString = resources.getString(characterType.getId() + ".Caste.Label");
    return resources.getString("LoadCharacter.PrintNameFile.Message", printName, characterString, casteTypeString, casteString);
  }

  private CharacterReference createReference(PrintNameFile value) {
    return new CharacterReference(new SimpleRepositoryId(value.getRepositoryId()), value.getPrintName());
  }
}
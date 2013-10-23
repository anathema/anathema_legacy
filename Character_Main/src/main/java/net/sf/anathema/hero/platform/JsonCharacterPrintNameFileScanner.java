package net.sf.anathema.hero.platform;

import net.sf.anathema.character.main.framework.item.CharacterPrintNameFileScanner;
import net.sf.anathema.character.main.persistence.HeroMainFileDto;
import net.sf.anathema.character.main.persistence.HeroMainFilePersister;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.hero.concept.CasteType.NULL_CASTE_TYPE;

public class JsonCharacterPrintNameFileScanner implements CharacterPrintNameFileScanner {

  private final Map<PrintNameFile, ITemplateType> typesByFile = new HashMap<>();
  private final Map<PrintNameFile, Identifier> castesByFile = new HashMap<>();
  private final IRepositoryFileResolver resolver;
  private final CharacterTypes characterTypes;

  public JsonCharacterPrintNameFileScanner(CharacterTypes characterTypes, IRepositoryFileResolver repositoryFileResolver) {
    this.characterTypes = characterTypes;
    this.resolver = repositoryFileResolver;
  }

  private void scan(PrintNameFile file) throws IOException {
    File scanFile = resolver.getMainFile(file.getItemType().getRepositoryConfiguration(), file.getRepositoryId());
    FileInputStream stream = new FileInputStream(scanFile);
    HeroMainFileDto mainFileDto = new HeroMainFilePersister().load(stream);
    CharacterType characterType = characterTypes.findById(mainFileDto.characterType.characterType);
    SimpleIdentifier subType = new SimpleIdentifier(mainFileDto.characterType.subType);
    typesByFile.put(file, new TemplateType(characterType, subType));
    castesByFile.put(file, NULL_CASTE_TYPE);
  }

  @Override
  public CharacterType getCharacterType(PrintNameFile file) {
    ITemplateType templateType = getTemplateType(file);
    if (templateType == null) {
      return null;
    }
    return templateType.getCharacterType();
  }

  @Override
  public ITemplateType getTemplateType(PrintNameFile file) {
    if (typesByFile.containsKey(file)) {
      return typesByFile.get(file);
    }
    try {
      scan(file);
      return typesByFile.get(file);
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public Identifier getCasteType(PrintNameFile file) {
    if (castesByFile.containsKey(file)) {
      return castesByFile.get(file);
    }
    try {
      scan(file);
      return castesByFile.get(file);
    } catch (IOException e) {
      return NULL_CASTE_TYPE;
    }
  }
}
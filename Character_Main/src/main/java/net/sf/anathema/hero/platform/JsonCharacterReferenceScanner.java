package net.sf.anathema.hero.platform;

import net.sf.anathema.character.main.framework.item.CharacterReferenceScanner;
import net.sf.anathema.character.main.persistence.HeroMainFileDto;
import net.sf.anathema.character.main.persistence.HeroMainFilePersister;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.hero.framework.perspective.model.CharacterReference;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.character.main.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;
import static net.sf.anathema.hero.concept.CasteType.NULL_CASTE_TYPE;

public class JsonCharacterReferenceScanner implements CharacterReferenceScanner {

  private final Map<CharacterReference, ITemplateType> typesByFile = new HashMap<>();
  private final Map<CharacterReference, Identifier> castesByFile = new HashMap<>();
  private final IRepositoryFileResolver resolver;
  private final CharacterTypes characterTypes;

  public JsonCharacterReferenceScanner(CharacterTypes characterTypes, IRepositoryFileResolver repositoryFileResolver) {
    this.characterTypes = characterTypes;
    this.resolver = repositoryFileResolver;
  }

  private void scan(CharacterReference reference) throws IOException {
    File scanFile = resolver.getMainFile(retrieveCharacterItemType().getRepositoryConfiguration(), reference.repositoryId.getStringRepresentation());
    try (FileInputStream stream = new FileInputStream(scanFile)) {
      HeroMainFileDto mainFileDto = new HeroMainFilePersister().load(stream);
      CharacterType characterType = characterTypes.findById(mainFileDto.characterType.characterType);
      SimpleIdentifier subType = new SimpleIdentifier(mainFileDto.characterType.subType);
      typesByFile.put(reference, new TemplateType(characterType, subType));
      castesByFile.put(reference, NULL_CASTE_TYPE);
    }
  }

  @Override
  public CharacterType getCharacterType(CharacterReference reference) {
    ITemplateType templateType = getTemplateType(reference);
    if (templateType == null) {
      return null;
    }
    return templateType.getCharacterType();
  }

  @Override
  public ITemplateType getTemplateType(CharacterReference reference) {
    if (typesByFile.containsKey(reference)) {
      return typesByFile.get(reference);
    }
    try {
      scan(reference);
      return typesByFile.get(reference);
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public Identifier getCasteType(CharacterReference reference) {
    if (castesByFile.containsKey(reference)) {
      return castesByFile.get(reference);
    }
    try {
      scan(reference);
      return castesByFile.get(reference);
    } catch (IOException e) {
      return NULL_CASTE_TYPE;
    }
  }
}
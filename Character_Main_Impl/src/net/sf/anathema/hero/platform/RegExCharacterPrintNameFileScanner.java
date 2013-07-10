package net.sf.anathema.hero.platform;

import net.sf.anathema.character.main.framework.item.CharacterPrintNameFileScanner;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_SUB_TYPE;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_CHARACTER_TYPE;
import static net.sf.anathema.hero.concept.CasteType.NULL_CASTE_TYPE;

public class RegExCharacterPrintNameFileScanner implements CharacterPrintNameFileScanner {

  private static final String TYPE_ELEMENT_NAME = TAG_CHARACTER_TYPE;
  private final Map<PrintNameFile, ITemplateType> typesByFile = new HashMap<>();
  private final Map<PrintNameFile, Identifier> castesByFile = new HashMap<>();
  private final IRepositoryFileResolver resolver;
  private final CharacterTypes characterTypes;

  public RegExCharacterPrintNameFileScanner(CharacterTypes characterTypes, IRepositoryFileResolver repositoryFileResolver) {
    this.characterTypes = characterTypes;
    this.resolver = repositoryFileResolver;
  }

  private void scan(PrintNameFile file) throws IOException {
    Document document;
    try {
      File scanFile = resolver.getMainFile(file.getItemType().getRepositoryConfiguration(), file.getRepositoryId());
      document = DocumentUtilities.read(scanFile.toPath());
    } catch (AnathemaException ex) {
      return;
    }

    Element typeElement = DocumentUtilities.findElement(document, TYPE_ELEMENT_NAME);

    String typeString = typeElement != null ? typeElement.getText() : null;

    if (typeString == null) {
      throw new IllegalStateException("Missing Hero type in " + file);
    }

    ICharacterType characterType = characterTypes.findById(typeString);
    SimpleIdentifier subType = new SimpleIdentifier(typeElement.attributeValue(ATTRIB_SUB_TYPE));
    typesByFile.put(file, new TemplateType(characterType, subType));
    castesByFile.put(file, NULL_CASTE_TYPE);
  }

  @Override
  public ICharacterType getCharacterType(PrintNameFile file) {
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
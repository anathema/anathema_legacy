package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.repository.access.printname.PrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.apache.commons.io.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.sf.anathema.character.generic.caste.ICasteType.NULL_CASTE_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_SUB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CASTE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARACTER_TYPE;

public class RegExCharacterPrintNameFileScanner implements CharacterPrintNameFileScanner {

  private static final String TYPE_ELEMENT_NAME = TAG_CHARACTER_TYPE;
  private static final String CASTE_ELEMENT_NAME = TAG_CASTE;
  private static final String CASTE_ELEMENT_TYPE_ATTR = ATTRIB_TYPE;
  private static final Pattern typePattern =
          Pattern.compile("<" + TYPE_ELEMENT_NAME + ATTRIB_SUB_TYPE + "=\"(.*?)\"" + ".*>(.*?)</" + TYPE_ELEMENT_NAME + ">");
  private static final Pattern castePattern = Pattern.compile("<" + CASTE_ELEMENT_NAME + " " + CASTE_ELEMENT_TYPE_ATTR + "=\"(.*?)\"/>");
  private final Map<PrintNameFile, ITemplateType> typesByFile = new HashMap<>();
  private final Map<PrintNameFile, Identified> castesByFile = new HashMap<>();
  private final IRegistry<ICharacterType, ICasteCollection> registry;
  private final IRepositoryFileResolver resolver;
  private final CharacterTypes characterTypes;

  public RegExCharacterPrintNameFileScanner(CharacterTypes characterTypes, IRegistry<ICharacterType, ICasteCollection> registry,
                                            IRepositoryFileResolver repositoryFileResolver) {
    this.characterTypes = characterTypes;
    this.registry = registry;
    this.resolver = repositoryFileResolver;
  }

  private void scan(PrintNameFile file) throws IOException {
    Document document;
    try {
      File scanFile = resolver.getMainFile(file.getItemType(), file.getRepositoryId());
      document = DocumentUtilities.read(scanFile.toPath());
    } catch (AnathemaException ex) {
      scanCompatible(file);
      return;
    }

    Element typeElement = DocumentUtilities.findElement(document, TYPE_ELEMENT_NAME);
    Element casteElement = DocumentUtilities.findElement(document, CASTE_ELEMENT_NAME);

    String typeString = typeElement != null ? typeElement.getText() : null;

    Attribute casteTypeAttr = casteElement != null ? casteElement.attribute(CASTE_ELEMENT_TYPE_ATTR) : null;

    String casteTypeStr = casteTypeAttr != null ? casteTypeAttr.getValue() : null;

    if (typeString == null) {
      throw new IllegalStateException("Missing Character Type in " + file);
    }

    ICharacterType characterType = characterTypes.findById(typeString);
    Identifier subType = new Identifier(typeElement.attributeValue(ATTRIB_SUB_TYPE));
    typesByFile.put(file, new TemplateType(characterType, subType));

    if (casteTypeStr == null) {
      castesByFile.put(file, NULL_CASTE_TYPE);
      return;
    }

    Identified casteType = registry.get(characterType).getById(casteTypeStr);
    castesByFile.put(file, casteType);
  }

  // Used only by scan as a fall-back method for backward compatibility when all fails.
  private void scanCompatible(PrintNameFile file) throws IOException {
    File scanFile = resolver.getMainFile(file.getItemType(), file.getRepositoryId());
    String content = FileUtils.readFileToString(scanFile, PrintNameFileAccess.COMPATIBILITY_ENCODING);
    Matcher typeMatcher = typePattern.matcher(content);
    typeMatcher.find();
    ICharacterType characterType;
    characterType = characterTypes.findById(typeMatcher.group(1));
    Identifier subType = new Identifier(typeMatcher.group(1));
    typesByFile.put(file, new TemplateType(characterType, subType));
    Matcher casteMatcher = castePattern.matcher(content);
    if (!casteMatcher.find()) {
      castesByFile.put(file, null);
      return;
    }
    Identified casteType = registry.get(characterType).getById(casteMatcher.group(1));
    castesByFile.put(file, casteType);
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
    ITemplateType templateType = typesByFile.get(file);
    if (templateType != null) {
      return templateType;
    }
    try {
      scan(file);
      return typesByFile.get(file);
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public Identified getCasteType(PrintNameFile file) {
    Identified caste = castesByFile.get(file);
    if (caste != null) {
      return caste;
    }
    try {
      scan(file);
      return castesByFile.get(file);
    } catch (IOException e) {
      return NULL_CASTE_TYPE;
    }
  }
}
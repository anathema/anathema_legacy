package net.sf.anathema.character.impl.module;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.repository.access.printname.PrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.apache.commons.io.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

public class CharacterPrintNameFileScanner {

  private static final String TYPE_ELEMENT_NAME = "CharacterType";
  private static final String CASTE_ELEMENT_NAME = "Caste";
  private static final String CASTE_ELEMENT_TYPE_ATTR = "type";
  private static final Pattern typePattern = Pattern.compile("<" + TYPE_ELEMENT_NAME + ".*>(.*?)</CharacterType>"); //$NON-NLS-1$
  private static final Pattern castePattern = Pattern.compile("<" + CASTE_ELEMENT_NAME + " " + CASTE_ELEMENT_TYPE_ATTR + "=\"(.*?)\"/>"); //$NON-NLS-1$
  private final Map<PrintNameFile, ICharacterType> typesByFile = new HashMap<PrintNameFile, ICharacterType>();
  private final Map<PrintNameFile, IIdentificate> castesByFile = new HashMap<PrintNameFile, IIdentificate>();
  private final IRegistry<ICharacterType, ICasteCollection> registry;
  private final IRepositoryFileResolver resolver;

  public CharacterPrintNameFileScanner(
      IRegistry<ICharacterType, ICasteCollection> registry,
      IRepositoryFileResolver repositoryFileResolver) {
    this.registry = registry;
    this.resolver = repositoryFileResolver;
  }
  
  private void scan(PrintNameFile file) throws IOException {
    Document document;
    try {
      File scanFile = resolver.getMainFile(file.getItemType(), file.getRepositoryId());
      document = DocumentUtilities.read(scanFile);
    } catch (AnathemaException ex) {
      scanCompatible(file);
      return;
    }
    
    Element typeElement = DocumentUtilities.findElement(document, TYPE_ELEMENT_NAME);
    Element casteElement = DocumentUtilities.findElement(document, CASTE_ELEMENT_NAME);
    
    String typeStr = typeElement != null 
        ? typeElement.getText() 
        : null;
        
    Attribute casteTypeAttr = casteElement != null 
        ? casteElement.attribute(CASTE_ELEMENT_TYPE_ATTR)
        : null;
        
    String casteTypeStr = casteTypeAttr != null
        ? casteTypeAttr.getValue()
        : null;
        
    if (typeStr == null) {
      // Imitate the old behaviour
      throw new IllegalStateException("Missing " + TYPE_ELEMENT_NAME + " in " + file);
    }
    
    ICharacterType characterType = CharacterType.getById(typeStr);
    typesByFile.put(file, characterType);
    
    if (casteTypeStr == null) {
      castesByFile.put(file, null);
      return;
    }
    
    IIdentificate casteType = registry.get(characterType).getById(casteTypeStr);
    castesByFile.put(file, casteType);
  }
  

  // Used only by scan as a fall-back method for backward compatibility when all fails.
  private void scanCompatible(PrintNameFile file) throws IOException {
    File scanFile = resolver.getMainFile(file.getItemType(), file.getRepositoryId());
    String content = FileUtils.readFileToString(scanFile, PrintNameFileAccess.COMPATIBILITY_ENCODING);
    Matcher typeMatcher = typePattern.matcher(content);
    ICharacterType characterType;
    typeMatcher.find();
    characterType = CharacterType.getById(typeMatcher.group(1));
    typesByFile.put(file, characterType);
    Matcher casteMatcher = castePattern.matcher(content);
    if (!casteMatcher.find()) {
      castesByFile.put(file, null);
      return;
    }
    IIdentificate casteType = registry.get(characterType).getById(casteMatcher.group(1));
    castesByFile.put(file, casteType);
  }

  public ICharacterType getCharacterType(PrintNameFile file) {
    ICharacterType characterType = typesByFile.get(file);
    if (characterType != null) {
      return characterType;
    }
    try {
      scan(file);
      return typesByFile.get(file);
    }
    catch (IOException e) {
      return null;
    }
  }

  public IIdentificate getCasteType(PrintNameFile file) {
    IIdentificate caste = castesByFile.get(file);
    if (caste != null) {
      return caste;
    }
    try {
      scan(file);
      return castesByFile.get(file);
    }
    catch (IOException e) {
      return null;
    }
  }
}
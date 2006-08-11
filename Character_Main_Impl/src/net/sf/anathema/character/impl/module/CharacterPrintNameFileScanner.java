package net.sf.anathema.character.impl.module;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileScanner;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.IIdentificate;

public class CharacterPrintNameFileScanner implements IPrintNameFileScanner {

  private static final Pattern typePattern = Pattern.compile("<CharacterType.*>(.*?)</CharacterType>"); //$NON-NLS-1$
  private static final Pattern castePattern = Pattern.compile("<Caste type=\"(.*?)\"/>"); //$NON-NLS-1$
  private final Map<String, CharacterType> typesByFile = new HashMap<String, CharacterType>();
  private final Map<String, IIdentificate> castesByFile = new HashMap<String, IIdentificate>();
  private final IRegistry<CharacterType, ICasteCollection> registry;

  public CharacterPrintNameFileScanner(IRegistry<CharacterType, ICasteCollection> registry) {
    this.registry = registry;
  }

  public void scan(String content, String repositoryId) {
    Matcher typeMatcher = typePattern.matcher(content);
    CharacterType characterType;
    typeMatcher.find();
    characterType = CharacterType.getById(typeMatcher.group(1));
    typesByFile.put(repositoryId, characterType);
    Matcher casteMatcher = castePattern.matcher(content);
    if (!casteMatcher.find()) {
      castesByFile.put(repositoryId, null);
      return;
    }
    IIdentificate casteType = registry.get(characterType).getById(casteMatcher.group(1));
    castesByFile.put(repositoryId, casteType);
  }

  public CharacterType getCharacterType(String repositoryId) {
    return typesByFile.get(repositoryId);
  }

  public IIdentificate getCasteType(String repositoryId) {
    return castesByFile.get(repositoryId);
  }
}
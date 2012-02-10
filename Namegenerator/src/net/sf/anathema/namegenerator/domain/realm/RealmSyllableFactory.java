package net.sf.anathema.namegenerator.domain.realm;

import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.random.RandomUtilities;
import net.sf.anathema.namegenerator.domain.general.INameTokenFactory;
import net.sf.anathema.namegenerator.domain.general.RandomChoosingTokenFactory;
import net.sf.anathema.namegenerator.domain.syllable.ISyllableFactory;

public class RealmSyllableFactory implements ISyllableFactory {

  private final INameTokenFactory uncleanFactory = new RandomChoosingTokenFactory(new String[] { "a", //$NON-NLS-1$
      "ba", //$NON-NLS-1$
      "be", //$NON-NLS-1$
      "bi", //$NON-NLS-1$
      "bo", //$NON-NLS-1$
      "bu", //$NON-NLS-1$
      "cha", //$NON-NLS-1$
      "che", //$NON-NLS-1$
      "chi", //$NON-NLS-1$
      "cho", //$NON-NLS-1$
      "chu", //$NON-NLS-1$
      "da", //$NON-NLS-1$
      "de", //$NON-NLS-1$
      "di", //$NON-NLS-1$
      "do", //$NON-NLS-1$
      "du", //$NON-NLS-1$
      "e", //$NON-NLS-1$
      "fa", //$NON-NLS-1$
      "fe", //$NON-NLS-1$
      "fi", //$NON-NLS-1$
      "fo", //$NON-NLS-1$
      "fu", //$NON-NLS-1$
      "ga", //$NON-NLS-1$
      "ge", //$NON-NLS-1$
      "gi", //$NON-NLS-1$
      "go", //$NON-NLS-1$
      "gu", //$NON-NLS-1$
      "ha", //$NON-NLS-1$
      "he", //$NON-NLS-1$
      "hi", //$NON-NLS-1$
      "ho", //$NON-NLS-1$
      "hu", //$NON-NLS-1$
      "i", //$NON-NLS-1$
      "ka", //$NON-NLS-1$
      "ke", //$NON-NLS-1$
      "ki", //$NON-NLS-1$
      "ko", //$NON-NLS-1$
      "ku", //$NON-NLS-1$
      "la", //$NON-NLS-1$
      "le", //$NON-NLS-1$
      "li", //$NON-NLS-1$
      "lo", //$NON-NLS-1$
      "lu", //$NON-NLS-1$
      "ma", //$NON-NLS-1$
      "me", //$NON-NLS-1$
      "mi", //$NON-NLS-1$
      "mo", //$NON-NLS-1$
      "mu", //$NON-NLS-1$
      "na", //$NON-NLS-1$
      "ne", //$NON-NLS-1$
      "ni", //$NON-NLS-1$
      "no", //$NON-NLS-1$
      "nu", //$NON-NLS-1$
      "o", //$NON-NLS-1$
      "pa", //$NON-NLS-1$
      "pe", //$NON-NLS-1$
      "pi", //$NON-NLS-1$
      "po", //$NON-NLS-1$
      "pu", //$NON-NLS-1$
      "ra", //$NON-NLS-1$
      "re", //$NON-NLS-1$
      "ri", //$NON-NLS-1$
      "ro", //$NON-NLS-1$
      "ru", //$NON-NLS-1$
      "sa", //$NON-NLS-1$
      "se", //$NON-NLS-1$
      "si", //$NON-NLS-1$
      "so", //$NON-NLS-1$
      "su", //$NON-NLS-1$
      "ta", //$NON-NLS-1$
      "te", //$NON-NLS-1$
      "ti", //$NON-NLS-1$
      "to", //$NON-NLS-1$
      "tu", //$NON-NLS-1$
      "u", //$NON-NLS-1$
      "wa", //$NON-NLS-1$
      "we", //$NON-NLS-1$
      "wi", //$NON-NLS-1$
      "wo", //$NON-NLS-1$
      "wu", //$NON-NLS-1$
      "xa", //$NON-NLS-1$
      "xe", //$NON-NLS-1$
      "xi", //$NON-NLS-1$
      "xo", //$NON-NLS-1$
      "xu", //$NON-NLS-1$
      "ya", //$NON-NLS-1$
      "ye", //$NON-NLS-1$
      "yi", //$NON-NLS-1$
      "yo", //$NON-NLS-1$
      "yu" //$NON-NLS-1$
  });

  public String createToken(String lastSyllable) {
    String uncleanSyllable = uncleanFactory.createToken();
    if (lastSyllable == null || lastSyllable.length() == 0 || uncleanSyllable.length() == 1) {
      return uncleanSyllable;
    }
    char lastNameCharacter = AnathemaStringUtilities.lastCharacter(lastSyllable);
    char lastUncleanCharacter = AnathemaStringUtilities.lastCharacter(uncleanSyllable);
    if (lastNameCharacter != lastUncleanCharacter || RandomUtilities.nextPercent() < 50) {
      return uncleanSyllable;
    }
    return AnathemaStringUtilities.cutOffLastCharacters(uncleanSyllable, 1);
  }
}
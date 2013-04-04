package net.sf.anathema.namegenerator.domain.realm;

import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.util.RandomUtilities;
import net.sf.anathema.namegenerator.domain.general.INameTokenFactory;
import net.sf.anathema.namegenerator.domain.general.RandomChoosingTokenFactory;
import net.sf.anathema.namegenerator.domain.syllable.ISyllableFactory;

public class RealmSyllableFactory implements ISyllableFactory {

  private final INameTokenFactory uncleanFactory = new RandomChoosingTokenFactory(new String[] { "a",
      "ba",
      "be",
      "bi",
      "bo",
      "bu",
      "cha",
      "che",
      "chi",
      "cho",
      "chu",
      "da",
      "de",
      "di",
      "do",
      "du",
      "e",
      "fa",
      "fe",
      "fi",
      "fo",
      "fu",
      "ga",
      "ge",
      "gi",
      "go",
      "gu",
      "ha",
      "he",
      "hi",
      "ho",
      "hu",
      "i",
      "ka",
      "ke",
      "ki",
      "ko",
      "ku",
      "la",
      "le",
      "li",
      "lo",
      "lu",
      "ma",
      "me",
      "mi",
      "mo",
      "mu",
      "na",
      "ne",
      "ni",
      "no",
      "nu",
      "o",
      "pa",
      "pe",
      "pi",
      "po",
      "pu",
      "ra",
      "re",
      "ri",
      "ro",
      "ru",
      "sa",
      "se",
      "si",
      "so",
      "su",
      "ta",
      "te",
      "ti",
      "to",
      "tu",
      "u",
      "wa",
      "we",
      "wi",
      "wo",
      "wu",
      "xa",
      "xe",
      "xi",
      "xo",
      "xu",
      "ya",
      "ye",
      "yi",
      "yo",
      "yu"
  });

  @Override
  public String createToken(String lastSyllable) {
    String uncleanSyllable = uncleanFactory.createToken();
    if (lastSyllable == null || lastSyllable.length() == 0 || uncleanSyllable.length() == 1) {
      return uncleanSyllable;
    }
    char lastNameCharacter = StringUtilities.lastCharacter(lastSyllable);
    char lastUncleanCharacter = StringUtilities.lastCharacter(uncleanSyllable);
    if (lastNameCharacter != lastUncleanCharacter || RandomUtilities.nextPercent() < 50) {
      return uncleanSyllable;
    }
    return StringUtilities.cutOffLastCharacters(uncleanSyllable, 1);
  }
}
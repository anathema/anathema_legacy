package net.sf.anathema.namegenerator.domain.realm;

import net.sf.anathema.lib.util.RandomUtilities;
import net.sf.anathema.namegenerator.domain.syllable.IWordFactory;
import net.sf.anathema.namegenerator.domain.syllable.SimpleWordFactory;

public class RealmWordFactory implements IWordFactory {

  private final String[] commonFamilyNames = new String[] { "Cathak",
      "Cynis",
      "Iselsi",
      "Ledaal",
      "Mnemon",
      "Nellens",
      "Peleps",
      "Ragara",
      "Sesus",
      "Tepet",
      "V'Neef" };

  private final SimpleWordFactory wordFactory = new SimpleWordFactory(
      new RealmSyllableFactory(),
      new RealmSyllableCalculator());

  private final int commonFamilyPercent;

  public RealmWordFactory(int commonFamilyPercent) {
    this.commonFamilyPercent = commonFamilyPercent;
  }

  @Override
  public String createWord(int wordIndex) {
    if (wordIndex == 0 && RandomUtilities.nextPercent() < commonFamilyPercent) {
      return RandomUtilities.choose(commonFamilyNames);
    }
    return wordFactory.createWord(wordIndex);
  }
}
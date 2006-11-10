package net.sf.anathema.namegenerator.domain.realm;

import net.sf.anathema.lib.random.RandomUtilities;
import net.sf.anathema.namegenerator.domain.syllable.IWordFactory;
import net.sf.anathema.namegenerator.domain.syllable.SimpleWordFactory;

public class RealmWordFactory implements IWordFactory {

  private final String[] commonFamilyNames = new String[] { "Cathak", //$NON-NLS-1$
      "Cynis", //$NON-NLS-1$
      "Iselsi", //$NON-NLS-1$
      "Ledaal", //$NON-NLS-1$
      "Mnemon", //$NON-NLS-1$
      "Nellens", //$NON-NLS-1$
      "Peleps", //$NON-NLS-1$
      "Ragara", //$NON-NLS-1$
      "Sesus", //$NON-NLS-1$
      "Tepet", //$NON-NLS-1$
      "V'Neef" }; //$NON-NLS-1$

  private final SimpleWordFactory wordFactory = new SimpleWordFactory(
      new RealmSyllableFactory(),
      new RealmSyllableCalculator());

  private final int commonFamilyPercent;

  public RealmWordFactory(int commonFamilyPercent) {
    this.commonFamilyPercent = commonFamilyPercent;
  }

  public String createWord(int wordIndex) {
    if (wordIndex == 0 && RandomUtilities.nextPercent() < commonFamilyPercent) {
      return RandomUtilities.choose(commonFamilyNames);
    }
    return wordFactory.createWord(wordIndex);
  }
}
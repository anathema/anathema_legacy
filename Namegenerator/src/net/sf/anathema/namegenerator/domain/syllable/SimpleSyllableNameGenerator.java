package net.sf.anathema.namegenerator.domain.syllable;

import net.sf.anathema.namegenerator.domain.INameGenerator;

public class SimpleSyllableNameGenerator implements INameGenerator {

  private final ICountCalculator wordCalculator;
  private final IWordFactory wordFactory;

  public SimpleSyllableNameGenerator(IWordFactory wordFactory, ICountCalculator wordCalculator) {
    this.wordFactory = wordFactory;
    this.wordCalculator = wordCalculator;
  }

  public String[] createNames(int count) {
    String[] names = new String[count];
    for (int nameIndex = 0; nameIndex < names.length; nameIndex++) {
      StringBuffer name = new StringBuffer();
      int wordCount = wordCalculator.calculateNamePartCount();
      for (int wordIndex = 0; wordIndex < wordCount; wordIndex++) {
        if (wordIndex != 0) {
          name.append(" "); //$NON-NLS-1$
        }
        name.append(wordFactory.createWord(wordIndex));
      }
      names[nameIndex] = name.toString();
    }
    return names;
  }
}
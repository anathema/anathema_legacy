package net.sf.anathema.namegenerator.domain.syllable;

public class SimpleWordFactory implements IWordFactory {

  private final ISyllabalFactory syllableFactory;
  private final ICountCalculator countCalculator;
  private final boolean upperCase;

  public SimpleWordFactory(ISyllabalFactory syllableFactory, ICountCalculator countCalculator) {
    this(syllableFactory, countCalculator, true);
  }

  public SimpleWordFactory(ISyllabalFactory syllableFactory, ICountCalculator countCalculator, boolean upperCase) {
    this.syllableFactory = syllableFactory;
    this.countCalculator = countCalculator;
    this.upperCase = upperCase;
  }

  public String createWord(int wordIndex) {
    StringBuffer word = new StringBuffer();
    int syllableCount = countCalculator.calculateNamePartCount();
    String lastSyllabal = null;
    for (int syllableIndex = 0; syllableIndex < syllableCount; syllableIndex++) {
      lastSyllabal = syllableFactory.createToken(lastSyllabal);
      word.append(lastSyllabal);
    }
    if (upperCase && word.length() > 0) {
      word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
    }
    return word.toString();
  }
}
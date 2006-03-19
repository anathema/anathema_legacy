package net.sf.anathema.namegenerator.domain.realm;

import net.sf.anathema.lib.random.RandomUtilities;
import net.sf.anathema.namegenerator.domain.syllable.ICountCalculator;

public class RealmSyllableCalculator implements ICountCalculator {

  public int calculateNamePartCount() {
    int percent = RandomUtilities.RANDOM.nextInt(100);
    if (percent < 10) {
      return 2;
    }
    if (percent < 65) {
      return 3;
    }
    return 4;
  }
}
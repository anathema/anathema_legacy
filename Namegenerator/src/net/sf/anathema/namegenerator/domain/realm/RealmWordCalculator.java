package net.sf.anathema.namegenerator.domain.realm;

import net.sf.anathema.lib.random.RandomUtilities;
import net.sf.anathema.namegenerator.domain.syllable.ICountCalculator;

public class RealmWordCalculator implements ICountCalculator {

  public int calculateNamePartCount() {
    return RandomUtilities.RANDOM.nextInt(100) < 5 ? 3 : 2;
  }
}
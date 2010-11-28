package net.sf.anathema.namegenerator.domain.realm;

import net.sf.anathema.namegenerator.domain.INameGenerator;
import net.sf.anathema.namegenerator.domain.syllable.SimpleSyllableNameGenerator;

public class RealmNameGenerator implements INameGenerator {

  private final INameGenerator realmNameGenerator;

  public RealmNameGenerator() {
    this(20);
  }

  public RealmNameGenerator(int familyNamePercent) {
    this.realmNameGenerator = new SimpleSyllableNameGenerator(
        new RealmWordFactory(familyNamePercent),
        new RealmWordCalculator());
  }

  public String[] createNames(int count) {
    String[] names = new String[count];
    for (int index = 0; index < names.length; index++) {
      String newName = realmNameGenerator.createNames(1)[0];
      names[index] = newName;
    }
    return names;
  }
}
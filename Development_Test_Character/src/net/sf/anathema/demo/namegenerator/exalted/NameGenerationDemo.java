package net.sf.anathema.demo.namegenerator.exalted;

import net.sf.anathema.namegenerator.domain.INameGenerator;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;
import de.jdemo.extensions.SwingDemoCase;

public class NameGenerationDemo extends SwingDemoCase {

  public void demo50RealmNames() {
    showNames(new RealmNameGenerator(), 50);
  }

  public void demo50ThresholdNames() {
    showNames(new ThresholdNameGenerator(), 50);
  }

  private void showNames(INameGenerator generator, int nameCount) {
      StringBuilder allNames = new StringBuilder();
    for (String name : generator.createNames(nameCount)) {
      allNames.append(name);
      allNames.append("\n"); //$NON-NLS-1$
    }
    show(allNames.toString());
  }
}
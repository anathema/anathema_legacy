package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.resources.TraitInternationalizer;

import java.util.Comparator;

public class TraitReferenceByNameComparator implements Comparator<ITraitReference> {

  private final TraitInternationalizer i18ner;

  public TraitReferenceByNameComparator(TraitInternationalizer i18ner) {
    this.i18ner = i18ner;
  }

  @Override
  public int compare(ITraitReference o1, ITraitReference o2) {
    String name1 = i18ner.getScreenName(o1);
    String name2 = i18ner.getScreenName(o2);
    return name1.compareToIgnoreCase(name2);
  }
}
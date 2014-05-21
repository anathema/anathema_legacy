package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.traits.display.TraitTypeInternationalizer;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.Comparator;

public class TraitTypeByNameComparator implements Comparator<TraitType> {

  private final TraitTypeInternationalizer i18ner;

  public TraitTypeByNameComparator(TraitTypeInternationalizer i18ner) {
    this.i18ner = i18ner;
  }

  @Override
  public int compare(TraitType o1, TraitType o2) {
    String name1 = i18ner.getScreenName(o1);
    String name2 = i18ner.getScreenName(o2);
    return name1.compareToIgnoreCase(name2);
  }
}
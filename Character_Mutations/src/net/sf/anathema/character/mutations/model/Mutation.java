package net.sf.anathema.character.mutations.model;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.library.quality.model.Quality;
import net.sf.anathema.character.library.quality.model.QualityPrerequisite;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityPredicate;

import java.util.List;

public class Mutation extends Quality implements IMutation {

  private final IExaltedSourceBook source;
  private final Integer page;
  private final int cost;

  public Mutation(String id, MutationType type, IExaltedSourceBook book, Integer page) {
    super(id, type);
    this.cost = mapCost(type);
    this.source = book;
    this.page = page;
  }

  public Mutation(String id, MutationType type) {
    this(id, type, null, null);
  }

  public Mutation(String id) {
    this(id, MutationType.Pox, null, null);
  }

  private static int mapCost(MutationType type) {
    switch (type) {
      case Pox:
        return 1;
      case Affliction:
        return 2;
      case Blight:
        return 4;
      case Abomination:
        return 6;
      case Deformity:
        return -4;
      case Debility:
        return -2;
      case Deficiency:
        return -1;
    }
    return 0;
  }

  public IExaltedSourceBook getSource() {
    return source;
  }

  public Integer getPage() {
    return page;
  }

  public void accept(IMutationVisitor visitor) {
    // Nothing to do
  }

  public int getCost() {
    return cost;
  }

  protected final boolean isPrerequisite(IQuality gift) {
    List<IQualityPredicate> prerequisiteList = getPrerequisiteList();
    for (IQualityPredicate predicate : prerequisiteList) {
      if (predicate instanceof QualityPrerequisite) {
        IQuality[] prerequisiteQualities = ((QualityPrerequisite) predicate).getPrerequisiteQualities();
        return ArrayUtilities.containsValue(prerequisiteQualities, gift);
      }
    }
    return false;
  }
}

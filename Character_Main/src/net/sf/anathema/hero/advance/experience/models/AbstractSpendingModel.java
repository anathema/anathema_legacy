package net.sf.anathema.hero.advance.experience.models;

import net.sf.anathema.hero.points.overview.IOverviewModelVisitor;
import net.sf.anathema.hero.points.overview.ISpendingModel;

public abstract class AbstractSpendingModel extends AbstractOverviewModel implements ISpendingModel {

  public AbstractSpendingModel(String categoryId, String id) {
    super(categoryId, id);
  }

  @Override
  public void accept(IOverviewModelVisitor visitor) {
    visitor.visitAllotmentModel(this);
  }
}
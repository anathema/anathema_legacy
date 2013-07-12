package net.sf.anathema.hero.advance.overview.model;

import net.sf.anathema.hero.points.overview.IOverviewModelVisitor;
import net.sf.anathema.hero.points.overview.SpendingModel;

public abstract class AbstractSpendingModel extends AbstractOverviewModel implements SpendingModel {

  public AbstractSpendingModel(String categoryId, String id) {
    super(categoryId, id);
  }

  @Override
  public void accept(IOverviewModelVisitor visitor) {
    visitor.visitAllotmentModel(this);
  }
}
package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.presenter.overview.IOverviewModelVisitor;
import net.sf.anathema.character.presenter.overview.ISpendingModel;

public abstract class AbstractSpendingModel extends AbstractOverviewModel implements ISpendingModel {

  public AbstractSpendingModel(String categoryId, String id) {
    super(categoryId, id);
  }

  public void accept(IOverviewModelVisitor visitor) {
    visitor.visitAlotmentModel(this);
  }
}
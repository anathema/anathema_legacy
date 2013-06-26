package net.sf.anathema.character.model.advance.models;

import net.sf.anathema.character.presenter.overview.IAdditionalSpendingModel;
import net.sf.anathema.character.presenter.overview.IOverviewModelVisitor;

public abstract class AbstractAdditionalSpendingModel extends AbstractOverviewModel implements IAdditionalSpendingModel {

  public AbstractAdditionalSpendingModel(String categoryId, String id) {
    super(categoryId, id);
  }

  @Override
  public void accept(IOverviewModelVisitor visitor) {
    visitor.visitAdditionalAlotmentModel(this);
  }
}
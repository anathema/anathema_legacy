package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.presenter.overview.IOverviewModelVisitor;
import net.sf.anathema.character.presenter.overview.IValueModel;

public abstract class AbstractIntegerValueModel extends AbstractOverviewModel implements IValueModel<Integer> {

  public AbstractIntegerValueModel(String categoryId, String id) {
    super(categoryId, id);
  }

  public void accept(IOverviewModelVisitor visitor) {
    visitor.visitIntegerValueModel(this);
  }
}
package net.sf.anathema.character.presenter.overview;

public interface IOverviewModelVisitor {

  public void visitStringValueModel(IValueModel<String> visitedModel);

  public void visitIntegerValueModel(IValueModel<Integer> model);

  public void visitAlotmentModel(ISpendingModel visitedModel);

  public void visitAdditionalAlotmentModel(IAdditionalSpendingModel visitedModel);
}
package net.sf.anathema.hero.points.overview;

public interface IOverviewModelVisitor {

  void visitStringValueModel(IValueModel<String> visitedModel);

  void visitIntegerValueModel(IValueModel<Integer> model);

  void visitAlotmentModel(ISpendingModel visitedModel);

  void visitAdditionalAlotmentModel(IAdditionalSpendingModel visitedModel);
}
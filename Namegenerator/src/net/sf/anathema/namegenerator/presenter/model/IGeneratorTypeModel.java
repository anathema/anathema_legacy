package net.sf.anathema.namegenerator.presenter.model;

public interface IGeneratorTypeModel {

  void accept(IGeneratorTypeModelVisitor visitor);
}
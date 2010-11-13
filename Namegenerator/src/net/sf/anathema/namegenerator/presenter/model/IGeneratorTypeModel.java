package net.sf.anathema.namegenerator.presenter.model;

public interface IGeneratorTypeModel {

  public void accept(IGeneratorTypeModelVisitor visitor);
}
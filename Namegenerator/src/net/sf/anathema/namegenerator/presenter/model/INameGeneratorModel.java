package net.sf.anathema.namegenerator.presenter.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identified;

public interface INameGeneratorModel {

  void addGeneratorTypeChangeListener(IChangeListener listener);

  IGeneratorTypeModel getGeneratorTypeModel(Identified type);

  Identified[] getGeneratorTypes();

  Identified getSelectedGeneratorType();

  void setGeneratorType(Identified selectedGeneratorType);

  String[] generateNames(int count);
}
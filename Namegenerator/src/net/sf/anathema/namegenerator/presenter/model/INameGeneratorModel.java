package net.sf.anathema.namegenerator.presenter.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface INameGeneratorModel {

  void addGeneratorTypeChangeListener(IChangeListener listener);

  IGeneratorTypeModel getGeneratorTypeModel(IIdentificate type);

  IIdentificate[] getGeneratorTypes();

  IIdentificate getSelectedGeneratorType();

  void setGeneratorType(IIdentificate selectedGeneratorType);

  String[] generateNames(int count);
}
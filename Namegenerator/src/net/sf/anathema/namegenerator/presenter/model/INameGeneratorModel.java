package net.sf.anathema.namegenerator.presenter.model;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface INameGeneratorModel {

  public void addGeneratorTypeChangeListener(IChangeListener listener);

  public IGeneratorTypeModel getGeneratorTypeModel(IIdentificate type);

  public IIdentificate[] getGeneratorTypes();

  public IIdentificate getSelectedGeneratorType();

  public void setGeneratorType(IIdentificate selectedGeneratorType);

  public String[] generateNames(int count);
}
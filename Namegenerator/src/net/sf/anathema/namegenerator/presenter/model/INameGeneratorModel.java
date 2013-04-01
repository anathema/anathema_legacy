package net.sf.anathema.namegenerator.presenter.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identified;

import java.util.Set;

public interface INameGeneratorModel {

  void addGeneratorTypeChangeListener(IChangeListener listener);

  Set<Identified> getGeneratorTypes();

  Identified getSelectedGeneratorType();

  void setGeneratorType(Identified selectedGeneratorType);

  String[] generateNames(int count);
}
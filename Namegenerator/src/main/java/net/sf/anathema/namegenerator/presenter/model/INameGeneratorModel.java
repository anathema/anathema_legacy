package net.sf.anathema.namegenerator.presenter.model;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;

import java.util.Set;

public interface INameGeneratorModel {

  void addGeneratorTypeChangeListener(ChangeListener listener);

  Set<Identifier> getGeneratorTypes();

  Identifier getSelectedGeneratorType();

  void setGeneratorType(Identifier selectedGeneratorType);

  String[] generateNames(int count);
}
package net.sf.anathema.namegenerator.presenter.view;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.IView;

import javax.swing.Action;

public interface INameGeneratorView extends IView {

  void addNameGeneratorType(String label, Object type);

  void setResult(String result);

  Object getSelectedGeneratorType();

  void addGeneratorTypeChangeListener(IChangeListener listener);

  void setSelectedGeneratorType(Object generatorType);

  void addGenerationAction(Action action);
}
package net.sf.anathema.namegenerator.presenter.view;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.IChangeListener;

import javax.swing.JComponent;

public interface NameGeneratorView {

  void addNameGeneratorType(String label, Object type);

  void setResult(String result);

  Object getSelectedGeneratorType();

  void addGeneratorTypeChangeListener(IChangeListener listener);

  void setSelectedGeneratorType(Object generatorType);

  void addGenerationAction(String label, Command command);

  JComponent getComponent();
}
package net.sf.anathema.namegenerator.presenter.view;

import javax.swing.Action;
import javax.swing.JComponent;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.IView;

public interface INameGeneratorView extends IView {

  public void addNameGeneratorType(String label, JComponent additionalComponent, Object type);

  public void setResult(String result);

  public Object getSelectedGeneratorType();

  public void addGeneratorTypeChangeListener(IChangeListener listener);

  public void setSelectedGeneratorType(Object generatorType);

  public void addGenerationAction(Action action);
}
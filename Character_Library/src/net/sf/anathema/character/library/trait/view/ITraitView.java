package net.sf.anathema.character.library.trait.view;

import javax.swing.JPanel;

import net.sf.anathema.framework.value.IIntValueView;

public interface ITraitView<K extends ITraitView<?>> extends IIntValueView {

  /** Adds 2 components */
  public void addComponents(JPanel viewPanel);

  public void delete();

  public K getInnerView();
}
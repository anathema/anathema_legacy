package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.framework.value.IIntValueView;

import javax.swing.JPanel;

public interface ITraitView<K extends ITraitView< ? >> extends IIntValueView {

  /** Adds 2 components */
  void addComponents(JPanel viewPanel);

  void delete();

  K getInnerView();
}
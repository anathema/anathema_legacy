package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.framework.value.IIntValueView;

import javax.swing.JPanel;

public interface TraitView extends IIntValueView {

  void addComponents(JPanel viewPanel);
}
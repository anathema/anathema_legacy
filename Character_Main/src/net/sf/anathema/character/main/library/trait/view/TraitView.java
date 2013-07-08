package net.sf.anathema.character.main.library.trait.view;

import net.sf.anathema.framework.value.IntValueView;

import javax.swing.JPanel;

public interface TraitView extends IntValueView {

  void addComponents(JPanel viewPanel);
}
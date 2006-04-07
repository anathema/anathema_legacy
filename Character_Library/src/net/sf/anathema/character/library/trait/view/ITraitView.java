package net.sf.anathema.character.library.trait.view;

import javax.swing.JPanel;

import net.sf.anathema.framework.value.IIntValueView;

public interface ITraitView extends IIntValueView {

  public void addComponents(JPanel viewPanel);

  public void delete();

}

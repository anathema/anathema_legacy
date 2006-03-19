package net.sf.anathema.character.view.repository;

import javax.swing.JComponent;
import javax.swing.event.TreeSelectionListener;

import net.sf.anathema.character.generic.template.ICharacterTemplate;

public interface ICharacterTemplateTree {

  public void initTemplateTree();

  public boolean isTemplateSelected();

  public JComponent getComponent();

  public ICharacterTemplate getSelectedTemplate();

  public void addTreeSelectionListener(TreeSelectionListener listener);
}
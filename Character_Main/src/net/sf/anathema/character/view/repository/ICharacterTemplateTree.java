package net.sf.anathema.character.view.repository;

import javax.swing.JComponent;
import javax.swing.event.TreeSelectionListener;

public interface ICharacterTemplateTree {

  public void initTemplateTree();

  public boolean isTemplateTypeSelected();

  public JComponent getComponent();

  public void addTreeSelectionListener(TreeSelectionListener listener);

  public ITemplateTypeAggregation getSelectedTemplate();
}
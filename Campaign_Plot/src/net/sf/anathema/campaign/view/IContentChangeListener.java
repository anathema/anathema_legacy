package net.sf.anathema.campaign.view;

import javax.swing.JTree;

public interface IContentChangeListener {

  public void addRequested();

  public void removeRequested();

  public void selectionChanged(JTree tree);
}
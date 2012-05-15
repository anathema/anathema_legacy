package net.sf.anathema.campaign.view;

import javax.swing.JTree;

public interface IContentChangeListener {

  void addRequested();

  void removeRequested();

  void selectionChanged(JTree tree);
}
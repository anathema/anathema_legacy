package net.sf.anathema.charmtree.presenter.view;

import java.awt.Dimension;

import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.util.IIdentificate;

public interface ICascadeSelectionView {

  public ICharmTreeView getCharmTreeView();

  public void addDocumentLoadedListener(IDocumentLoadedListener listener);

  public void addCharmTypeSelector(String title, IIdentificate[] types, ListCellRenderer renderer);

  public void addCharmTypeSelectionListener(IExaltTypeChangedListener selectionListener);

  public void fillCharmGroupBox(IIdentificate[] charmGroups);

  public void fillCharmTypeBox(IIdentificate[] cascadeTypes);

  public void addCharmGroupSelector(
      String title,
      ListCellRenderer renderer,
      ICharmGroupChangeListener selectionListener,
      Dimension preferredSize);
}
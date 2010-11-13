package net.sf.anathema.charmtree.presenter.view;

import java.awt.Dimension;

import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.presenter.view.IDocumentLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;

public interface ICascadeSelectionView {

  public ISvgTreeView getCharmTreeView();

  public void addDocumentLoadedListener(IDocumentLoadedListener listener);

  public void addCharmTypeSelector(String title, IIdentificate[] types, ListCellRenderer renderer);

  public void addCharmTypeSelectionListener(IObjectValueChangedListener<IIdentificate> selectionListener);

  public void fillCharmGroupBox(IIdentificate[] charmGroups);

  public void fillCharmTypeBox(IIdentificate[] cascadeTypes);

  public void addCharmGroupSelector(
      String title,
      ListCellRenderer renderer,
      ICharmGroupChangeListener selectionListener,
      Dimension preferredSize);
}
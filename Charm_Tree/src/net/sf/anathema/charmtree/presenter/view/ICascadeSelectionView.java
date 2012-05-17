package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadedListener;

import javax.swing.JComponent;
import javax.swing.ListCellRenderer;
import java.awt.Dimension;

public interface ICascadeSelectionView {

  CharmTreeRenderer getCharmTreeRenderer();

  void addCascadeLoadedListener(CascadeLoadedListener listener);

  void addCharmTypeSelector(String title, Identified[] types, ListCellRenderer renderer);

  void addCharmTypeSelectionListener(ObjectValueListener<Identified> selectionListener);

  void addCharmFilterButton(SmartAction action, String titleText, String buttonText);

  void fillCharmGroupBox(Identified[] charmGroups);

  void fillCharmTypeBox(Identified[] cascadeTypes);

  void addCharmGroupSelector(String title, ListCellRenderer renderer, ICharmGroupChangeListener selectionListener, Dimension preferredSize);
	
  void addCharmCascadeHelp(String helpText);

  JComponent getCharmComponent();

  void initGui();
}
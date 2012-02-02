package net.sf.anathema.charmtree;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.charmtree.presenter.view.CharmTreeRenderer;
import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.SvgCharmTreeRenderer;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.presenter.view.IDocumentLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
import net.sf.anathema.platform.svgtree.view.SvgTreeView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public abstract class AbstractCascadeSelectionView implements ICascadeSelectionView {

  private IChangeableJComboBox<IIdentificate> groupComboBox;
  private IChangeableJComboBox<IIdentificate> typeComboBox;
  private final JPanel selectionPanel;
  private final ISvgTreeView charmTreeView;
  public AbstractCascadeSelectionView(ISvgTreeViewProperties treeProperties) {
    this.selectionPanel = new JPanel(new GridDialogLayout(3, false));
    this.charmTreeView = new SvgTreeView(treeProperties);
  }

  @Override
  public void addCharmTypeSelector(String title, IIdentificate[] types, ListCellRenderer renderer) {
    final JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    typeComboBox = new ChangeableJComboBox<IIdentificate>(types, false);
    typeComboBox.setSelectedObject(null);
    typeComboBox.setRenderer(renderer);
    panel.add(typeComboBox.getComponent(), BorderLayout.CENTER);
    getSelectionComponent().add(panel);
  }

  @Override
  public void fillCharmGroupBox(IIdentificate[] charmGroups) {
    groupComboBox.setObjects(charmGroups);
  }

  @Override
  public void fillCharmTypeBox(IIdentificate[] charmGroups) {
    typeComboBox.setObjects(charmGroups);
  }

  @Override
  public void addCharmTypeSelectionListener(final IObjectValueChangedListener<IIdentificate> selectionListener) {
    typeComboBox.addObjectSelectionChangedListener(selectionListener);
  }

  @Override
  public void addCharmGroupSelector(
      String title,
      ListCellRenderer renderer,
      final ICharmGroupChangeListener selectionListener,
      Dimension preferredSize) {
    final JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    groupComboBox = new ChangeableJComboBox<IIdentificate>(null, false);
    groupComboBox.setSelectedObject(null);
    groupComboBox.setRenderer(renderer);
    groupComboBox.setPreferredSize(preferredSize);
    groupComboBox.addObjectSelectionChangedListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(IIdentificate newValue) {
        selectionListener.valueChanged(groupComboBox.getSelectedObject(), typeComboBox.getSelectedObject());
      }
    });
    panel.add(groupComboBox.getComponent(), BorderLayout.CENTER);
    getSelectionComponent().add(panel);
  }

  @Override
  public void addCharmFilterButton(SmartAction action, String titleText, String buttonText)
  {
	  JPanel buttonPanel = new JPanel();
	  JButton filterButton = new JButton();
	  filterButton.setAction(action);
	  filterButton.setText(buttonText);
	  buttonPanel.add(filterButton);
	  
	  TitledBorder title;
	  title = BorderFactory.createTitledBorder(titleText);
	  buttonPanel.setBorder(title);
	  
	  getSelectionComponent().add(buttonPanel);
  }

  protected final JComponent getSelectionComponent() {
    return selectionPanel;
  }

  protected final ISvgTreeView getCharmTreeView() {
    return charmTreeView;
  }

  @Override
  public CharmTreeRenderer getCharmTreeRenderer() {
    return new SvgCharmTreeRenderer(charmTreeView);
  }

  @Override
  public final void addDocumentLoadedListener(IDocumentLoadedListener documentListener) {
    charmTreeView.addDocumentLoadedListener(documentListener);
  }

  public final void dispose() {
    charmTreeView.dispose();
  }

  protected void unselect() {
    typeComboBox.setSelectedObject(null);
    groupComboBox.setSelectedObject(null);
  }
}
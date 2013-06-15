package net.sf.anathema.charmtree;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.charmtree.view.CharmTreeRenderer;
import net.sf.anathema.charmtree.view.ICascadeSelectionView;
import net.sf.anathema.charmtree.view.ICharmGroupChangeListener;
import net.sf.anathema.charmtree.view.svg.GenericCascadeRenderer;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.platform.tree.document.GenericCascadeFactory;
import net.sf.anathema.platform.tree.presenter.view.CascadeLoadedListener;
import net.sf.anathema.platform.tree.presenter.view.ITreeView;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;
import net.sf.anathema.platform.tree.view.SwingCascadeStrategy;
import net.sf.anathema.platform.tree.view.SwingTreeView;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public abstract class AbstractCascadeSelectionView implements ICascadeSelectionView {

  private final JPanel selectionPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(4).fillX()));
  private IChangeableJComboBox<Identified> groupComboBox;
  private IChangeableJComboBox<Identified> typeComboBox;
  private final SwingTreeView swingTreeView;

  public AbstractCascadeSelectionView() {
    this.swingTreeView = new SwingTreeView();
  }

  public void initGui(final ToolTipProperties treeProperties, final NodeProperties properties) {
    CascadeLoadedListener listener = new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        swingTreeView.initNodeNames(properties);
      }
    };
    swingTreeView.initToolTips(treeProperties);
    swingTreeView.addCascadeLoadedListener(listener);
  }

  @Override
  public void addCharmTypeSelector(String title, Identified[] types, ListCellRenderer renderer) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    typeComboBox = new ChangeableJComboBox<>(types, false);
    typeComboBox.setSelectedObject(null);
    typeComboBox.setRenderer(renderer);
    panel.add(typeComboBox.getComponent(), BorderLayout.CENTER);
    getSelectionComponent().add(panel);
  }

  @Override
  public void fillCharmGroupBox(Identified[] charmGroups) {
    groupComboBox.setObjects(charmGroups);
  }

  @Override
  public void fillCharmTypeBox(Identified[] charmGroups) {
    typeComboBox.setObjects(charmGroups);
  }

  @Override
  public void addCharmTypeSelectionListener(ObjectValueListener<Identified> selectionListener) {
    typeComboBox.addObjectSelectionChangedListener(selectionListener);
  }

  @Override
  public void addCharmGroupSelector(String title, ListCellRenderer renderer, final ICharmGroupChangeListener selectionListener,
                                    Dimension preferredSize) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    groupComboBox = new ChangeableJComboBox<>(null, false);
    groupComboBox.setSelectedObject(null);
    groupComboBox.setRenderer(renderer);
    groupComboBox.setPreferredSize(preferredSize);
    groupComboBox.addObjectSelectionChangedListener(new ObjectValueListener<Identified>() {
      @Override
      public void valueChanged(Identified newValue) {
        selectionListener.valueChanged(groupComboBox.getSelectedObject(), typeComboBox.getSelectedObject());
      }
    });
    panel.add(groupComboBox.getComponent(), BorderLayout.CENTER);
    getSelectionComponent().add(panel);
  }

  @Override
  public void addCharmFilterButton(SmartAction action, String titleText) {
    JPanel buttonPanel = new JPanel();
    JButton filterButton = new JButton();
    filterButton.setAction(action);
    buttonPanel.add(filterButton);
    buttonPanel.setBorder(new TitledBorder(titleText));
    getSelectionComponent().add(buttonPanel);
  }

  @Override
  public void addCharmCascadeHelp(String helpText) {
    JLabel help = new JLabel(helpText);
    getSelectionComponent().add(help, new CC().growX().pushX());
  }

  protected final JComponent getSelectionComponent() {
    return selectionPanel;
  }

  protected final ITreeView getCharmTreeView() {
    return swingTreeView;
  }

  @Override
  public CharmTreeRenderer getCharmTreeRenderer() {
    return GenericCascadeRenderer.CreateFor(swingTreeView, new GenericCascadeFactory(new SwingCascadeStrategy()));
  }

  @Override
  public final void addCascadeLoadedListener(CascadeLoadedListener cascadeListener) {
    swingTreeView.addCascadeLoadedListener(cascadeListener);
  }

  protected void unselect() {
    typeComboBox.setSelectedObject(null);
    groupComboBox.setSelectedObject(null);
  }

  @Override
  public JComponent getCharmComponent() {
    return swingTreeView.getComponent();
  }
}
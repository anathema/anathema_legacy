package net.sf.anathema.charmtree;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.charmtree.presenter.view.CharmTreeRenderer;
import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.svg.GenericCascadeRenderer;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.platform.svgtree.document.GenericCascadeFactory;
import net.sf.anathema.platform.svgtree.document.SvgCascadeStrategy;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;
import net.sf.anathema.platform.svgtree.presenter.view.ITreeView;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.svgtree.presenter.view.TreeViewProperties;
import net.sf.anathema.platform.svgtree.view.SvgTreeView;
import net.sf.anathema.platform.tree.view.SwingCascadeStrategy;
import net.sf.anathema.platform.tree.view.SwingTreeView;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

public abstract class AbstractCascadeSelectionView implements ICascadeSelectionView {

  public static final boolean useSwingForCascades = false;
  private final JPanel selectionPanel = new JPanel(new GridDialogLayout(4, false));
  private IChangeableJComboBox<Identified> groupComboBox;
  private IChangeableJComboBox<Identified> typeComboBox;
  private final ISvgTreeView svgTreeView;
  private final SwingTreeView swingTreeView;

  public AbstractCascadeSelectionView(final TreeViewProperties treeProperties, final NodeProperties properties) {
    this.swingTreeView = new SwingTreeView();
    this.svgTreeView = new SvgTreeView(treeProperties);
    CascadeLoadedListener listener = new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        if (useSwingForCascades) {
          swingTreeView.initNodeNames(properties);
        } else {
          svgTreeView.initNodeNames(properties);
        }
      }
    };
    if (useSwingForCascades) {
      swingTreeView.initToolTips(treeProperties);
      swingTreeView.addCascadeLoadedListener(listener);
    } else {
      svgTreeView.addCascadeLoadedListener(listener);
    }
  }

  @Override
  public void addCharmTypeSelector(String title, Identified[] types, ListCellRenderer renderer) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    typeComboBox = new ChangeableJComboBox<Identified>(types, false);
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
  public void addCharmGroupSelector(String title, ListCellRenderer renderer,
                                    final ICharmGroupChangeListener selectionListener, Dimension preferredSize) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    groupComboBox = new ChangeableJComboBox<Identified>(null, false);
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
  public void addCharmFilterButton(SmartAction action, String titleText, String buttonText) {
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

  @Override
  public void addCharmCascadeHelp(String helpText) {
    JLabel help = new JLabel(helpText);
    getSelectionComponent().add(help, GridDialogLayoutData.FILL_HORIZONTAL);
  }

  protected final JComponent getSelectionComponent() {
    return selectionPanel;
  }

  protected final ITreeView getCharmTreeView() {
    if (useSwingForCascades) {
      return swingTreeView;
    }
    return svgTreeView;
  }

  protected ISvgTreeView getSpecialCharmTreeView() {
    return svgTreeView;
  }

  @Override
  public CharmTreeRenderer getCharmTreeRenderer() {
    if (useSwingForCascades) {
      return GenericCascadeRenderer.CreateFor(swingTreeView, new GenericCascadeFactory(new SwingCascadeStrategy()));
    }
    return GenericCascadeRenderer.CreateFor(svgTreeView, new GenericCascadeFactory(new SvgCascadeStrategy()));
  }

  @Override
  public final void addCascadeLoadedListener(CascadeLoadedListener cascadeListener) {
    if (useSwingForCascades) {
      swingTreeView.addCascadeLoadedListener(cascadeListener);
    } else {
      svgTreeView.addCascadeLoadedListener(cascadeListener);
    }
  }

  protected void unselect() {
    typeComboBox.setSelectedObject(null);
    groupComboBox.setSelectedObject(null);
  }

  @Override
  public JComponent getCharmComponent() {
    return getCharmTreeView().getComponent();
  }
}
package net.sf.anathema.hero.charms.display.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ui.ConfigurableListCellRenderer;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.AgnosticTreeView;
import net.sf.anathema.platform.tree.display.CascadeLoadedListener;
import net.sf.anathema.platform.tree.display.GenericCascadeRenderer;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;
import net.sf.anathema.platform.tree.display.TreeRenderer;
import net.sf.anathema.platform.tree.display.TreeView;
import net.sf.anathema.platform.tree.document.GenericCascadeFactory;
import net.sf.anathema.platform.tree.swing.SwingPolygonPanel;
import net.sf.anathema.platform.tree.view.AgnosticCascadeStrategy;
import net.sf.anathema.platform.tree.view.MouseBorderClosure;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;
import static net.sf.anathema.lib.gui.swing.GuiUtilities.calculateComboBoxSize;

public abstract class AbstractCascadeSelectionView implements CascadeSelectionView, IView {

  private final JPanel selectionPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(4).fillX()));
  private JPanel content = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
  private IChangeableJComboBox<Identifier> groupComboBox;
  private IChangeableJComboBox<Identifier> typeComboBox;
  private final SwingPolygonPanel viewComponent = new SwingPolygonPanel();
  private final AgnosticTreeView treeView = new AgnosticTreeView(viewComponent);

  public void initGui(final ToolTipProperties treeProperties, final NodeProperties properties) {
    CascadeLoadedListener listener = new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        treeView.initNodeNames(properties);
      }
    };
    treeView.initToolTips(treeProperties);
    treeView.addCascadeLoadedListener(listener);
    content.add(getSelectionComponent(), new CC().growX());
    content.add(viewComponent.getComponent(), new CC().grow().push());
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void addCharmTypeSelector(String title, Identifier[] types, AgnosticUIConfiguration uiConfig) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    typeComboBox = new ChangeableJComboBox<>(types);
    typeComboBox.setSelectedObject(null);
    ListCellRenderer renderer = new ConfigurableListCellRenderer(uiConfig);
    typeComboBox.setRenderer(renderer);
    panel.add(typeComboBox.getComponent(), BorderLayout.CENTER);
    selectionPanel.add(panel);
  }

  @Override
  public void fillCharmGroupBox(Identifier[] charmGroups) {
    groupComboBox.setObjects(charmGroups);
  }

  @Override
  public void fillCharmTypeBox(Identifier[] charmGroups) {
    typeComboBox.setObjects(charmGroups);
  }

  @Override
  public void addCharmTypeSelectionListener(ObjectValueListener<Identifier> selectionListener) {
    typeComboBox.addObjectSelectionChangedListener(selectionListener);
  }

  @Override
  public void addCharmGroupSelector(String title, AgnosticUIConfiguration uiConfig,
                                    final ICharmGroupChangeListener selectionListener,
                                    Identifier[] allPotentialGroups) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    groupComboBox = new ChangeableJComboBox<>();
    groupComboBox.setSelectedObject(null);
    ListCellRenderer renderer = new ConfigurableListCellRenderer(uiConfig);
    groupComboBox.setRenderer(renderer);
    Dimension preferredSize = calculateComboBoxSize(allPotentialGroups, renderer);
    groupComboBox.setPreferredSize(preferredSize);
    groupComboBox.addObjectSelectionChangedListener(new ObjectValueListener<Identifier>() {
      @Override
      public void valueChanged(Identifier newValue) {
        selectionListener.valueChanged(groupComboBox.getSelectedObject(), typeComboBox.getSelectedObject());
      }
    });
    panel.add(groupComboBox.getComponent(), BorderLayout.CENTER);
    selectionPanel.add(panel);
  }

  @Override
  public void addCharmCascadeHelp(String helpText) {
    JLabel help = new JLabel(helpText);
    selectionPanel.add(help, new CC().growX().pushX());
  }

  protected final JComponent getSelectionComponent() {
    return selectionPanel;
  }

  protected final TreeView getCharmTreeView() {
    return treeView;
  }

  @Override
  public TreeRenderer getCharmTreeRenderer() {
    return new GenericCascadeRenderer(treeView, new GenericCascadeFactory(new AgnosticCascadeStrategy()));
  }

  @Override
  public final void addCascadeLoadedListener(CascadeLoadedListener cascadeListener) {
    treeView.addCascadeLoadedListener(cascadeListener);
  }

  protected void unselect() {
    typeComboBox.setSelectedObject(null);
    groupComboBox.setSelectedObject(null);
  }

  @Override
  public void whenCursorLeavesCharmAreaResetAllPopups() {
    viewComponent.addMouseBorderListener(new MouseBorderClosure() {
      @Override
      public void mouseEntered() {
        //nothing to do
      }

      @Override
      public void mouseExited() {
        viewComponent.resetAllTooltips();
      }
    });
  }
}
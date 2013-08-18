package net.sf.anathema.hero.charms.display.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.charms.display.special.BooleanSelectionSpecialNodeView;
import net.sf.anathema.hero.charms.display.special.CategorizedSpecialView;
import net.sf.anathema.hero.charms.display.special.ToggleButtonSpecialNodeView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.StyledTitledPane;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import net.sf.anathema.platform.tree.display.AgnosticPolygonPanel;
import net.sf.anathema.platform.tree.display.AgnosticTreeView;
import net.sf.anathema.platform.tree.display.CategorizedSpecialNodeView;
import net.sf.anathema.platform.tree.display.ContentFactory;
import net.sf.anathema.platform.tree.display.TreeView;
import net.sf.anathema.platform.tree.fx.FxPolygonPanel;
import net.sf.anathema.platform.tree.view.MouseBorderClosure;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxCharmView implements CharmView, NodeHolder {
  private final MigPane selectionPanel = new MigPane(withoutInsets().wrapAfter(4).fillX());
  private final MigPane content = new MigPane(fillWithoutInsets().wrapAfter(1));
  private final FxPolygonPanel viewComponent = new FxPolygonPanel();
  private final AgnosticTreeView treeView = new AgnosticTreeView(new AgnosticPolygonPanel(viewComponent));

  public FxCharmView() {
    content.add(selectionPanel, new CC().growX());
    content.add(viewComponent.getNode(), new CC().grow().push());
  }

  @Override
  public TreeView addTreeView() {
    return treeView;
  }

  @Override
  public ComboBoxSelectionView<Identifier> addSelectionView(final String title, final AgnosticUIConfiguration<Identifier> uiConfig) {
    final BorderPane borderPane = new BorderPane();
    ComboBoxSelectionView<Identifier> selectionView = new ComboBoxSelectionView<>(title, uiConfig);
    borderPane.centerProperty().set(selectionView.getNode());
    final Node titledBorder = StyledTitledPane.Create(title, borderPane);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        selectionPanel.add(titledBorder);
      }
    });
    return selectionView;
  }

  @Override
  public ObjectSelectionView<Identifier> addSelectionViewAndSizeItFor(String title, AgnosticUIConfiguration<Identifier> uiConfig, Identifier[] objects) {
    ComboBoxSelectionView<Identifier> selectionView = addSelectionView(title, uiConfig);
    selectionView.sizeComboboxFor(objects);
    return selectionView;
  }

  @Override
  public void addCharmCascadeHelp(final String helpText) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        Label help = new Label(helpText);
        selectionPanel.add(help, new CC().growX().pushX());
      }
    });
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

  @Override
  public void registerSpecialType(Class contentClass, ContentFactory factory) {
    treeView.registerSpecialType(contentClass, factory);
  }

  @Override
  public ToggleButtonSpecialNodeView createToggleButtonSpecialView() {
    return new BooleanSelectionSpecialNodeView();
  }

  @Override
  public CategorizedSpecialNodeView createCategorizedSpecialView() {
    return new CategorizedSpecialView();
  }

  @Override
  public Node getNode() {
    return content;
  }
}
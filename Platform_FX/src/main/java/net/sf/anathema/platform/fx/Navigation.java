package net.sf.anathema.platform.fx;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.tool.FxBaseTool;
import net.sf.anathema.platform.tool.FxButtonTool;
import net.sf.anathema.platform.tool.FxToggleTool;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class Navigation {

  private MigPane pane = new MigPane(withoutInsets().gridGap("0", "2").wrapAfter(1), new AC().grow().fill(), new AC().fill());
  private MigPane navigation = new MigPane(withoutInsets().gridGap("0", "2").wrapAfter(1), new AC().grow().fill(), new AC().fill());
  private ToolBar toolBar = new ToolBar();

  public Navigation() {
    pane.add(toolBar, new CC().width("100%").grow());
    pane.add(navigation, new CC().push());
  }

  public Tool addTool() {
    FxButtonTool fxButtonTool = FxButtonTool.ForToolbar();
    addTool(fxButtonTool);
    return fxButtonTool;
  }

  @SuppressWarnings("UnusedDeclaration")  //Used by subclasses
  public ToggleTool addToggleTool() {
    final FxToggleTool fxToggleTool = FxToggleTool.create();
    addTool(fxToggleTool);
    return fxToggleTool;
  }

  protected void addElementToNavigation(Node element) {
    navigation.add(element, new CC().pushX());
  }

  protected void addContainerToNavigation(Node element) {
    navigation.add(element, new CC().push());
  }

  public void clear() {
    navigation.getChildren().clear();
  }

  public Node getNode() {
    return pane;
  }

  protected void addTool(final FxBaseTool fxButtonTool) {
    toolBar.getItems().add(fxButtonTool.getNode());
    Scene scene = toolBar.getScene();
        /*ObservableMap<KeyCombination, Runnable> accelerators = scene.getAccelerators();
        FxAcceleratorMap acceleratorMap = new FxAcceleratorMap(accelerators);
        fxButtonTool.registerHotkeyIn(acceleratorMap);*/
  }
}
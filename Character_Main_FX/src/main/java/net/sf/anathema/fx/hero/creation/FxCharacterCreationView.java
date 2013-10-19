package net.sf.anathema.fx.hero.creation;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.hero.creation.CharacterCreationView;
import net.sf.anathema.hero.creation.ToggleButtonPanel;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.platform.fx.ListSelectionView;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import static javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;

public class FxCharacterCreationView implements CharacterCreationView {

  private final Stage stage = new Stage();
  private final MigPane component = new MigPane(new LC().gridGapX("10").gridGapY("10").wrapAfter(2));
  private final MigPane buttonBar = new MigPane(LayoutUtils.withoutInsets());
  private final Window parent;

  public FxCharacterCreationView(Window parent) {
    this.parent = parent;
    component.add(buttonBar, new CC().newline().gapTop("10").span().push().grow());
  }

  @Override
  public ToggleButtonPanel addToggleButtonPanel() {
    FxToggleButtonPanel panel = new FxToggleButtonPanel();
    component.add(panel.getNode(), new CC().grow().pushY());
    return panel;
  }

  @Override
  public VetoableObjectSelectionView<HeroTemplate> addObjectSelectionList() {
    ListSelectionView<HeroTemplate> view = new ListSelectionView<>();
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setHbarPolicy(NEVER);
    scrollPane.setVbarPolicy(AS_NEEDED);
    scrollPane.setContent(view.getNode());
    component.add(scrollPane, new CC().grow().push());
    return view;
  }

  @Override
  public void show() {
    Scene scene = new Scene(component);
    stage.initOwner(parent);
    stage.setScene(scene);
    stage.setHeight(200);
    stage.setWidth(300);
    stage.show();
  }

  @Override
  public void close() {
    stage.close();
  }

  @Override
  public Tool addButton() {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    buttonBar.add(tool.getNode(), new CC().alignX("right"));
    return tool;
  }

  @Override
  public void setTitle(String title) {
    stage.setTitle(title);
  }
}
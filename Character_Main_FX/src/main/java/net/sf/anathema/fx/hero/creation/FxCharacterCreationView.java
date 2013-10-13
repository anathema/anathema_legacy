package net.sf.anathema.fx.hero.creation;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.hero.creation.CharacterCreationView;
import net.sf.anathema.hero.creation.ToggleButtonPanel;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.platform.fx.ListSelectionView;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

public class FxCharacterCreationView implements CharacterCreationView {

  private final Stage stage = new Stage();
  private final MigPane component = new MigPane(LayoutUtils.withoutInsets().gridGapX("10"));

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
    scrollPane.setContent(view.getNode());
    component.add(scrollPane, new CC().grow().push());
    return view;
  }

  @Override
  public void show() {
    Scene scene = new Scene(component);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void close() {
    stage.close();
  }

  @Override
  public Tool addButton() {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    component.add(tool.getNode());
    return tool;
  }
}
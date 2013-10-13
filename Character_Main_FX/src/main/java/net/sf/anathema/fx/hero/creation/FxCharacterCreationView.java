package net.sf.anathema.fx.hero.creation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.hero.creation.CharacterCreationView;
import net.sf.anathema.hero.creation.ToggleButtonPanel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.platform.fx.ListSelectionView;
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

  public Parent getNode() {
    return component;
  }

  public void show() {
    Parent parent = getNode();
    Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
  }

  public void close() {
    stage.close();
  }

  public void whenConfirmed(final Command command) {
    //TODO (Swing->FX): i18n
    addButton(command, "OK");
  }

  public void whenCanceled(Command command) {
    //TODO (Swing->FX): i18n
    addButton(command, "Cancel");
  }

  private void addButton(final Command command, String label) {
    Button button = new Button(label);
    component.add(button);
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        command.execute();
      }
    });
  }
}
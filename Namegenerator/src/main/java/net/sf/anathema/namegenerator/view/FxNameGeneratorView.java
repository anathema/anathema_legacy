package net.sf.anathema.namegenerator.view;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.namegenerator.presenter.view.NameGeneratorView;
import net.sf.anathema.platform.fx.PerspectivePane;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

public class FxNameGeneratorView implements NameGeneratorView {

  private final PerspectivePane pane = new PerspectivePane();
  private final MigPane navigation = new MigPane(new LC().wrapAfter(1));
  private final ToggleGroup nameGeneratorTypeGroup = new ToggleGroup();
  private final TextArea resultView = new TextArea();
  private final BiMap<RadioButton, Object> generatorsByButton = HashBiMap.create();
  private final Announcer<ChangeListener> generatorListeners = Announcer.to(ChangeListener.class);

  public FxNameGeneratorView() {
    pane.setNavigationComponent(navigation);
    resultView.setPrefColumnCount(25);
    resultView.setPrefRowCount(5);
    pane.setContentComponent(resultView);
    resultView.setEditable(false);
    nameGeneratorTypeGroup.selectedToggleProperty().addListener(new javafx.beans.value.ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle toggle2) {
        if (toggle2 != null) {
          generatorListeners.announce().changeOccurred();
        }
      }
    });
  }

  @Override
  public Node getNode() {
    return pane.getNode();
  }

  @Override
  public void addNameGeneratorType(final String label, final Object generatorType) {
    final RadioButton generatorButton = new RadioButton(label);
    generatorsByButton.put(generatorButton, generatorType);
    generatorButton.setToggleGroup(nameGeneratorTypeGroup);
    navigation.add(generatorButton);
  }

  @Override
  public void setResult(final String result) {
    resultView.setText(result);
  }

  @Override
  public Object getSelectedGeneratorType() {
    Toggle selectedToggle = nameGeneratorTypeGroup.getSelectedToggle();
    return generatorsByButton.get(selectedToggle);
  }

  @Override
  public void addGeneratorTypeChangeListener(ChangeListener listener) {
    generatorListeners.addListener(listener);
  }

  @Override
  public void setSelectedGeneratorType(final Object generatorType) {
    RadioButton radioButton = generatorsByButton.inverse().get(generatorType);
    radioButton.setSelected(true);
  }

  @Override
  public void addGenerationAction(final String label, final Command command) {
    Button button = new Button();
    button.setText(label);
    button.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
      @Override
      public void handle(javafx.event.ActionEvent actionEvent) {
        command.execute();
      }
    });
    navigation.add(button, new CC().grow().pushX());
  }
}
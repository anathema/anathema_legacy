package net.sf.anathema.namegenerator.view;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import net.miginfocom.layout.LC;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.namegenerator.presenter.view.NameGeneratorView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.PerspectivePane;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;

public class FxNameGeneratorView implements NameGeneratorView {

  private final PerspectivePane pane = new PerspectivePane();
  private final MigPane navigation = new MigPane(new LC().wrapAfter(1));
  private final ToggleGroup nameGeneratorTypeGroup = new ToggleGroup();
  private final TextArea resultView = new TextArea();
  private final BiMap<RadioButton, Object> generatorsByButton = HashBiMap.create();
  private final Announcer<IChangeListener> generatorListeners = Announcer.to(IChangeListener.class);

  public FxNameGeneratorView() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.setNavigationComponent(navigation);
        resultView.setPrefColumnCount(25);
        resultView.setPrefRowCount(5);
        pane.setContentComponent(resultView);
        resultView.setEditable(false);
        nameGeneratorTypeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
          @Override
          public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle toggle2) {
            if (toggle2 != null) {
              generatorListeners.announce().changeOccurred();
            }
          }
        });
      }
    });
  }

  @Override
  public JComponent getComponent() {
    return pane.getComponent();
  }

  @Override
  public void addNameGeneratorType(final String label, final Object generatorType) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        final RadioButton generatorButton = new RadioButton(label);
        generatorsByButton.put(generatorButton, generatorType);
        generatorButton.setToggleGroup(nameGeneratorTypeGroup);
        navigation.add(generatorButton);
      }
    });
  }

  @Override
  public void setResult(final String result) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        resultView.setText(result);
      }
    });
  }

  @Override
  public Object getSelectedGeneratorType() {
    Toggle selectedToggle = nameGeneratorTypeGroup.getSelectedToggle();
    return generatorsByButton.get(selectedToggle);
  }

  @Override
  public void addGeneratorTypeChangeListener(IChangeListener listener) {
    generatorListeners.addListener(listener);
  }

  @Override
  public void setSelectedGeneratorType(final Object generatorType) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        RadioButton radioButton = generatorsByButton.inverse().get(generatorType);
        radioButton.setSelected(true);
      }
    });
  }

  @Override
  public void addGenerationAction(final String label, final Command command) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Button button = new Button();
        button.setText(label);
        button.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
          @Override
          public void handle(javafx.event.ActionEvent actionEvent) {
            command.execute();
          }
        });
        navigation.add(button);
      }
    });
  }
}
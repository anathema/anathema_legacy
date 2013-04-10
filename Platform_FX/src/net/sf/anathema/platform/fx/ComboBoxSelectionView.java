package net.sf.anathema.platform.fx;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Arrays;

public class ComboBoxSelectionView<V> implements IObjectSelectionView<V> {
  private ComboBox<V> comboBox;
  private Label label;
  private MigPane pane;

  public ComboBoxSelectionView(final String description, final AgnosticUIConfiguration<V> ui) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        comboBox = new ComboBox<>();
        label = new Label(description);
        pane = new MigPane();
        pane.add(label);
        pane.add(comboBox);
      }
    });
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        comboBox.setButtonCell(new UITableCell<>(ui));
        comboBox.setCellFactory(new ConfigurableListCellFactory<>(ui));
      }
    });
  }

  @Override
  public void setSelectedObject(final V object) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        comboBox.getSelectionModel().select(object);
      }
    });
  }

  @Override
  public void addObjectSelectionChangedListener(final ObjectValueListener<V> listener) {
    comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<V>() {
      @Override
      public void changed(ObservableValue<? extends V> observableValue, V v, V newValue) {
        listener.valueChanged(newValue);
      }
    });
  }

  @Override
  public void setObjects(V[] objects) {
    waitForContent();
    comboBox.setItems(new ObservableListWrapper<>(Arrays.asList(objects)));
  }

  private void waitForContent() {
    try {
      while (pane == null) {
        Thread.sleep(50);
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public V getSelectedObject() {
    return comboBox.getSelectionModel().getSelectedItem();
  }

  @Override
  public boolean isObjectSelected() {
    return comboBox.getSelectionModel().isEmpty();
  }

  @Override
  public void setEnabled(boolean enabled) {
    label.setDisable(!enabled);
    comboBox.setDisable(!enabled);
  }

  public Node getNode() {
    return pane;
  }
}
package net.sf.anathema.character.equipment.item.view.fx;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import jfxtras.labs.scene.control.ListSpinner;
import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Arrays;

public class FxCostSelectionView implements CostSelectionView {

  private Label label;
  private ComboBox<String> selection;
  private ListSpinner<Integer> spinner;
  private final MigPane pane = new MigPane();

  public FxCostSelectionView(final String text) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        label = new Label(text);
        selection = new ComboBox<>();
        spinner = new ListSpinner<>(0, 5);
        pane.add(label);
        pane.add(spinner);
      }
    });
  }

  @Override
  public void setValue(final ItemCost cost) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        if (cost == null) {
          selection.getSelectionModel().select(null);
          spinner.setValue(0);
        } else {
          selection.getSelectionModel().select(cost.getType());
          spinner.setValue(cost.getValue());
        }
      }
    });
  }

  @Override
  public void addSelectionChangedListener(final ISelectionIntValueChangedListener<String> listener) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        selection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
          @Override
          public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
            listener.valueChanged(newValue, spinner.getValue());
          }
        });
        spinner.indexProperty().addListener(new ChangeListener<Integer>() {
          @Override
          public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer newValue) {
            listener.valueChanged(selection.getValue(), newValue);
          }
        });
      }
    });
  }

  @Override
  public void setSelectableBackgrounds(final String[] backgrounds) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        selection.setItems(new ObservableListWrapper<>(Arrays.asList(backgrounds)));
      }
    });
  }

  public Node getNode() {
    return pane;
  }
}
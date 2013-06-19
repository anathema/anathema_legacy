package net.sf.anathema.character.equipment.item.view.fx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import jfxtras.labs.scene.control.ListSpinner;
import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;
import net.sf.anathema.platform.fx.FxObjectSelectionView;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;
import net.sf.anathema.platform.fx.ComboBoxSelectionView;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxCostSelectionView implements CostSelectionView {

  private final SelectionViewFactory selectionViewFactory;
  private FxObjectSelectionView<String> selection;
  private ListSpinner<Integer> spinner;
  private final MigPane pane = new MigPane(withoutInsets());
  private final Announcer<ISelectionIntValueChangedListener> announcer = new Announcer<>(
          ISelectionIntValueChangedListener.class);
  private final CostTypeChangeListener typeChangeListener = new CostTypeChangeListener();
  private final CostValueChangeListener valueChangeListener = new CostValueChangeListener();

  public FxCostSelectionView(final String text, SelectionViewFactory viewFactory) {
    selectionViewFactory = viewFactory;
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        selection = selectionViewFactory.create(text, new SimpleUiConfiguration());
        spinner = new ListSpinner<>(0, 5);
        spinner.getStyleClass().add("dots");
        pane.add(selection.getNode());
        pane.add(spinner);
        startListening();
      }
    });
  }

  @Override
  public void setValue(final ItemCost cost) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        stopListening();
        if (cost == null) {
          selection.setSelectedObject(null);
          spinner.setValue(0);
        } else {
          selection.setSelectedObject(cost.getType());
          spinner.setValue(cost.getValue());
        }
        startListening();
      }
    });
  }

  @Override
  public void addSelectionChangedListener(final ISelectionIntValueChangedListener<String> listener) {
    announcer.addListener(listener);
  }

  @Override
  public void setSelectableBackgrounds(final String[] backgrounds) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        selection.setObjects(backgrounds);
      }
    });
  }

  public Node getNode() {
    return pane;
  }

  private void startListening() {
    selection.addObjectSelectionChangedListener(typeChangeListener);
    spinner.indexProperty().addListener(valueChangeListener);
  }


  private void stopListening() {
    selection.removeObjectSelectionChangedListener(typeChangeListener);
    spinner.indexProperty().removeListener(valueChangeListener);
  }

  @SuppressWarnings("unchecked")
  private class CostTypeChangeListener implements ObjectValueListener<String> {
    @Override
    public void valueChanged(String newValue) {
      announcer.announce().valueChanged(newValue, spinner.getValue());
    }
  }

  @SuppressWarnings("unchecked")
  private class CostValueChangeListener implements ChangeListener<Integer> {
    @Override
    public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer newValue) {
      announcer.announce().valueChanged(selection.getSelectedObject(), newValue);
    }
  }
}
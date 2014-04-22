package net.sf.anathema.character.equipment.item.view.fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;
import net.sf.anathema.platform.fx.FxObjectSelectionView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.dot.DotSelectionSpinner;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxCostSelectionView implements CostSelectionView {

  private final SelectionViewFactory selectionViewFactory;
  private FxObjectSelectionView<String> selection;
  private final DotSelectionSpinner spinner = new DotSelectionSpinner(0, 5);
  private final MigPane pane = new MigPane(withoutInsets());
  private final Announcer<ISelectionIntValueChangedListener> announcer = new Announcer<>(
          ISelectionIntValueChangedListener.class);
  private final CostTypeChangeListener typeChangeListener = new CostTypeChangeListener();
  private final CostValueChangeListener valueChangeListener = new CostValueChangeListener();

  public FxCostSelectionView(final String text, SelectionViewFactory viewFactory) {
    selectionViewFactory = viewFactory;
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        selection = selectionViewFactory.create(text, new SimpleUiConfiguration());
        pane.add(selection.getNode());
        pane.add(spinner.getNode(), new CC().alignY("center"));
        selection.addObjectSelectionChangedListener(typeChangeListener);
        spinner.addListener(valueChangeListener);
      }
    });
  }

  @Override
  public void setValue(final ItemCost cost) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        if (cost == null) {
          selectTypeSilently(null);
          spinner.setValueSilently(0);
        } else {
          selectTypeSilently(cost.getType());
          spinner.setValueSilently(cost.getValue());
        }
      }
    });
  }

  private void selectTypeSilently(String type) {
    selection.removeObjectSelectionChangedListener(typeChangeListener);
    selection.setSelectedObject(type);
    selection.addObjectSelectionChangedListener(typeChangeListener);
  }

  @Override
  public void addSelectionChangedListener(final ISelectionIntValueChangedListener<String> listener) {
    announcer.addListener(listener);
  }

  @Override
  public void setSelectableBackgrounds(final String[] backgrounds) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        selection.setObjects(backgrounds);
      }
    });
  }

  public Node getNode() {
    return pane;
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
package net.sf.anathema.character.library.selection;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ObjectValueListener;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStringEntryTraitPresenter<V> {

  private final IStringEntryTraitModel<V> model;
  private final Map<V, IRemovableTraitView< ? >> viewsByEntry = new HashMap<>();
  private final IRemovableStringEntriesView< ? > view;

  public AbstractStringEntryTraitPresenter(IStringEntryTraitModel<V> model, IRemovableStringEntriesView< ? > view) {
    this.model = model;
    this.view = view;
  }

  protected void initModelListening(final BasicUi basicUi, final IStringSelectionView selectionView) {
    model.addModelChangeListener(new IRemovableEntryListener<V>() {
      @Override
      public void entryAdded(V v) {
        addSubView(basicUi, v);
        reset(selectionView);
      }

      @Override
      public void entryRemoved(V v) {
        IRemovableEntryView removableView = viewsByEntry.get(v);
        view.removeEntryView(removableView);
      }

      @Override
      public void entryAllowed(boolean complete) {
        selectionView.setAddButtonEnabled(complete);
      }
    });
  }

  protected final void addSubView(BasicUi basicUi, V v) {
    IRemovableTraitView< ? > subView = createSubView(basicUi, v);
    viewsByEntry.put(v, subView);
  }

  protected final void addSubView(V v, IRemovableTraitView< ? > subView) {
    viewsByEntry.put(v, subView);
  }

  protected final IRemovableTraitView< ? > getSubView(V v) {
    return viewsByEntry.get(v);
  }

  protected abstract IRemovableTraitView< ? > createSubView(BasicUi basicUi, V v);

  protected final void initSelectionViewListening(IStringSelectionView selectionView) {
    selectionView.addTextChangeListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        model.setCurrentName(newValue);
      }
    });
    selectionView.addAddButtonListener(new Command(){
      @Override
      public void execute() {
        model.commitSelection();
      }
    });
  }

  protected final void reset(IStringSelectionView selectionView) {
    selectionView.clear();
    model.setCurrentName(null);
  }
}

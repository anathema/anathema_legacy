package net.sf.anathema.character.library.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public abstract class AbstractStringEntryTraitPresenter<V> extends AbstractTraitPresenter {

  private final IStringEntryTraitModel<V> model;
  private final Map<V, IRemovableTraitView< ? >> viewsByEntry = new HashMap<V, IRemovableTraitView< ? >>();
  private final IRemovableStringEntriesView< ? > view;

  public AbstractStringEntryTraitPresenter(IStringEntryTraitModel<V> model, IRemovableStringEntriesView< ? > view) {
    this.model = model;
    this.view = view;
  }

  protected final void initModelListening(final BasicUi basicUi, final IStringSelectionView selectionView) {
    model.addModelChangeListener(new IRemovableEntryListener<V>() {
      public void entryAdded(final V v) {
        addSubView(basicUi, v);
        reset(selectionView);
      }

      public void entryRemoved(V v) {
        IRemovableEntryView removableView = viewsByEntry.get(v);
        view.removeEntryView(removableView);
      }

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

  protected abstract IRemovableTraitView< ? > createSubView(BasicUi basicUi, V v);

  protected final void initSelectionViewListening(IStringSelectionView selectionView) {
    selectionView.addTextChangeListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setCurrentName(newValue);
      }
    });
    selectionView.addAddButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.commitSelection();
      }
    });
  }

  protected final void reset(final IStringSelectionView selectionView) {
    selectionView.clear();
    model.setCurrentName(null);
  }
}

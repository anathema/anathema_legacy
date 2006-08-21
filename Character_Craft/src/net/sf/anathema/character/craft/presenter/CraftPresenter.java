package net.sf.anathema.character.craft.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.selection.AbstractStringEntryTraitPresenter;
import net.sf.anathema.character.library.selection.IRemovableStringEntriesView;
import net.sf.anathema.character.library.selection.IStringSelectionView;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class CraftPresenter extends AbstractStringEntryTraitPresenter<ISubTrait> implements IPresenter {

  private final ICraftModel model;
  private final IRemovableStringEntriesView<SimpleTraitView> view;
  private final IResources resources;

  public CraftPresenter(ICraftModel model, IRemovableStringEntriesView<SimpleTraitView> view, IResources resources) {
    super(model, view);
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String labelText = resources.getString("Crafts.SelectionLabel"); //$NON-NLS-1$
    BasicUi basicUi = new BasicUi(resources);
    IStringSelectionView selectionView = view.addSelectionView(labelText, basicUi.getAddIcon());
    initSelectionViewListening(selectionView);
    initModelListening(basicUi, selectionView);
    for (ISubTrait craft : model.getEntries()) {
      if (!model.isRemovable(craft)) {
        addFixedSubView(basicUi, craft);
      }
      else {
        addSubView(basicUi, craft);
      }
    }
    reset(selectionView);
    initButtons(model.isExperienced());
  }

  @Override
  protected void initModelListening(BasicUi basicUi, IStringSelectionView selectionView) {
    super.initModelListening(basicUi, selectionView);
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        initButtons(experienced);
      }
    });
  }

  private void initButtons(boolean experienced) {
    for (ISubTrait trait : model.getEntries()) {
      if (!model.isRemovable(trait)) {
        continue;
      }
      IRemovableTraitView< ? > subView = getSubView(trait);
      if (trait.getCreationValue() > 0) {
        subView.setButtonEnabled(!experienced);
      }
    }
  }

  private void addFixedSubView(BasicUi basicUi, ISubTrait craft) {
    IRemovableTraitView< ? > subView = createSubView(basicUi, craft);
    subView.setButtonEnabled(false);
    addSubView(craft, subView);
  }

  @Override
  protected IRemovableTraitView< ? > createSubView(BasicUi basicUi, final ISubTrait craft) {
    final IRemovableTraitView< ? > craftView = view.addEntryView(basicUi.getRemoveIcon(), craft.getName());
    craftView.setValue(craft.getCurrentValue());
    addModelValueListener(craft, craftView);
    addViewValueListener(craftView, craft);
    craftView.addButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.removeEntry(craft);
      }
    });
    return craftView;
  }
}
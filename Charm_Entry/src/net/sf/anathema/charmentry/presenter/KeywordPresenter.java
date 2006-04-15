package net.sf.anathema.charmentry.presenter;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;

import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class KeywordPresenter {

  private final CharmEntryModel model;
  private final IKeywordView view;
  private final IResources resources;
  private final Map<ICharmAttribute, IRemovableEntryView> viewsByAttribute = new HashMap<ICharmAttribute, IRemovableEntryView>();

  public KeywordPresenter(CharmEntryModel model, IKeywordView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    final BasicUi basicUi = new BasicUi(resources);
    final IButtonControlledObjectSelectionView selectionView = view.addObjectSelectionView(
        new DefaultListCellRenderer(),
        resources.getString("CharmEntry.Keyword"), basicUi.getMediumAddIcon()); //$NON-NLS-1$
    selectionView.setObjects(new Object[] {
        IExtendedCharmData.COMBO_BASIC_ATTRIBUTE,
        IExtendedCharmData.COMBO_OK_ATTRIBUTE,
        IExtendedCharmData.COMPULSION_ATTRIBUTE });
    selectionView.addObjectSelectionChangedListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(IIdentificate newValue) {
        model.getKeywordModel().setCurrentKeyword(newValue);
      }
    });
    selectionView.addButtonListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(IIdentificate newValue) {
        model.getKeywordModel().commitSelection();
      }
    });
    model.getKeywordModel().addModelChangeListener(new IRemovableEntryListener<ICharmAttribute>() {
      public void entryAdded(ICharmAttribute entry) {
        IRemovableEntryView entryView = view.addEntryView(basicUi.getMediumRemoveIcon(), entry.getId());
        viewsByAttribute.put(entry, entryView);
      }

      public void entryAllowed(boolean complete) {
        selectionView.setButtonEnabled(complete);
      }

      public void entryRemoved(ICharmAttribute entry) {
        view.removeEntryView(viewsByAttribute.get(entry));
        viewsByAttribute.remove(entry);
      }
    });
    selectionView.setButtonEnabled(false);
  }
}
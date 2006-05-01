package net.sf.anathema.charmentry.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;

import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class KeywordEntryPresenter implements ICharmEntrySubPresenter {

  private final CharmEntryModel model;
  private final IKeywordView view;
  private final IResources resources;
  private final Map<ICharmAttribute, IRemovableEntryView> viewsByAttribute = new HashMap<ICharmAttribute, IRemovableEntryView>();

  public KeywordEntryPresenter(CharmEntryModel model, IKeywordView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    final BasicUi basicUi = new BasicUi(resources);
    final IButtonControlledObjectSelectionView selectionView = view.addObjectSelectionView(
        new DefaultListCellRenderer(),
        resources.getString("CharmEntry.Keyword"), basicUi.getMediumAddIcon()); //$NON-NLS-1$
    selectionView.setObjects(getAllKeywords());
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
      public void entryAdded(final ICharmAttribute entry) {
        IRemovableEntryView entryView = view.addEntryView(basicUi.getMediumRemoveIcon(), entry.getId());
        viewsByAttribute.put(entry, entryView);
        entryView.addButtonListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            model.getKeywordModel().removeEntry(entry);
          }
        });
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

  private IIdentificate[] getAllKeywords() {
    return new IIdentificate[] {
        ICharmData.ALLOWS_CELESTIAL_ATTRIBUTE,
        ICharmData.FORM_ATTRIBUTE,
        ICharmData.NO_STYLE_ATTRIBUTE,
        ICharmData.NOT_ALIEN_LEARNABLE,
        ICharmData.UNRESTRICTED_ATTRIBUTE,
        IExtendedCharmData.COMBO_BASIC_ATTRIBUTE,
        IExtendedCharmData.COMBO_OK_ATTRIBUTE,
        IExtendedCharmData.COMPULSION_ATTRIBUTE,
        IExtendedCharmData.COUNTERATTACK_ATTRIBUTE,
        IExtendedCharmData.CRIPPLING_ATTRIBUTE,
        IExtendedCharmData.EMOTION_ATTRIBUTE,
        IExtendedCharmData.EXCLUSIVE_ATTRIBUTE,
        IExtendedCharmData.HOLY_ATTRIBUTE,
        IExtendedCharmData.ILLUSION_ATTRIBUTE,
        IExtendedCharmData.KNOCKBACK_ATTRIBUTE,
        IExtendedCharmData.MANDATE_ATTRIBUTE,
        IExtendedCharmData.OBVIOUS_ATTRIBUTE,
        IExtendedCharmData.POISON_ATTRIBUTE,
        IExtendedCharmData.SERVITUDE_ATTRIBUTE,
        IExtendedCharmData.SHAPING_ATTRIBUTE,
        IExtendedCharmData.SICKNESS_ATTRIBUTE,
        IExtendedCharmData.SOCIAL_ATTRIBUTE,
        IExtendedCharmData.STACKABLE_ATTRIBUTE,
        IExtendedCharmData.TOUCH_ATTRIBUTE,
        IExtendedCharmData.TRAINING_ATTRIBUTE,
        IExtendedCharmData.WAR_ATTRIBUTE };
  }

  public void charmAdded(IConfigurableCharmData charmData) {
    // Nothing to do
  }
}
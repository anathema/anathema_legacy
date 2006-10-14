package net.sf.anathema.character.linguistics.presenter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.util.ProxyComboBoxEditor;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class LinguisticsPresenter implements IPresenter {

  private final ILinguisticsModel model;
  private final ILinguisticsView view;
  private final IResources resources;
  private final Map<IIdentificate, IRemovableEntryView> viewsByEntry = new HashMap<IIdentificate, IRemovableEntryView>();
  private final Map<String, IIdentificate> languagesByDisplayName = new HashMap<String, IIdentificate>();

  public LinguisticsPresenter(ILinguisticsModel model, ILinguisticsView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String labelText = resources.getString("Linguistics.SelectionView.Label"); //$NON-NLS-1$
    final BasicUi basicUi = new BasicUi(resources);
    Icon addIcon = basicUi.getAddIcon();
    ProxyComboBoxEditor editor = new ProxyComboBoxEditor() {
      @Override
      public void setItem(Object anObject) {
        super.setItem(getDisplayString(anObject));
      }
    };
    ListCellRenderer renderer = new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        return super.getListCellRendererComponent(list, getDisplayString(value), index, isSelected, cellHasFocus);
      }
    };
    final IButtonControlledObjectSelectionView<Object> selectionView = view.addSelectionView(
        labelText,
        editor,
        renderer,
        addIcon);
    selectionView.setObjects(model.getPredefinedLanguages());
    selectionView.addObjectSelectionChangedListener(new IObjectValueChangedListener<Object>() {
      public void valueChanged(Object newValue) {
        if (newValue == null) {
          return;
        }
        IIdentificate definedLanguage = getLanguage(newValue);
        if (definedLanguage == null) {
          model.selectBarbarianLanguage(newValue.toString());
        }
        else {
          model.selectLanguage(definedLanguage);
        }
      }
    });
    selectionView.addButtonListener(new IObjectValueChangedListener<Object>() {
      public void valueChanged(Object newValue) {
        if (!model.isEntryAllowed()) {
          return;
        }
        model.commitSelection();
      }
    });
    model.addModelChangeListener(new IRemovableEntryListener<IIdentificate>() {
      public void entryAdded(final IIdentificate entry) {
        IRemovableEntryView entryView = view.addEntryView(basicUi.getRemoveIcon(), getDisplayString(entry));
        viewsByEntry.put(entry, entryView);
        entryView.addButtonListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            model.removeEntry(entry);
          }
        });
        selectionView.setSelectedObject(null);
      }

      public void entryAllowed(boolean complete) {
        selectionView.setButtonEnabled(complete);
      }

      public void entryRemoved(IIdentificate entry) {
        IRemovableEntryView entryView = viewsByEntry.remove(entry);
        view.removeEntryView(entryView);
      }
    });
    for (IIdentificate language : model.getPredefinedLanguages()) {
      languagesByDisplayName.put(getDisplayString(language), language);
    }
  }

  private IIdentificate getLanguage(Object anObject) {
    if (anObject instanceof IIdentificate) {
      return (IIdentificate) anObject;
    }
    String displayName = anObject.toString();
    IIdentificate language = languagesByDisplayName.get(displayName);
    if (language != null) {
      return language;
    }
    return model.getPredefinedLanguageById(displayName);
  }

  private String getDisplayString(Object object) {
    if (object == null) {
      return null;
    }
    if (model.isPredefinedLanguage(object)) {
      return resources.getString("Language." + ((IIdentificate) object).getId()); //$NON-NLS-1$
    }
    return object.toString();
  }
}

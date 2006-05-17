package net.sf.anathema.charmentry.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.IKeywordEntryModel;
import net.sf.anathema.charmentry.presenter.view.IKeywordView;
import net.sf.anathema.charmentry.properties.IKeywordEntryPageProperties;
import net.sf.anathema.charmentry.properties.KeywordEntryPageProperties;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class KeywordEntryPage extends AbstractAnathemaWizardPage {

  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final IKeywordEntryPageProperties properties;
  private IKeywordView view;
  private final Map<ICharmAttribute, IRemovableEntryView> viewsByAttribute = new HashMap<ICharmAttribute, IRemovableEntryView>();

  public KeywordEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.properties = new KeywordEntryPageProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    // nothing to do
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    // nothing to do
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createKeywordEntryView();
    final IButtonControlledObjectSelectionView selectionView = view.addObjectSelectionView(
        properties.getKeywordRenderer(),
        properties.getKeywordLabel(),
        properties.getAddIcon());
    selectionView.setObjects(getPageModel().getAvailableKeywords());
    selectionView.addObjectSelectionChangedListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(IIdentificate newValue) {
        getPageModel().setCurrentKeyword(newValue);
      }
    });
    selectionView.addButtonListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(IIdentificate newValue) {
        getPageModel().commitSelection();
      }
    });
    getPageModel().addModelChangeListener(new IRemovableEntryListener<ICharmAttribute>() {
      public void entryAdded(final ICharmAttribute entry) {
        IRemovableEntryView entryView = view.addEntryView(properties.getRemoveIcon(), entry.getId());
        viewsByAttribute.put(entry, entryView);
        entryView.addButtonListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            getPageModel().removeEntry(entry);
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

  private IKeywordEntryModel getPageModel() {
    return model.getKeywordEntryModel();
  }

  public boolean canFinish() {
    return true;
  }

  public String getDescription() {
    return properties.getPageTitle();
  }

  public IBasicMessage getMessage() {
    return properties.getDefaultMessage();
  }

  public IPageContent getPageContent() {
    return view;
  }
}
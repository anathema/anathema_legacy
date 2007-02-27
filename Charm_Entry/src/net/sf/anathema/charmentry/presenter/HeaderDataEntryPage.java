package net.sf.anathema.charmentry.presenter;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmentry.model.IHeaderDataModel;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.ISourceEntryModel;
import net.sf.anathema.charmentry.presenter.view.IHeaderDataEntryView;
import net.sf.anathema.charmentry.properties.CharmEntryProperties;
import net.sf.anathema.charmentry.view.ISourceSelectionView;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class HeaderDataEntryPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final CharmEntryProperties properties;
  private IHeaderDataEntryView view;

  public HeaderDataEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.properties = new CharmEntryProperties(resources);
    this.resources = resources;
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(
        new SecondEditionCharmTypeEntryPage(resources, model, viewFactory),
        inputListener,
        new ICondition() {
          public boolean isFullfilled() {
            return isCharacterTypeSelected()
                && isNameDefined()
                && getPageModel().getEdition() == ExaltedEdition.SecondEdition;
          }
        });
    addFollowupPage(new CharmTypeEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFullfilled() {
        return isCharacterTypeSelected()
            && isNameDefined()
            && getPageModel().getEdition() == ExaltedEdition.FirstEdition;
      }
    });
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().addModelListener(inputListener);
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createHeaderDataEntryView();
    initNameView();
    final IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer("", resources); //$NON-NLS-1$
    initTypeView(renderer);
    initEditionView();
    initSourceView();
  }

  private void initNameView() {
    ITextView nameView = view.addLineTextRow(properties.getCharmNameLabel());
    nameView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().getName().setText(newValue);
      }
    });
  }

  private void initEditionView() {
    final IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer("Edition.", resources); //$NON-NLS-1$
    IObjectSelectionView<IExaltedEdition> editionView = view.addComboBoxRow(
        properties.getEditionLabel(),
        renderer,
        getPageModel().getEditions());
    editionView.addObjectSelectionChangedListener(new IObjectValueChangedListener<IExaltedEdition>() {
      public void valueChanged(IExaltedEdition newValue) {
        getPageModel().setExaltedEdition(newValue);
      }
    });
  }

  private void initTypeView(final IdentificateSelectCellRenderer renderer) {
    IObjectSelectionView<ICharacterType> typeView = view.addComboBoxRow(
        properties.getCharacterTypeLabel(),
        renderer,
        getPageModel().getCharacterTypes());
    typeView.addObjectSelectionChangedListener(new IObjectValueChangedListener<ICharacterType>() {
      public void valueChanged(ICharacterType newValue) {
        getPageModel().setCharacterType(newValue);
      }
    });
  }

  private void initSourceView() {
    final ISourceEntryModel sourceModel = getPageModel().getSourceModel();
    final ISourceSelectionView sourceView = view.addSourceView(
        properties.getBookLabel(),
        properties.getPageLabel(),
        sourceModel.getLegalSources(),
        new IdentificateSelectCellRenderer("ExaltedSourceBook.", resources));//$NON-NLS-1$
    sourceView.addSourceChangeListener(new IObjectValueChangedListener<IExaltedSourceBook>() {
      public void valueChanged(IExaltedSourceBook newValue) {
        sourceModel.setSourceBook(newValue);
      }
    });
    sourceView.addPageChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        sourceModel.setSourcePage(newValue);
      }
    });
    getPageModel().addChangeListener(new IChangeListener() {
      public void changeOccured() {
        sourceView.setObjects(sourceModel.getLegalSources());
      }
    });
  }

  public boolean canFinish() {
    return false;
  }

  public String getDescription() {
    return properties.getHeaderDataTitle();
  }

  public IBasicMessage getMessage() {
    if (!isNameDefined()) {
      return properties.getUndefinedNameMessage();
    }
    if (!isCharacterTypeSelected()) {
      return properties.getUndefinedTypeMessage();
    }
    if (!isEditionSelected()) {
      return properties.getUndefinedEditionMessage();
    }
    return properties.getHeaderDataMessage();
  }

  private boolean isEditionSelected() {
    return getPageModel().getEdition() != null;
  }

  private boolean isCharacterTypeSelected() {
    return getPageModel().getCharacterType() != null;
  }

  private boolean isNameDefined() {
    return !getPageModel().getName().isEmpty();
  }

  public IPageContent getPageContent() {
    return view;
  }

  private IHeaderDataModel getPageModel() {
    return model.getHeaderDataModel();
  }
}
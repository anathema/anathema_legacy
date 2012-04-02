package net.sf.anathema.charmentry.presenter;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
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
    addFollowupPage(new SecondEditionCharmTypeEntryPage(resources, model, viewFactory), inputListener,
            new ICondition() {
              @Override
              public boolean isFulfilled() {
                return isCharacterTypeSelected() && isNameDefined();
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
    initSourceView();
  }

  private void initNameView() {
    ITextView nameView = view.addLineTextRow(properties.getCharmNameLabel());
    nameView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        getPageModel().getName().setText(newValue);
      }
    });
  }

  private void initTypeView(final IdentificateSelectCellRenderer renderer) {
    IObjectSelectionView<ICharacterType> typeView = view.addComboBoxRow(properties.getCharacterTypeLabel(), renderer,
            getPageModel().getCharacterTypes());
    typeView.addObjectSelectionChangedListener(new IObjectValueChangedListener<ICharacterType>() {
      @Override
      public void valueChanged(ICharacterType newValue) {
        getPageModel().setCharacterType(newValue);
      }
    });
  }

  private void initSourceView() {
    final ISourceEntryModel sourceModel = getPageModel().getSourceModel();
    final ISourceSelectionView sourceView = view.addSourceView(properties.getBookLabel(), properties.getPageLabel(),
            sourceModel.getLegalSources(),
            new IdentificateSelectCellRenderer("ExaltedSourceBook.", resources));//$NON-NLS-1$
    sourceView.addSourceChangeListener(new IObjectValueChangedListener<IExaltedSourceBook>() {
      @Override
      public void valueChanged(IExaltedSourceBook newValue) {
        sourceModel.setSourceBook(newValue);
      }
    });
    sourceView.addPageChangeListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        sourceModel.setSourcePage(newValue);
      }
    });
    getPageModel().addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        sourceView.setObjects(sourceModel.getLegalSources());
      }
    });
  }

  @Override
  public boolean canFinish() {
    return false;
  }

  @Override
  public String getDescription() {
    return properties.getHeaderDataTitle();
  }

  @Override
  public IBasicMessage getMessage() {
    if (!isNameDefined()) {
      return properties.getUndefinedNameMessage();
    }
    if (!isCharacterTypeSelected()) {
      return properties.getUndefinedTypeMessage();
    }
    return properties.getHeaderDataMessage();
  }

  private boolean isCharacterTypeSelected() {
    return getPageModel().getCharacterType() != null;
  }

  private boolean isNameDefined() {
    return !getPageModel().getName().isEmpty();
  }

  @Override
  public IPageContent getPageContent() {
    return view;
  }

  private IHeaderDataModel getPageModel() {
    return model.getHeaderDataModel();
  }
}
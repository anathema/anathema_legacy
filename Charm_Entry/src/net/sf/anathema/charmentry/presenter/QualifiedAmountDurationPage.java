package net.sf.anathema.charmentry.presenter;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.IDurationEntryModel;
import net.sf.anathema.charmentry.presenter.view.IAmountDurationEntryView;
import net.sf.anathema.charmentry.properties.DurationPageProperties;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class QualifiedAmountDurationPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final DurationPageProperties properties;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private IAmountDurationEntryView view;

  public QualifiedAmountDurationPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new DurationPageProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new PrerequisitesEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFulfilled() {
        return getPageModel().isDurationComplete();
      }
    });
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().addModelListener(inputListener);
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createQualifiedAmountDurationView();
    final IntegerSpinner spinner = view.addRadioButtonSpinner();
    spinner.setMinimum(1);
    spinner.addChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        if (spinner.getComponent().isEnabled()) {
          getPageModel().setValueForAmountDuration(newValue);
        }
      }
    });
    final ObjectSelectionView<ITraitType> abilityBox = view.addRadioButtonComboBox(
        properties.getAbilityString(),
        new IdentificateListCellRenderer(resources),
        AbilityType.values());
    initComboBoxListening(abilityBox);
    final ObjectSelectionView<ITraitType> attributeBox = view.addRadioButtonComboBox(
        properties.getAttributeString(),
        new IdentificateListCellRenderer(resources),
        AttributeType.values());
    initComboBoxListening(attributeBox);
    final ObjectSelectionView<ITraitType> virtueBox = view.addRadioButtonComboBox(
        properties.getVirtueString(),
        new IdentificateListCellRenderer(resources),
        VirtueType.values());
    initComboBoxListening(virtueBox);
    final ObjectSelectionView<ITraitType> otherBox = view.addRadioButtonComboBox(
        properties.getOtherString(),
        new IdentificateListCellRenderer(resources),
        OtherTraitType.values());
    initComboBoxListening(otherBox);
    final ITextView textView = view.addTextView();
    textView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setTextForAmountDuration(newValue);
      }
    });
    view.addTypeChangeListener(new IChangeListener() {
      public void changeOccurred() {
        abilityBox.setSelectedObject(null);
        attributeBox.setSelectedObject(null);
        virtueBox.setSelectedObject(null);
        otherBox.setSelectedObject(null);
        spinner.setValue(1);
        getPageModel().clearDuration();
        if (spinner.getComponent().isEnabled()) {
          getPageModel().setValueForAmountDuration(1);
        }
      }
    });
  }

  private void initComboBoxListening(ObjectSelectionView<ITraitType> selectionView) {
    selectionView.addObjectSelectionChangedListener(new IObjectValueChangedListener<ITraitType>() {
      public void valueChanged(ITraitType newValue) {
        getPageModel().setTraitForAmountDuration(newValue);
      }
    });
  }

  public boolean canFinish() {
    return false;
  }

  public String getDescription() {
    return properties.getDurationPageTitle();
  }

  public IBasicMessage getMessage() {
    return properties.getBasicMessage();
  }

  public IPageContent getPageContent() {
    return view;
  }

  private IDurationEntryModel getPageModel() {
    return model.getDurationModel();
  }
}
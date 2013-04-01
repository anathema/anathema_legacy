package net.sf.anathema.character.equipment.creation.presenter.stats;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.AbstractEquipmentStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.IWeaponStatisticsView;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.CheckInputListener;
import net.sf.anathema.lib.data.ICondition;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public abstract class AbstractEquipmentStatisticsPresenterPage<M extends IEquipmentStatisticsModel, P extends AbstractEquipmentStatisticsProperties> extends
    AbstractAnathemaWizardPage {

  private final P properties;
  private final M pageModel;
  private final IEquipmentStatisticsCreationModel overallModel;
  private final IEquipmentStatisticsCreationViewFactory viewFactory;
  private final IResources resources;
  private IWeaponStatisticsView view;

  public AbstractEquipmentStatisticsPresenterPage(
      IResources resources,
      P properties,
      IEquipmentStatisticsCreationModel overallModel,
      M pageModel,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    this.properties = properties;
    this.pageModel = pageModel;
    this.resources = resources;
    this.overallModel = overallModel;
    this.viewFactory = viewFactory;
    ITextualDescription name = getPageModel().getName();
    if (name.isEmpty()) {
      name.setText(properties.getDefaultName());
    }
  }

  protected final P getProperties() {
    return properties;
  }

  protected final M getPageModel() {
    return pageModel;
  }

  protected final IEquipmentStatisticsCreationViewFactory getViewFactory() {
    return viewFactory;
  }

  protected final IResources getResources() {
    return resources;
  }

  private boolean isNameDefined() {
    return !pageModel.getName().isEmpty();
  }

  @Override
  public IBasicMessage getMessage() {
    if (!isNameDefined()) {
      return properties.getUndefinedNameMessage();
    }
    if (!isNameUnique()) {
      return properties.getDuplicateNameMessage();
    }
    return properties.getDefaultMessage();
  }

  private boolean isNameUnique() {
    return overallModel.isNameUnique(pageModel.getName().getText());
  }

  @Override
  public final String getDescription() {
    return properties.getPageDescription();
  }

  @Override
  public boolean canFinish() {
    return isNameDefined() && isNameUnique();
  }

  @Override
  protected final void initPageContent() {
    this.view = viewFactory.createEquipmentStatisticsView();
    initNameRow(getProperties().getNameLabel(), getPageModel().getName());
    addAdditionalContent();
  }

  private void initNameRow(String label, ITextualDescription textModel) {
    ITextView textView = view.addLineTextView(label);
    new TextualPresentation().initView(textView, textModel);
  }

  @Override
  protected final void addFollowUpPages(CheckInputListener inputListener) {
    if (!isTagsSupported()) {
      return;
    }
    addFollowupPage(new WeaponTagsPresenterPage(resources, overallModel, viewFactory), inputListener, new ICondition() {
      @Override
      public boolean isFulfilled() {
        return isInLegalState();
      }
    });
  }

  protected boolean isInLegalState() {
    return canFinish();
  }

  protected abstract boolean isTagsSupported();

  protected abstract void addAdditionalContent();

  @Override
  protected final void initModelListening(CheckInputListener inputListener) {
    getPageModel().getName().addTextChangedListener(inputListener);
  }

  @Override
  public final IWeaponStatisticsView getPageContent() {
    return view;
  }

  protected final void addLabelledComponentRow(final String[] labels, final Component[] contents) {
    Preconditions.checkArgument(labels.length == contents.length, "Same number of labels and content items required"); //$NON-NLS-1$
    getPageContent().addView(new AdditiveView() {
      @Override
      public void addTo(JPanel panel, CC data) {
        for (int index = 0; index < contents.length; index++) {
          panel.add(new JLabel(labels[index]));
          panel.add(contents[index], new CC().growX().pushX());
        }
      }
    }, new CC());
  }

  protected final IntegerSpinner initIntegerSpinner(IIntValueModel intModel) {
    IntegerSpinner spinner = new IntegerSpinner(intModel.getValue());
    new IntValuePresentation().initView(spinner, intModel);
    return spinner;
  }
}
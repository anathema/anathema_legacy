package net.sf.anathema.character.equipment.creation.view.swing;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.AbstractEquipmentStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public abstract class AbstractEquipmentStatisticsPresenterPage<M extends IEquipmentStatisticsModel, P extends AbstractEquipmentStatisticsProperties> extends
        AbstractDialogPage {

  private final P properties;
  private final M pageModel;
  private final IEquipmentStatisticsCreationModel overallModel;
  private final Resources resources;
  private IWeaponStatisticsView view;

  public AbstractEquipmentStatisticsPresenterPage(
          Resources resources,
          P properties,
          IEquipmentStatisticsCreationModel overallModel,
          M pageModel) {
    super(properties.getDefaultMessage().getText());
    this.properties = properties;
    this.pageModel = pageModel;
    this.resources = resources;
    this.overallModel = overallModel;
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

  protected final Resources getResources() {
    return resources;
  }

  public IEquipmentStatisticsCreationModel getOverallModel() {
    return overallModel;
  }

  private boolean isNameDefined() {
    return !pageModel.getName().isEmpty();
  }

  @Override
  public IBasicMessage createCurrentMessage() {
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
  public String getTitle() {
    return properties.getPageDescription();
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
  public final JComponent createContent() {
    this.view = new WeaponStatisticsView();
    initNameRow(getProperties().getNameLabel(), getPageModel().getName());
    addAdditionalContent();
    return view.getContent();
  }

  private void initNameRow(String label, ITextualDescription textModel) {
    ITextView textView = view.addLineTextView(label);
    new TextualPresentation().initView(textView, textModel);
  }

  protected abstract void addAdditionalContent();

  @Override
  public void setInputValidListener(ChangeListener inputValidListener) {
    getPageModel().getName().addTextChangedListener(new CheckInputListener(new Runnable() {
      @Override
      public void run() {
        inputValidListener.changeOccurred();
      }
    }));
  }

  protected final void addView(AdditiveView view) {
    this.view.addView(view);
  }

  protected final void addLabelledComponentRow(final String[] labels, final Component[] contents) {
    Preconditions.checkArgument(labels.length == contents.length, "Same number of labels and content items required");
    addView(new AdditiveView() {
      @Override
      public void addTo(JPanel panel) {
        for (int index = 0; index < contents.length; index++) {
          panel.add(new JLabel(labels[index]));
          panel.add(contents[index], new CC().growX().pushX());
        }
      }
    });
  }

  protected final IntegerSpinner initIntegerSpinner(IIntValueModel intModel) {
    IntegerSpinner spinner = new IntegerSpinner(intModel.getValue());
    new IntValuePresentation().initView(spinner, intModel);
    return spinner;
  }
}
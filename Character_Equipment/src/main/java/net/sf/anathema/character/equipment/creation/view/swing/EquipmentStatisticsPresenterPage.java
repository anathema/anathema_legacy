package net.sf.anathema.character.equipment.creation.view.swing;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsView;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.AbstractEquipmentStatisticsProperties;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import org.jmock.example.announcer.Announcer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class EquipmentStatisticsPresenterPage<P extends AbstractEquipmentStatisticsProperties> extends AbstractDialogPage implements EquipmentStatsView {

  private ExtensibleEquipmentStatsView view = new ExtensibleEquipmentStatsView();
  private final Announcer<ChangeListener> announcer = Announcer.to(ChangeListener.class);
  private boolean canCurrentlyFinish;
  private IBasicMessage message;
  private String title;
  private String description;

  public EquipmentStatisticsPresenterPage() {
    super("");
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return message;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public final String getDescription() {
    return description;
  }

  @Override
  public boolean canFinish() {
    return canCurrentlyFinish;
  }

  @Override
  public final JComponent createContent() {
    return view.getComponent();
  }

  @Override
  public void setInputValidListener(ChangeListener inputValidListener) {
    announcer.addListener(inputValidListener);
    refreshFinishingState();
  }

  public final void addView(AdditiveView view) {
    this.view.addView(view);
  }

  public final void addLabelledComponentRow(final String[] labels, final Component[] contents) {
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

  public final IntegerSpinner initIntegerSpinner(IIntValueModel intModel) {
    IntegerSpinner spinner = new IntegerSpinner(intModel.getValue());
    new IntValuePresentation().initView(spinner, intModel);
    return spinner;
  }

  public ITextView addLineTextView(String nameLabel) {
    return view.addLineTextView(nameLabel);
  }

  public void setCanFinish() {
    this.canCurrentlyFinish = true;
    refreshFinishingState();
  }

  public void setCannotFinish() {
    this.canCurrentlyFinish = false;
    refreshFinishingState();
  }

  @Override
  public void setMessage(IBasicMessage message) {
    this.message = message;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  private void refreshFinishingState() {
    announcer.announce().changeOccurred();
  }
}
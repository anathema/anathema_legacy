package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.platform.module.IToggleButtonPanel;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JToggleButton;
import java.awt.Component;
import java.util.Arrays;
import java.util.Comparator;

public class CharacterCreationWizardPage extends AbstractAnathemaWizardPage {
  private final CharacterCreationPageProperties properties;
  private final ICharacterItemCreationModel model;
  private final ICharacterItemCreationView view;

  public CharacterCreationWizardPage(ICharacterItemCreationModel model, ICharacterItemCreationView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.properties = new CharacterCreationPageProperties(resources);
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    //nothing to do;
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    model.addListener(inputListener);
  }

  @Override
  protected void initPageContent() {
    IToggleButtonPanel panel = view.addToggleButtonPanel();
    for (final ICharacterType type : model.getAvailableCharacterTypes()) {
      JToggleButton button = panel.addButton(new SmartAction(properties.getTypeIcon(type)) {
        @Override
        protected void execute(Component parentComponent) {
          model.setCharacterType(type);
        }
      }, properties.getTypeString(type));
      button.setSelected(type == model.getSelectedTemplate().getTemplateType().getCharacterType());
    }
    final IListObjectSelectionView<ITemplateTypeAggregation> list = view.addObjectSelectionList();
    list.addObjectSelectionChangedListener(new ObjectValueListener<ITemplateTypeAggregation>() {
      @Override
      public void valueChanged(ITemplateTypeAggregation newValue) {
        if (newValue == null) {
          return;
        }
        model.setSelectedTemplate(newValue);
      }
    });
    list.setCellRenderer(properties.getTemplateRenderer());
    model.addListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        refreshList(list);
      }
    });
    refreshList(list);
  }

  protected void refreshList(IListObjectSelectionView<ITemplateTypeAggregation> list) {
    ITemplateTypeAggregation[] availableTemplates = model.getAvailableTemplates();
    Arrays.sort(availableTemplates, new Comparator<ITemplateTypeAggregation>() {
      @Override
      public int compare(ITemplateTypeAggregation o1, ITemplateTypeAggregation o2) {
        return getTemplateResource(o1).compareTo(getTemplateResource(o2));
      }
    });
    list.setObjects(availableTemplates);
    list.setSelectedObject(model.getSelectedTemplate());
  }

  private String getTemplateResource(ITemplateTypeAggregation o1) {
    return o1.getPresentationProperties().getNewActionResource();
  }

  @Override
  public boolean canFinish() {
    return model.isSelectionComplete();
  }

  @Override
  public String getDescription() {
    return properties.getDescription();
  }

  @Override
  public IBasicMessage getMessage() {
    if (!model.isCharacterTypeSelected()) {
      return properties.getSelectCharacterTypeMessage();
    }
    if (model.getAvailableTemplates().length == 0) {
      return properties.getNoTemplatesAvailableMessage();
    }
    return properties.getConfirmMessage();
  }

  @Override
  public IPageContent getPageContent() {
    return view;
  }
}
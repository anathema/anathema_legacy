package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.platform.module.IToggleButtonPanel;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;
import javax.swing.JToggleButton;
import java.awt.Component;
import java.util.Arrays;
import java.util.Comparator;

public class CharacterCreationDialogPage extends AbstractDialogPage {
  private final CharacterCreationPageProperties properties;
  private final ICharacterItemCreationModel model;
  private final ICharacterItemCreationView view;

  public CharacterCreationDialogPage(ICharacterItemCreationModel model, ICharacterItemCreationView view, CharacterCreationPageProperties properties) {
    super(properties.getConfirmMessage().getText());
    this.model = model;
    this.view = view;
    this.properties = properties;
  }

  @Override
  public void setInputValidListener(IChangeListener inputValidListener) {
    model.addListener(inputValidListener);
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
  public String getTitle() {
    return  properties.getDescription();
  }

  @Override
  public String getDescription() {
    return properties.getDescription();
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    if (!model.isCharacterTypeSelected()) {
      return properties.getSelectCharacterTypeMessage();
    }
    if (model.getAvailableTemplates().length == 0) {
      return properties.getNoTemplatesAvailableMessage();
    }
    return properties.getConfirmMessage();
  }

  @Override
  public JComponent createContent() {
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
    return view.getContent();
  }
}
package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
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
  public void setInputValidListener(ChangeListener inputValidListener) {
    model.addListener(inputValidListener);
  }

  protected void refreshList(VetoableObjectSelectionView<ITemplateTypeAggregation> list) {
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
    return properties.getTemplateUI().getLabel(o1);
  }

  @Override
  public boolean canFinish() {
    return model.isSelectionComplete();
  }

  @Override
  public String getTitle() {
    return  properties.getTitle();
  }

  @Override
  public String getDescription() {
    return properties.getTitle();
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return properties.getConfirmMessage();
  }

  @Override
  public JComponent createContent() {
    IToggleButtonPanel panel = view.addToggleButtonPanel();
    for (final CharacterType type : model.getAvailableCharacterTypes()) {
      JToggleButton button = panel.addButton(new SmartAction(properties.getTypeIcon(type)) {
        @Override
        protected void execute(Component parentComponent) {
          model.setCharacterType(type);
        }
      }, properties.getTypeString(type));
      button.setSelected(type.equals(model.getSelectedTemplate().getTemplateType().getCharacterType()));
    }
    final VetoableObjectSelectionView<ITemplateTypeAggregation> list = view.addObjectSelectionList();
    list.addObjectSelectionChangedListener(new ObjectValueListener<ITemplateTypeAggregation>() {
      @Override
      public void valueChanged(ITemplateTypeAggregation newValue) {
        if (newValue == null) {
          return;
        }
        model.setSelectedTemplate(newValue);
      }
    });
    list.setCellRenderer(properties.getTemplateUI());
    model.addListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        refreshList(list);
      }
    });
    refreshList(list);
    return view.getContent();
  }
}
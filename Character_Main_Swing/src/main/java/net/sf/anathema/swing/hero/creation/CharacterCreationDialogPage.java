package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;
import java.util.Arrays;
import java.util.Comparator;

public class CharacterCreationDialogPage extends AbstractDialogPage {
  private final CharacterCreationPageProperties properties;
  private final ICharacterItemCreationModel model;
  private final CharacterCreationView view;

  public CharacterCreationDialogPage(ICharacterItemCreationModel model, CharacterCreationView view,
                                     CharacterCreationPageProperties properties) {
    super(properties.getConfirmMessage().getText());
    this.model = model;
    this.view = view;
    this.properties = properties;
  }

  @Override
  public void setInputValidListener(ChangeListener inputValidListener) {
    model.addListener(inputValidListener);
  }

  protected void refreshList(VetoableObjectSelectionView<HeroTemplate> list) {
    HeroTemplate[] availableTemplates = model.getAvailableTemplates();
    Arrays.sort(availableTemplates, new Comparator<HeroTemplate>() {
      @Override
      public int compare(HeroTemplate o1, HeroTemplate o2) {
        return getTemplateResource(o1).compareTo(getTemplateResource(o2));
      }
    });
    list.setObjects(availableTemplates);
    list.setSelectedObject(model.getSelectedTemplate());
  }

  private String getTemplateResource(HeroTemplate o1) {
    return properties.getTemplateUI().getLabel(o1);
  }

  @Override
  public boolean canFinish() {
    return true;
  }

  @Override
  public String getTitle() {
    return properties.getTitle();
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
    ToggleButtonPanel panel = view.addToggleButtonPanel();
    for (final CharacterType type : model.getAvailableCharacterTypes()) {
      ToggleTool button = panel.addButton(properties.getTypeString(type));
      button.setIcon(properties.getTypeIcon(type));
      button.setCommand(new Command() {
        @Override
        public void execute() {
          model.setCharacterType(type);
        }
      });
      if (type.equals(model.getSelectedTemplate().getTemplateType().getCharacterType())) {
        button.select();
      } else {
        button.deselect();
      }
    }
    final VetoableObjectSelectionView<HeroTemplate> list = view.addObjectSelectionList();
    list.addObjectSelectionChangedListener(new ObjectValueListener<HeroTemplate>() {
      @Override
      public void valueChanged(HeroTemplate newValue) {
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
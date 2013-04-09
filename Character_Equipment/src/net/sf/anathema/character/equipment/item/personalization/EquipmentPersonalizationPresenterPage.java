package net.sf.anathema.character.equipment.item.personalization;

import net.sf.anathema.character.equipment.character.model.IEquipmentPersonalizationModel;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JComponent;

public class EquipmentPersonalizationPresenterPage extends AbstractDialogPage {

  private final EquipmentPersonalizationProperties properties;
  private final IEquipmentPersonalizationModel model;
  private EquipmentPersonalizationView view;

  public EquipmentPersonalizationPresenterPage(IEquipmentPersonalizationModel model,
                                               EquipmentPersonalizationProperties personalizationProperties) {
    super(personalizationProperties.getPersonalizeMessage());
    this.properties = personalizationProperties;
    this.view = new EquipmentPersonalizationView();
    this.model = model;
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  @Override
  public boolean canFinish() {
    return true;
  }

  @Override
  public String getTitle() {
    return properties.getPersonalizeDetails();
  }

  @Override
  public JComponent createContent() {
    addField(properties.getTitleMessage(), model.getTitle(), new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        model.setTitle(newValue);
      }
    });
    addField(properties.getDescriptionMessage(), model.getDescription(), new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newText) {
        model.setDescription(newText);
      }
    });
    return view.getContent();
  }

  private void addField(String label, String content, ObjectValueListener<String> listener) {
    ITextView textView = view.addEntry(label);
    if (content != null) {
      textView.setText(content);
    }
    textView.addTextChangedListener(listener);
  }

  @Override
  public String getDescription() {
    return properties.getPersonalizeDetails();
  }
}
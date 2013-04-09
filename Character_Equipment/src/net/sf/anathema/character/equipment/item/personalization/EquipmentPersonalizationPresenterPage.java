package net.sf.anathema.character.equipment.item.personalization;

import net.sf.anathema.character.equipment.character.model.IEquipmentPersonalizationModel;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.event.AbstractDocumentListener;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class EquipmentPersonalizationPresenterPage extends AbstractDialogPage {

  private final EquipmentPersonalizationProperties properties;
  private final IEquipmentPersonalizationModel model;
  private EquipmentPersonalizationView view;

  public EquipmentPersonalizationPresenterPage(IEquipmentPersonalizationModel model, EquipmentPersonalizationProperties personalizationProperties) {
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
    addField(properties.getTitleMessage(), model.getTitle(), new ITextFieldChangedListener() {
      @Override
      public void textChanged(String newText) {
        model.setTitle(newText);
      }
    });
    addField(properties.getDescriptionMessage(), model.getDescription(), new ITextFieldChangedListener() {
      @Override
      public void textChanged(String newText) {
        model.setDescription(newText);
      }
    });
    return view.getContent();
  }

  private void addField(String label, String content, final ITextFieldChangedListener listener) {
    JTextField box = new JTextField();
    if (content != null) {
      box.setText(content);
    }
    box.getDocument().addDocumentListener(new AbstractDocumentListener() {
      @Override
      protected void updateText(DocumentEvent e) {
        try {
          Document document = e.getDocument();
          listener.textChanged(document.getText(0, document.getLength()));
        } catch (BadLocationException ignored) {
        }
      }
    });
    view.addEntry(label, box);
  }

  @Override
  public String getDescription() {
    return properties.getPersonalizeDetails();
  }
}
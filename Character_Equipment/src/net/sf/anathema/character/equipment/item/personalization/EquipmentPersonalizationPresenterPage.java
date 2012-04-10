package net.sf.anathema.character.equipment.item.personalization;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.equipment.character.model.IEquipmentPersonalizationModel;
import net.sf.anathema.character.equipment.item.view.EquipmentPersonalizationView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class EquipmentPersonalizationPresenterPage extends AbstractAnathemaWizardPage {

  private final EquipmentPersonalizationProperties properties;
  private final IEquipmentPersonalizationModel model;
  private final BasicMessage defaultMessage;
  private EquipmentPersonalizationView view;

  public EquipmentPersonalizationPresenterPage(IResources resources, IEquipmentPersonalizationModel model) {
    this.properties = new EquipmentPersonalizationProperties(resources);
    this.defaultMessage = new BasicMessage(properties.getPersonalizeMessage());
    this.view = new EquipmentPersonalizationView();
    this.model = model;
  }

  @Override
  public boolean canFinish() {
    return true;
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    //model.addEquipmentTypeChangeListener(inputListener);
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    // nothing to do
  }

  @Override
  protected void initPageContent() {
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
  }
  
  private void addField(String label, String field, final ITextFieldChangedListener listener) {
	  JTextField box = new JTextField();
	  if (field != null) {
		  box.setText(field);
	  }
	  box.getDocument().addDocumentListener(new DocumentListener() {

		@Override
		public void changedUpdate(DocumentEvent event) {
			try {
				Document document = event.getDocument();
				listener.textChanged(document.getText(0, document.getLength()));
			} catch (BadLocationException e) {
			}
		}

		@Override
		public void insertUpdate(DocumentEvent event) {
			try {
				Document document = event.getDocument();
				listener.textChanged(document.getText(0, document.getLength()));
			} catch (BadLocationException e) {
			}
		}

		@Override
		public void removeUpdate(DocumentEvent event) {
			try {
				Document document = event.getDocument();
				listener.textChanged(document.getText(0, document.getLength()));
			} catch (BadLocationException e) {
			}
		}
		  
	  });
	  view.addEntry(label, box);
  }

  @Override
  public IPageContent getPageContent() {
    return view;
  }

  @Override
  public IBasicMessage getMessage() {
    return defaultMessage;
  }

  @Override
  public String getDescription() {
    return properties.getPersonalizeDetails();
  }
}
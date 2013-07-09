package net.sf.anathema.hero.equipment.display;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.util.Closure;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JComponent;

public class EquipmentPersonalizationPresenterPage extends AbstractDialogPage {

  private final EquipmentPersonalizationProperties properties;
  private final EquipmentPersonalizationView view = new EquipmentPersonalizationView();
  private final ITextView description;
  private final ITextView title;
  private ProxyClosure<String> onTitleChange = new ProxyClosure<>();
  private ProxyClosure<String> onDescriptionChange = new ProxyClosure<>();

  public EquipmentPersonalizationPresenterPage(EquipmentPersonalizationProperties personalizationProperties) {
    super(personalizationProperties.getPersonalizeMessage());
    this.properties = personalizationProperties;
    this.title = addField(properties.getTitleMessage(), new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        onTitleChange.execute(newValue);
      }
    });
    this.description = addField(properties.getDescriptionMessage(), new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newText) {
        onDescriptionChange.execute(newText);
      }
    });
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
    return view.getContent();
  }

  private ITextView addField(String label, ObjectValueListener<String> listener) {
    ITextView textView = view.addEntry(label);
    textView.addTextChangedListener(listener);
    return textView;
  }

  @Override
  public String getDescription() {
    return properties.getPersonalizeDetails();
  }

  public void setDescription(String description) {
    this.description.setText(description);
  }

  public void setTitle(String title) {
    this.title.setText(title);
  }

  public void whenTitleChanges(Closure<String> closure) {
    onTitleChange.setDelegate(closure);
  }

  public void whenDescriptionChanges(Closure<String> closure) {
    onDescriptionChange.setDelegate(closure);
  }
}
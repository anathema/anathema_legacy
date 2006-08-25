package net.sf.anathema.character.equipment.item;

import net.disy.commons.core.message.IMessage;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.message.MessageType;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class EquipmentDatabasePresenter implements IPresenter {

  private static final int COLUMN_COUNT = 45;
  private final IResources resources;
  private final IEquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;

  public EquipmentDatabasePresenter(IResources resources, IEquipmentDatabaseManagement model, IEquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    initTemplateList();
    initDescriptionView();
    view.setTemplateListHeader("Available Templates");
    view.setEditTemplateHeader("Edit Template");
  }

  private void initTemplateList() {
    view.setTemplateListHeader("Available Templates");
    view.getTemplateListView().setObjects(model.getAllAvailableTemplateIds());
    view.getTemplateListView().addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        if (model.getTemplateEditModel().isDirty()) {
          IMessage message = new Message(
              "Sie haben noch ungespeicherte Änderungen. Wollen Sie diese verwerfen?",
              MessageType.WARNING);
          UserDialog userDialog = MessageDialogFactory.createMessageDialog(view.getComponent(), message);
          userDialog.show();
          if (!userDialog.isCanceled()) {
            model.getTemplateEditModel().loadTemplate(newValue);
          }
        }
      }
    });
  }

  private void initDescriptionView() {
    StandardPanelBuilder panelBuilder = new StandardPanelBuilder();
    ITextView nameView = panelBuilder.addLineTextView("Name:", COLUMN_COUNT);
    new TextualPresentation().initView(nameView, model.getTemplateEditModel().getDescription().getName());
    ITextView descriptionView = panelBuilder.addAreaTextView("Description:", 5, COLUMN_COUNT);
    new TextualPresentation().initView(descriptionView, model.getTemplateEditModel().getDescription().getContent());
    view.fillDescriptionPanel(panelBuilder.getTitledContent("Basics"));
  }
}
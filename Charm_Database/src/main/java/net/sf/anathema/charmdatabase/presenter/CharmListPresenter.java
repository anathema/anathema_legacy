package net.sf.anathema.charmdatabase.presenter;

import java.util.Arrays;
import java.util.Comparator;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.charmdatabase.management.ICharmDatabaseManagement;
import net.sf.anathema.charmdatabase.management.model.ICharmEditModel;
import net.sf.anathema.charmdatabase.view.CharmNavigation;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;

public class CharmListPresenter implements Presenter {

  private final class CharmLoadListener implements ObjectValueListener<Charm> {
    @Override
    public void valueChanged(Charm newValue) {
      if (newValue == null) {
        return;
      }
      ICharmEditModel editModel = model.getCharmEditModel();
      editModel.setEditCharm(newValue);
    }
  }

  private final Resources resources;
  private final CharmNavigation view;
  private final ICharmDatabaseManagement model;

  public CharmListPresenter(
      Resources resources,
      ICharmDatabaseManagement model,
      CharmNavigation view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
	model.addCharmListChangeListener(new ChangeListener() {

		@Override
		public void changeOccurred() {
			updateCharmList();
		}
		
	});
    /*model.getDatabase().addAvailableTemplateChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        updateAvailableTemplates();
      }
    });*/
    updateCharmList();
    //view.getTemplateListView().addSelectionVetor(new DiscardChangesVetor(resources, new DirtyEquipmentCondition(model)));
    view.getTemplateListView().addObjectSelectionChangedListener(new CharmLoadListener());
  }

  private void updateCharmList() {
    view.getTemplateListView().setObjects(model.getCharms());
  }
}

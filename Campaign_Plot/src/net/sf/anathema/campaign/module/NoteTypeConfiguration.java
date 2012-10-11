package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.note.model.IBasicItemData;
import net.sf.anathema.campaign.note.persistence.BasicDataItemPersister;
import net.sf.anathema.campaign.note.view.IBasicItemView;
import net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants;
import net.sf.anathema.campaign.presenter.NotePresenter;
import net.sf.anathema.campaign.presenter.TextEditorProperties;
import net.sf.anathema.campaign.view.BasicItemView;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractPersistableItemTypeConfiguration;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.presenter.view.SimpleItemTypeViewProperties;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

@ItemTypeConfiguration
@Weight(weight = 20)
public class NoteTypeConfiguration extends AbstractPersistableItemTypeConfiguration {

  public NoteTypeConfiguration() {
    super(new ItemType("Note", new RepositoryConfiguration(".not", "Notes/"))); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
  }

  @Override
  protected IRepositoryItemPersister createPersister(IAnathemaModel model) {
    return new BasicDataItemPersister(getItemType(), ISeriesPersistenceConstants.TAG_NOTE_ROOT);
  }

  @Override
  protected IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      @Override
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        Icon icon = new PlotUI(resources).getNoteTabIcon();
        IBasicItemView basicItemView = new BasicItemView(printName, icon, new TextEditorProperties(resources));
        IBasicItemData basicItem = (IBasicItemData) item.getItemData();
        new NotePresenter(basicItemView, resources, basicItem).initPresentation();
        return basicItemView;
      }
    };
  }

  @Override
  protected IItemTypeViewProperties createItemTypeCreationProperties(
          IAnathemaModel anathemaModel,
          IResources resources) {
    return new SimpleItemTypeViewProperties(getItemType(), new PlotUI(resources).getNoteTabIcon());
  }
}
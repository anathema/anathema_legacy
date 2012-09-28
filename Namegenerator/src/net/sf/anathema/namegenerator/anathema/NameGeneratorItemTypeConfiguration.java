package net.sf.anathema.namegenerator.anathema;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.MainView;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.namegenerator.exalted.ExaltedNameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.NameGeneratorPresenter;
import net.sf.anathema.namegenerator.presenter.model.INameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.view.INameGeneratorView;
import net.sf.anathema.namegenerator.view.NameGeneratorView;

import javax.swing.Icon;

@ItemTypeConfiguration
@Weight(weight = 20)
public class NameGeneratorItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String NAME_GENERATOR_ITEM_TYPE_ID = "NameGenerator"; //$NON-NLS-1$

  public NameGeneratorItemTypeConfiguration() {
    super(new ItemType(NAME_GENERATOR_ITEM_TYPE_ID, null));
  }

  @Override
  protected IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      @Override
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        Icon icon = new NamegeneratorUI(resources).getNameGeneratorTabIcon();
        INameGeneratorView view = new NameGeneratorView();
        INameGeneratorModel model = new ExaltedNameGeneratorModel();
        new NameGeneratorPresenter(resources, view, model).initPresentation();
        return new DelegatingItemView(printName, icon, view);
      }
    };
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(MainView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[] { new ActionMenuItem(ShowNameGeneratorAction.createMenuAction(resources, anathemaModel)) };
  }
}
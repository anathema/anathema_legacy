package net.sf.anathema.namegenerator.anathema;

import javax.swing.Icon;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.namegenerator.exalted.ExaltedNameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.ExaltedNameGeneratorTypePresentation;
import net.sf.anathema.namegenerator.presenter.INameGeneratorTypePresentation;
import net.sf.anathema.namegenerator.presenter.NameGeneratorPresenter;
import net.sf.anathema.namegenerator.presenter.model.INameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.view.INameGeneratorView;
import net.sf.anathema.namegenerator.view.NameGeneratorView;

public final class NameGeneratorItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String NAME_GENERATOR_ITEM_TYPE_ID = "NameGenerator"; //$NON-NLS-1$

  public NameGeneratorItemTypeConfiguration() {
    super(new ItemType(NAME_GENERATOR_ITEM_TYPE_ID, null));
  }

  @Override
  protected IItemViewFactory createItemViewFactory(final IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        Icon icon = new NamegeneratorUI(resources).getNameGeneratorTabIcon();
        INameGeneratorView view = new NameGeneratorView();
        INameGeneratorModel model = new ExaltedNameGeneratorModel();
        INameGeneratorTypePresentation typePresentation = new ExaltedNameGeneratorTypePresentation();
        new NameGeneratorPresenter(resources, view, model, typePresentation).initPresentation();
        return new DelegatingItemView(printName, icon, view);
      }
    };
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[] { new ActionMenuItem(ShowNameGeneratorAction.createMenuAction(resources, anathemaModel)) };
  }
}
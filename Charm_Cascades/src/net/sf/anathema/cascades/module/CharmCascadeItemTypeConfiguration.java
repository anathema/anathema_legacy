package net.sf.anathema.cascades.module;

import javax.swing.Icon;

import net.sf.anathema.cascades.presenter.CascadePresenter;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
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

public final class CharmCascadeItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String CHARM_CASCADES_ITEM_TYPE_ID = "CharmCascades"; //$NON-NLS-1$

  public CharmCascadeItemTypeConfiguration() {
    super(new ItemType(CHARM_CASCADES_ITEM_TYPE_ID, null));
  }

  @Override
  protected IItemViewFactory createItemViewFactory(final IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        Icon icon = new CascadesUI(resources).getCascadesTabIcon();
        CharmCascadeModuleView view = new CharmCascadeModuleView(printName, icon);
        ICharacterGenericsExtension extension = (ICharacterGenericsExtension) anathemaModel.getExtensionPointRegistry()
            .get(ICharacterGenericsExtension.ID);
        ICharacterGenerics characterGenerics = extension.getCharacterGenerics();
        new CascadePresenter(resources, characterGenerics.getTemplateRegistry()).initPresentation(view);
        return view;
      }
    };
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[] { new ActionMenuItem(ShowCascadesAction.createMenuAction(resources, anathemaModel)) };
  }
}
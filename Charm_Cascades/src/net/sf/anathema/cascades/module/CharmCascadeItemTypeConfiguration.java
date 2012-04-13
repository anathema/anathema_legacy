package net.sf.anathema.cascades.module;

import net.sf.anathema.cascades.presenter.CascadePresenter;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

@ItemTypeConfiguration
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
        ICharacterGenerics characterGenerics = CharacterGenericsExtractor.getGenerics(anathemaModel);
        MagicDescriptionProvider magicDescriptionProvider = getCharmDescriptionProvider();
        new CascadePresenter(resources, characterGenerics,
        		view, magicDescriptionProvider).initPresentation();
        return view;
      }

      private MagicDescriptionProvider getCharmDescriptionProvider() {
        return CharmDescriptionProviderExtractor.CreateFor(anathemaModel, resources);
      }
    };
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[]{new ActionMenuItem(ShowCascadesAction.createMenuAction(resources, anathemaModel))};
  }
}

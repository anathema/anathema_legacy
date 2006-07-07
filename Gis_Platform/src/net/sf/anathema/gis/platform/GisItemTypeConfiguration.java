package net.sf.anathema.gis.platform;

import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.gis.main.impl.model.AnathemaLayerPopupFactory;
import net.sf.anathema.gis.main.model.IGisModel;
import net.sf.anathema.gis.main.presenter.GisPresenter;
import net.sf.anathema.gis.platform.util.DefaultFileChooserProvider;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;
import de.disy.gis.gisterm.pro.map.layer.LayerPanel;

public final class GisItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String GIS_ITEM_TYPE_ID = "Gis.ItemType"; //$NON-NLS-1$

  public GisItemTypeConfiguration() {
    super(new ItemType(GIS_ITEM_TYPE_ID, null));
    customizeGisTermFramework();
  }

  // TODO In start-Methode von Plugin-Klasse verlagern
  private void customizeGisTermFramework() {
    LayerPanel.popupFactory = new AnathemaLayerPopupFactory();
    SmartFileChooser.getInstance().setFileChooserProvider(new DefaultFileChooserProvider());
  }

  @Override
  protected IItemViewFactory createItemViewFactory(final IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        GisModuleView anathemaGisView = new GisModuleView(printName);
        IGisModel model = (IGisModel) item.getItemData();
        new GisPresenter(model, anathemaGisView.addGisView(), resources).initPresentation();
        return anathemaGisView;
      }
    };
  }

  @Override
  protected String getPrintNameKey() {
    return "ItemType.Gis.PrintName"; //$NON-NLS-1$
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[0];
  }
}
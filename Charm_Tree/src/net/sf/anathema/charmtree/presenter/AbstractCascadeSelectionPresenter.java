package net.sf.anathema.charmtree.presenter;

import java.awt.Dimension;

import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupChangeListener;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class AbstractCascadeSelectionPresenter implements ICascadeSelectionPresenter {

  public static final IIdentificate MARTIAL_ARTS = new Identificate("MartialArts"); //$NON-NLS-1$
  private final ITemplateRegistry registry;
  private final IResources resources;

  public AbstractCascadeSelectionPresenter(IResources resources, ITemplateRegistry registry) {
    this.resources = resources;
    this.registry = registry;
  }

  protected IResources getResources() {
    return resources;
  }

  protected void createCharmGroupSelector(
      ICascadeSelectionView selectionView,
      ICharmGroupChangeListener charmSelectionChangeListener,
      ICharmGroup[] allGroups) {
    IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer("", getResources()); //$NON-NLS-1$
    Dimension preferredSize = GuiUtilities.calculateComboBoxSize(allGroups, renderer);
    selectionView.addCharmGroupSelector(getResources().getString("CardView.CharmConfiguration.AlienCharms.CharmGroup"), //$NON-NLS-1$
        renderer,
        charmSelectionChangeListener,
        preferredSize);
  }

  protected void createCharmTypeSelector(
      IIdentificate[] types,
      ICascadeSelectionView selectionView,
      String titleResourceKey) {
    selectionView.addCharmTypeSelector(
        getResources().getString(titleResourceKey),
        types,
        new IdentificateSelectCellRenderer("", getResources())); //$NON-NLS-1$
  }

  protected ICharmGroup[] sortCharmGroups(ICharmGroup[] originalGroups) {
    return new I18nedIdentificateSorter<ICharmGroup>().sortAscending(
        originalGroups,
        new ICharmGroup[originalGroups.length],
        resources);
  }

  protected final ITemplateRegistry getTemplateRegistry() {
    return registry;
  }
}
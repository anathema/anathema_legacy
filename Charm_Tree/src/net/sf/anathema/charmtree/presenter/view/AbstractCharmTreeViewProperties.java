package net.sf.anathema.charmtree.presenter.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.CharmInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractCharmTreeViewProperties implements ICharmTreeViewProperties {

  private final ICharmInfoStringBuilder tooltipTextProvider;

  public AbstractCharmTreeViewProperties(Resources resources, MagicDescriptionProvider magicDescriptionProvider) {
    this.tooltipTextProvider = new CharmInfoStringBuilder(resources, magicDescriptionProvider);
  }

  @Override
  public final boolean isRequirementNode(final String nodeId) {
    return nodeId.startsWith(REQUIREMENT);
  }

  @Override
  public final String getToolTip(String charmId) {
    if (isRequirementNode(charmId)) {
      return null;
    }
    ICharm charm = findNonNullCharm(charmId);
    ISpecialCharm specialCharm = getSpecialCharm(charmId);
    return tooltipTextProvider.getInfoString(charm, specialCharm);
  }

  private ICharm findNonNullCharm(final String charmId) {
    ICharm charm = getCharmById(charmId);
    Preconditions.checkNotNull(charm, "Charm with id '" + charmId + "' not found."); //$NON-NLS-1$ //$NON-NLS-2$
    return charm;
  }

  protected abstract ICharm getCharmById(String id);

  protected abstract ISpecialCharm getSpecialCharm(String id);
}
package net.sf.anathema.character.main.magic.charmtree.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder.CharmInfoStringBuilder;
import net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder.ICharmInfoStringBuilder;
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
    Preconditions.checkNotNull(charm, "Charm with id '" + charmId + "' not found.");
    return charm;
  }

  protected abstract ICharm getCharmById(String id);

  protected abstract ISpecialCharm getSpecialCharm(String id);
}
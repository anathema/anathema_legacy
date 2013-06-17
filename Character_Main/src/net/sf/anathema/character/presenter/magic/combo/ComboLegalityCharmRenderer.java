package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.charmtree.builder.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

public class ComboLegalityCharmRenderer extends LegalityCheckListCellRenderer {

  private final ICharmInfoStringBuilder charmInfoStringProvider;
  private final MagicDisplayLabeler labeler;

  public ComboLegalityCharmRenderer(Resources resource, ICharmInfoStringBuilder charmInfoStringBuilder, final IComboConfiguration comboConfiguration) {
    super(new ComboLegality(comboConfiguration));
    this.charmInfoStringProvider = charmInfoStringBuilder;
    this.labeler = new MagicDisplayLabeler(resource);
  }

  @Override
  protected String getPrintName(Object value) {
    return labeler.getLabelForMagic((IMagic) value);
  }

  @Override
  protected String getToolTip(Object value) {
    return charmInfoStringProvider.getInfoString((ICharm) value, null);
  }
}
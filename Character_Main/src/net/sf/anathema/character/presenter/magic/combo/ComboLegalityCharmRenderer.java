package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.charmtree.builder.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JComponent;
import javax.swing.JList;
import java.awt.Component;

public class ComboLegalityCharmRenderer extends LegalityCheckListCellRenderer {

  private final IComboConfiguration comboConfiguration;
  private final ICharmInfoStringBuilder charmInfoStringProvider;
  private final MagicDisplayLabeler labeler;

  public ComboLegalityCharmRenderer(Resources resource, ICharmInfoStringBuilder charmInfoStringBuilder, IComboConfiguration comboConfiguration) {
    super(resource);
    this.charmInfoStringProvider = charmInfoStringBuilder;
    this.labeler = new MagicDisplayLabeler(resource);
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  protected boolean isLegal(Object object) {
    return comboConfiguration.isComboLegal((ICharm) object);
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    JComponent renderComponent = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    String tooltipString = charmInfoStringProvider.getInfoString((ICharm) value, null);
    renderComponent.setToolTipText(tooltipString);
    return renderComponent;
  }

  @Override
  protected String getPrintName(Resources resources, Object value) {
    return labeler.getLabelForMagic((IMagic) value);
  }
}
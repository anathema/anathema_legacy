package net.sf.anathema.character.presenter.magic.filter;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmtree.filters.CharmFilter;
import net.sf.anathema.charmtree.filters.EssenceLevelCharmFilterPage;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.resources.Resources;
import org.dom4j.Element;

import javax.swing.JPanel;

import static net.sf.anathema.lib.xml.ElementUtilities.addAttribute;
import static net.sf.anathema.lib.xml.ElementUtilities.getIntAttrib;

public class EssenceLevelCharmFilter implements CharmFilter {
  private final EssenceLevel essenceLevel = new EssenceLevel(5);
  private final EssenceLevel workingEssenceLevel = new EssenceLevel(0);
  private final Enablement enabled = new Enablement();
  private final Enablement workingEnabled = new Enablement();

  private static final String TAG_FILTERNAME = "EssenceFilter";
  private static final String ATTRIB_ENABLED = "enabled";
  private static final String ATTRIB_ESSENCE = "essence";

  @Override
  public boolean acceptsCharm(ICharm charm, boolean isAncestor) {
    return !enabled.isEnabled() || essenceLevel.isGreaterOrEqualThan(charm.getEssence());
  }

  @Override
  public boolean isDirty() {
    return !enabled.isEqualTo(workingEnabled) || !essenceLevel.isEqualTo(workingEssenceLevel);
  }

  @Override
  public void apply() {
    enabled.setValueTo(workingEnabled);
    essenceLevel.setValueTo(workingEssenceLevel);
  }

  @Override
  public void reset() {
    copyCurrentEnabledStateToWorkingEnabledState();
    copyCurrentValueToWorkingEssenceLevel();
  }

  @Override
  public void save(Element parent) {
    if (enabled.isEnabled()) {
      Element sourceElement = parent.addElement(TAG_FILTERNAME);
      sourceElement.addAttribute(ATTRIB_ENABLED, "true");
      addAttribute(sourceElement, ATTRIB_ESSENCE, essenceLevel.getValue());
    }
  }

  @Override
  public boolean load(Element node) {
    if (node.getName().equals(TAG_FILTERNAME)) {
      if (node.attribute(ATTRIB_ENABLED).getValue().equals("true")) {
        enabled.enable();
      }
      try {
        essenceLevel.setValueTo(getIntAttrib(node, ATTRIB_ESSENCE, 5));
      } catch (PersistenceException e) {
        e.printStackTrace();
      }
      return true;
    }
    return false;
  }

  @Override
  public JPanel getFilterPreferencePanel(Resources resources) {
    copyCurrentEnabledStateToWorkingEnabledState();
    copyCurrentValueToWorkingEssenceLevel();
    return new EssenceLevelCharmFilterPage(resources, workingEnabled, workingEssenceLevel).getContent();
  }

  private void copyCurrentEnabledStateToWorkingEnabledState() {
    workingEnabled.setValueTo(enabled);
  }

  private void copyCurrentValueToWorkingEssenceLevel() {
    workingEssenceLevel.setValueTo(essenceLevel);
  }
}
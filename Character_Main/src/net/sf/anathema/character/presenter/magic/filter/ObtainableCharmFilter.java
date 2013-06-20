package net.sf.anathema.character.presenter.magic.filter;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.charmtree.filters.CharmFilter;
import net.sf.anathema.charmtree.filters.ObtainableCharmFilterPage;
import net.sf.anathema.lib.resources.Resources;
import org.dom4j.Element;

import javax.swing.JPanel;

public class ObtainableCharmFilter implements CharmFilter {
  private final CharmsModel characterSet;
  private boolean enabled;

  boolean[] workingEnabled = new boolean[1];

  static final String TAG_FILTERNAME = "ObtainFilter";
  static final String ATTRIB_ENABLED = "enabled";

  public ObtainableCharmFilter(CharmsModel characterSet) {
    this(characterSet, false);
  }

  public ObtainableCharmFilter(CharmsModel characterSet, boolean enabled) {
    this.characterSet = characterSet;
    this.enabled = enabled;
  }

  @Override
  public boolean acceptsCharm(ICharm charm, boolean isAncestor) {
    if (!enabled) {
      return true;
    }

    for (ICharm learnedCharm : characterSet.getLearnedCharms(true)) {
      if (learnedCharm == charm) {
        return true;
      }
    }

    return characterSet.isLearnable(charm);
  }

  @Override
  public void apply() {
    enabled = workingEnabled[0];
  }

  @Override
  public JPanel getFilterPreferencePanel(Resources resources) {
    workingEnabled[0] = enabled;
    return new ObtainableCharmFilterPage(resources, workingEnabled).getContent();
  }

  @Override
  public boolean isDirty() {
    return enabled != workingEnabled[0];
  }

  @Override
  public void reset() {
    workingEnabled[0] = enabled;
  }

  @Override
  public void save(Element parent) {
    if (enabled) {
      Element sourceElement = parent.addElement(TAG_FILTERNAME);
      sourceElement.addAttribute(ATTRIB_ENABLED, "true");
    }
  }

  @Override
  public boolean load(Element node) {
    if (node.getName().equals(TAG_FILTERNAME)) {
      if (node.attribute(ATTRIB_ENABLED).getValue().equals("true")) {
        enabled = true;
      }
      return true;
    }
    return false;
  }

}

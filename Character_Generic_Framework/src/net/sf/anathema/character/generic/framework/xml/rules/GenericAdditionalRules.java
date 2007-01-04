package net.sf.anathema.character.generic.framework.xml.rules;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericAdditionalRules extends NullAdditionalRules implements ICloneable<GenericAdditionalRules> {

  private String[] compulsiveCharmIds = new String[0];
  private String[] rejectedBackgroundIds = new String[0];
  private IAdditionalEssencePool[] pools = new IAdditionalEssencePool[0];

  public void setCompulsiveCharmIds(String[] compulsiveCharmIds) {
    this.compulsiveCharmIds = compulsiveCharmIds;
  }

  @Override
  public String[] getCompulsiveCharmIDs() {
    return compulsiveCharmIds;
  }

  public void setAdditionalEssencePools(IAdditionalEssencePool[] pools) {
    this.pools = pools;
  }

  @Override
  public IAdditionalEssencePool[] getAdditionalEssencePools() {
    return pools;
  }

  @Override
  public boolean isRejected(IBackgroundTemplate backgroundTemplate) {
    return ArrayUtilities.contains(rejectedBackgroundIds, backgroundTemplate.getId());
  }

  public void setRejectedBackgrounds(String[] backgroundIds) {
    this.rejectedBackgroundIds = backgroundIds;
  }

  @Override
  public GenericAdditionalRules clone() {
    return new GenericAdditionalRules();
  }

}
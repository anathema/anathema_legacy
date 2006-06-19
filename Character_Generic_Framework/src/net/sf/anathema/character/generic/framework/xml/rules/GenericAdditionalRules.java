package net.sf.anathema.character.generic.framework.xml.rules;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericAdditionalRules extends NullAdditionalRules implements IAdditionalRules, ICloneable<GenericAdditionalRules> {

  private String[] compulsiveCharmIds = new String[0];
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
  public GenericAdditionalRules clone() {
    return new GenericAdditionalRules();
  }

}
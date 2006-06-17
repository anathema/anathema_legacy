package net.sf.anathema.character.generic.impl.traits.caste;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.traits.AbstractTraitTemplate;
import net.sf.anathema.character.generic.impl.traits.limitation.EssenceBasedLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;

public class CasteRangeTraitTemplate extends AbstractTraitTemplate {

  private final ITraitTemplate defaultTemplate;
  private final ICasteTraitMinimum[] casteMinimums;
  private final ITraitLimitation limitation = new EssenceBasedLimitation();

  public CasteRangeTraitTemplate(ITraitTemplate defaultTemplate, ICasteTraitMinimum[] casteRanges) {
    super(defaultTemplate);
    this.defaultTemplate = defaultTemplate;
    this.casteMinimums = casteRanges;
  }

  public int getMinimumValue(ILimitationContext limiationContext) {
    for (ICasteTraitMinimum casteMinimum : casteMinimums) {
      ICasteType< ? extends ICasteTypeVisitor> casteType = limiationContext.getCasteType();
      if (ObjectUtilities.equals(casteType, casteMinimum.getCaste())) {
        return casteMinimum.getMinimumValue(limiationContext);
      }
    }
    return defaultTemplate.getMinimumValue(limiationContext);
  }

  public ITraitLimitation getLimitation() {
    return limitation;
  }
}
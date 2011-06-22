package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.lib.util.IIdentificate;

public enum FavoringTraitType implements IIdentificate {
  AbilityType() {
    @Override
    public void accept(IFavoringTraitTypeVisitor visitor) {
      visitor.visitAbilityType(this);
    }

    @Override
    public AbilityType[] getTraitTypes(IExaltedEdition edition) {
      return net.sf.anathema.character.generic.traits.types.AbilityType.getAbilityTypes(edition);
    }
  },
  AttributeType() {
    @Override
    public void accept(IFavoringTraitTypeVisitor visitor) {
      visitor.visitAttributeType(this);
    }

    @Override
    public AttributeType[] getTraitTypes(IExaltedEdition edition) {
      return net.sf.anathema.character.generic.traits.types.AttributeType.values();
    }
  },
  VirtueType() {
        @Override
        public void accept(IFavoringTraitTypeVisitor visitor) {
          visitor.visitVirtueType(this);
        }

        @Override
        public VirtueType[] getTraitTypes(IExaltedEdition edition) {
          return net.sf.anathema.character.generic.traits.types.VirtueType.values();
        }
  },
  YoziType() {
	    @Override
	    public void accept(IFavoringTraitTypeVisitor visitor) {
	      visitor.visitYoziType(this);
	    }

	    @Override
	    public YoziType[] getTraitTypes(IExaltedEdition edition) {
	      return net.sf.anathema.character.generic.traits.types.YoziType.values();
	    }
	  };

  public String getId() {
    return name();
  }

  public abstract void accept(IFavoringTraitTypeVisitor visitor);

  public abstract ITraitType[] getTraitTypes(IExaltedEdition edition);
}
package net.sf.anathema.character.lunar.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum LunarCaste implements ICasteType<ILunarCasteVisitor> {

  FullMoon {
    public void accept(ILunarCasteVisitor visitor) {
      visitor.visitFullMoon(this);
    }
  },
  ChangingMoon {
    public void accept(ILunarCasteVisitor visitor) {
      visitor.visitChangingMoon(this);
    }
  },
  NoMoon {
    public void accept(ILunarCasteVisitor visitor) {
      visitor.visitNoMoon(this);
    }
  };

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}
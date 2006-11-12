package net.sf.anathema.character.solar.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum SolarCaste implements ICasteType<ISolarCasteVisitor> {

  Dawn {
    public void accept(ISolarCasteVisitor visitor) {
      visitor.visitDawn(this);
    }
  },
  Zenith {
    public void accept(ISolarCasteVisitor visitor) {
      visitor.visitZenith(this);
    }
  },
  Twilight {
    public void accept(ISolarCasteVisitor visitor) {
      visitor.visitTwilight(this);
    }
  },
  Night {
    public void accept(ISolarCasteVisitor visitor) {
      visitor.visitNight(this);
    }
  },
  Eclipse {
    public void accept(ISolarCasteVisitor visitor) {
      visitor.visitEclipse(this);
    }
  };

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
}
package net.sf.anathema.character.magic.spells;

import net.sf.anathema.lib.util.Identifier;

public enum CircleType implements Identifier {

  Terrestrial {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitTerrestrial(this);
    }

    @Override
    public boolean isSorceryCircle() {
      return true;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return false;
    }
  },
  Celestial {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitCelestial(this);
    }

    @Override
    public boolean isSorceryCircle() {
      return true;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return false;
    }
  },
  Solar {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitSolar(this);
    }

    @Override
    public boolean isSorceryCircle() {
      return true;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return false;
    }
  },
  Shadowlands {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitShadowland(this);
    }

    @Override
    public boolean isSorceryCircle() {
      return false;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return true;
    }
  },
  Labyrinth {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitLabyrinth(this);
    }

    @Override
    public boolean isSorceryCircle() {
      return false;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return true;
    }
  },
  Void {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitVoid(this);
    }

    @Override
    public boolean isSorceryCircle() {
      return false;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return true;
    }
  };

  @Override
  public String getId() {
    return name();
  }

  public abstract void accept(ICircleTypeVisitor visitor);

  public abstract boolean isSorceryCircle();
  
  @SuppressWarnings("UnusedDeclaration")
  public abstract boolean isNecromancyCircle();
}
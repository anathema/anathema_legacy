package net.sf.anathema.lib.gui.image;

public class LibGuiImageProvider extends ImageProvider {

  private static final LibGuiImageProvider instance = new LibGuiImageProvider();

  public static LibGuiImageProvider getInstance() {
    return instance;
  }

  private LibGuiImageProvider() {
    super("net/sf/anathema/lib/gui/icons"); //$NON-NLS-1$
  }

}
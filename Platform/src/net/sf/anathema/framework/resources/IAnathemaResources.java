package net.sf.anathema.framework.resources;

import java.util.Locale;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public interface IAnathemaResources extends IResources {

  public void addStringResourceHandler(IStringResourceHandler handler);

  public String getDefaultFrameTitle();

  public Locale getLocale();

}
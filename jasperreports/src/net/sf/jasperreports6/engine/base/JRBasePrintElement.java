/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2023 Cloud Software Group, Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports6.engine.base;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.UUID;

import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JRDefaultStyleProvider;
import net.sf.jasperreports6.engine.JROrigin;
import net.sf.jasperreports6.engine.JRPrintElement;
import net.sf.jasperreports6.engine.JRPropertiesHolder;
import net.sf.jasperreports6.engine.JRPropertiesMap;
import net.sf.jasperreports6.engine.JRStyle;
import net.sf.jasperreports6.engine.PrintElementVisitor;
import net.sf.jasperreports6.engine.type.ModeEnum;
import net.sf.jasperreports6.engine.util.StyleResolver;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRBasePrintElement implements JRPrintElement, Serializable
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected UUID uuid;
	protected JROrigin origin;
	protected String key;
	
	/**
	 *
	 */
	protected ModeEnum modeValue;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Color forecolor;
	protected Color backcolor;

	protected JRDefaultStyleProvider defaultStyleProvider;
	protected JRStyle style;
	
	private JRPropertiesMap propertiesMap;
	private int sourceElementId;
	private int printElementId;

	
	/**
	 *
	 */
	public JRBasePrintElement(JRDefaultStyleProvider defaultStyleProvider)
	{
		this.defaultStyleProvider = defaultStyleProvider;
	}
	

	@Override
	public JRDefaultStyleProvider getDefaultStyleProvider()
	{
		return defaultStyleProvider;
	}

	/**
	 *
	 */
	protected StyleResolver getStyleResolver() 
	{
		if (getDefaultStyleProvider() != null)
		{
			return getDefaultStyleProvider().getStyleResolver();
		}
		return StyleResolver.getInstance();
	}

	@Override
	public UUID getUUID()
	{
		return uuid;
	}

	@Override
	public void setUUID(UUID uuid)
	{
		this.uuid = uuid;
	}

	@Override
	public JROrigin getOrigin()
	{
		return origin;
	}
	
	/**
	 *
	 */
	public void setOrigin(JROrigin origin)
	{
		this.origin = origin;
	}

	@Override
	public JRStyle getStyle()
	{
		return style;
	}
	
	@Override
	public void setStyle(JRStyle style)
	{
		this.style = style;
	}

	@Override
	public ModeEnum getModeValue()
	{
		return getStyleResolver().getMode(this, ModeEnum.OPAQUE);
	}

	@Override
	public ModeEnum getOwnModeValue()
	{
		return modeValue;
	}

	@Override
	public void setMode(ModeEnum modeValue)
	{
		this.modeValue = modeValue;
	}

	@Override
	public int getX()
	{
		return this.x;
	}
	
	@Override
	public void setX(int x)
	{
		this.x = x;
	}
	
	@Override
	public int getY()
	{
		return this.y;
	}
	
	@Override
	public void setY(int y)
	{
		this.y = y;
	}
	
	@Override
	public int getWidth()
	{
		return this.width;
	}
	
	@Override
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	@Override
	public int getHeight()
	{
		return this.height;
	}
	
	@Override
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	@Override
	public Color getForecolor()
	{
		return getStyleResolver().getForecolor(this);
	}
	
	@Override
	public Color getOwnForecolor()
	{
		return forecolor;
	}
	
	@Override
	public void setForecolor(Color forecolor)
	{
		this.forecolor = forecolor;
	}
	
	@Override
	public Color getBackcolor()
	{
		return getStyleResolver().getBackcolor(this);
	}

	@Override
	public Color getOwnBackcolor()
	{
		return backcolor;
	}

	@Override
	public void setBackcolor(Color backcolor)
	{
		this.backcolor = backcolor;
	}


	@Override
	public String getKey()
	{
		return key;
	}


	/**
	 * Sets the print element key.
	 * 
	 * @param key the element key
	 * @see #getKey()
	 */
	public void setKey(String key)
	{
		this.key = key;
	}


	/**
	 * Returns null as external style references are not allowed for print objects.
	 */
	@Override
	public String getStyleNameReference()
	{
		return null;
	}
	

	@Override
	public synchronized boolean hasProperties()
	{
		return propertiesMap != null && propertiesMap.hasProperties();
	}

	@Override
	public synchronized JRPropertiesMap getPropertiesMap()
	{
		if (propertiesMap == null)
		{
			propertiesMap = new JRPropertiesMap();
		}
		return propertiesMap;
	}

	@Override
	public JRPropertiesHolder getParentProperties()
	{
		return null;
	}

	
	/*
	 * These fields are only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
	/**
	 * @deprecated
	 */
	private Byte mode;
	
	@SuppressWarnings("deprecation")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		
		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
		{
			modeValue = ModeEnum.getByValue(mode);
			
			mode = null;
		}
	}


	@Override
	public <T> void accept(PrintElementVisitor<T> visitor, T arg)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getSourceElementId()
	{
		return sourceElementId;
	}

	/**
	 * Sets the source/fill element Id for the print element.
	 * 
	 * @param sourceElementId
	 * @see #getSourceElementId()
	 */
	public void setSourceElementId(int sourceElementId)
	{
		this.sourceElementId = sourceElementId;
	}

	@Override
	public int getPrintElementId()
	{
		return printElementId;
	}

	/**
	 * Sets the print Id for this element.
	 * 
	 * @param printElementId the print Id
	 * @see #getPrintElementId()
	 */
	public void setPrintElementId(int printElementId)
	{
		this.printElementId = printElementId;
	}

}

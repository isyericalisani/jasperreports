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
package net.sf.jasperreports6.engine.design;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;

import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JRDefaultStyleProvider;
import net.sf.jasperreports6.engine.JRGraphicElement;
import net.sf.jasperreports6.engine.JRPen;
import net.sf.jasperreports6.engine.base.JRBasePen;
import net.sf.jasperreports6.engine.base.JRBaseStyle;
import net.sf.jasperreports6.engine.type.FillEnum;


/**
 * This class contains functionality common to graphic elements at design time. It provides implementation for the methods described
 * in <tt>JRTextElement</tt>.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public abstract class JRDesignGraphicElement extends JRDesignElement implements JRGraphicElement
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	protected JRPen linePen;
	protected FillEnum fillValue;


	/**
	 *
	 */
	protected JRDesignGraphicElement(JRDefaultStyleProvider defaultStyleProvider)
	{
		super(defaultStyleProvider);
		
		linePen = new JRBasePen(this);
	}
		

	@Override
	public JRPen getLinePen()
	{
		return linePen;
	}


	@Override
	public FillEnum getFillValue()
	{
		return getStyleResolver().getFillValue(this);
	}

	@Override
	public FillEnum getOwnFillValue()
	{
		return this.fillValue;
	}
	
	@Override
	public void setFill(FillEnum fillValue)
	{
		FillEnum old = this.fillValue;
		this.fillValue = fillValue;
		getEventSupport().firePropertyChange(JRBaseStyle.PROPERTY_FILL, old, this.fillValue);
	}

	@Override
	public Float getDefaultLineWidth() 
	{
		return JRPen.LINE_WIDTH_1;
	}

	@Override
	public Color getDefaultLineColor() 
	{
		return getForecolor();
	}

	
	@Override
	public Object clone() 
	{
		JRDesignGraphicElement clone = (JRDesignGraphicElement)super.clone();
		
		clone.linePen = linePen.clone(clone);
		
		return clone;
	}


	/*
	 * These fields are only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2; //NOPMD
	/**
	 * @deprecated
	 */
	private Byte fill;
	
	@SuppressWarnings("deprecation")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		
		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
		{
			fillValue = FillEnum.getByValue(fill);
			fill = null;
		}
	}
		
}

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

import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JRDatasetRun;
import net.sf.jasperreports6.engine.JRElementDataset;
import net.sf.jasperreports6.engine.JRExpression;
import net.sf.jasperreports6.engine.JRGroup;
import net.sf.jasperreports6.engine.base.JRBaseElementDataset;
import net.sf.jasperreports6.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports6.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports6.engine.design.events.JRPropertyChangeSupport;
import net.sf.jasperreports6.engine.type.DatasetResetTypeEnum;
import net.sf.jasperreports6.engine.type.IncrementTypeEnum;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRDesignElementDataset extends JRBaseElementDataset implements JRChangeEventsSupport
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_DATASET_RUN = "datasetRun";
	
	public static final String PROPERTY_INCREMENT_GROUP = "incrementGroup";
	
	public static final String PROPERTY_INCREMENT_TYPE = "incrementType";
	
	public static final String PROPERTY_INCREMENT_WHEN_EXPRESSION = "incrementWhenExpression";
	
	public static final String PROPERTY_RESET_GROUP = "resetGroup";
	
	public static final String PROPERTY_RESET_TYPE = "resetType";
	
	public static final String PROPERTY_DATASET_RESET_TYPE = "datasetResetType";

	
	public JRDesignElementDataset()
	{
		super();
	}

	/**
	 *
	 */
	public JRDesignElementDataset(JRElementDataset dataset)
	{
		super(dataset);
	}


	/**
	 *
	 */
	public JRDesignElementDataset(JRElementDataset dataset, JRBaseObjectFactory factory)
	{
		super(dataset, factory);
	}


	/**
	 *
	 */
	public void setResetType(DatasetResetTypeEnum datasetResetTypeValue)
	{
		Object old = this.datasetResetType;
		this.datasetResetType = datasetResetTypeValue;
		getEventSupport().firePropertyChange(PROPERTY_DATASET_RESET_TYPE, old, this.datasetResetType);
	}
		
	/**
	 *
	 */
	public void setIncrementType(IncrementTypeEnum incrementTypeValue)
	{
		Object old = this.incrementTypeValue;
		this.incrementTypeValue = incrementTypeValue;
		getEventSupport().firePropertyChange(PROPERTY_INCREMENT_TYPE, old, this.incrementTypeValue);
	}
		
	/**
	 *
	 */
	public void setResetGroup(JRGroup group)
	{
		Object old = this.resetGroup;
		this.resetGroup = group;
		getEventSupport().firePropertyChange(PROPERTY_RESET_GROUP, old, this.resetGroup);
	}
		
	/**
	 *
	 */
	public void setIncrementGroup(JRGroup group)
	{
		Object old = this.incrementGroup;
		this.incrementGroup = group;
		getEventSupport().firePropertyChange(PROPERTY_INCREMENT_GROUP, old, this.incrementGroup);
	}
	
	
	/**
	 * Sets the sub dataset run for this dataset.
	 * 
	 * @param datasetRun the dataset run
	 * @see JRElementDataset#getDatasetRun()
	 */
	public void setDatasetRun(JRDatasetRun datasetRun)
	{
		Object old = this.datasetRun;
		this.datasetRun = datasetRun;
		getEventSupport().firePropertyChange(PROPERTY_DATASET_RUN, old, this.datasetRun);
	}
	
	
	/**
	 * Sets the "increment when" expression.
	 * <p>
	 * The expression value class should be compatible with <code>java.lang.Boolean</code>
	 * 
	 * @param expression the expression
	 * @see JRElementDataset#getIncrementWhenExpression()
	 */
	public void setIncrementWhenExpression(JRExpression expression)
	{
		Object old = this.incrementWhenExpression;
		this.incrementWhenExpression = expression;
		getEventSupport().firePropertyChange(PROPERTY_INCREMENT_WHEN_EXPRESSION, old, this.incrementWhenExpression);
	}
	
	@Override
	public Object clone()
	{
		JRDesignElementDataset clone = (JRDesignElementDataset)super.clone();
		clone.eventSupport = null;
		return clone;
	}
	
	private transient JRPropertyChangeSupport eventSupport;
	
	@Override
	public JRPropertyChangeSupport getEventSupport()
	{
		synchronized (this)
		{
			if (eventSupport == null)
			{
				eventSupport = new JRPropertyChangeSupport(this);
			}
		}
		
		return eventSupport;
	}
}

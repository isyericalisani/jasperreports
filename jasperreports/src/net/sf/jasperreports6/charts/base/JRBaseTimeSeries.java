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
package net.sf.jasperreports6.charts.base;

import java.io.Serializable;

import net.sf.jasperreports6.charts.JRTimeSeries;
import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JRExpression;
import net.sf.jasperreports6.engine.JRHyperlink;
import net.sf.jasperreports6.engine.JRRuntimeException;
import net.sf.jasperreports6.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports6.engine.util.JRCloneUtils;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRBaseTimeSeries implements JRTimeSeries, Serializable
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected JRExpression seriesExpression;
	protected JRExpression timePeriodExpression;
	protected JRExpression valueExpression;
	protected JRExpression labelExpression;
	protected JRHyperlink itemHyperlink;

	
	/**
	 *
	 */
	protected JRBaseTimeSeries()
	{
	}
	
	
	/**
	 *
	 */
	public JRBaseTimeSeries(JRTimeSeries timeSeries, JRBaseObjectFactory factory)
	{
		factory.put(timeSeries, this);

		seriesExpression = factory.getExpression(timeSeries.getSeriesExpression());
		timePeriodExpression = factory.getExpression(timeSeries.getTimePeriodExpression());
		valueExpression = factory.getExpression(timeSeries.getValueExpression());
		labelExpression = factory.getExpression(timeSeries.getLabelExpression());
		itemHyperlink = factory.getHyperlink(timeSeries.getItemHyperlink());
	}

	
	@Override
	public JRExpression getSeriesExpression()
	{
		return seriesExpression;
	}
		
	@Override
	public JRExpression getTimePeriodExpression()
	{
		return timePeriodExpression;
	}
		
	@Override
	public JRExpression getValueExpression()
	{
		return valueExpression;
	}
		
	@Override
	public JRExpression getLabelExpression()
	{
		return labelExpression;
	}

	
	@Override
	public JRHyperlink getItemHyperlink()
	{
		return itemHyperlink;
	}
		
	@Override
	public Object clone() 
	{
		JRBaseTimeSeries clone = null;
		
		try
		{
			clone = (JRBaseTimeSeries)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}

		clone.seriesExpression = JRCloneUtils.nullSafeClone(seriesExpression);
		clone.timePeriodExpression = JRCloneUtils.nullSafeClone(timePeriodExpression);
		clone.valueExpression = JRCloneUtils.nullSafeClone(valueExpression);
		clone.labelExpression = JRCloneUtils.nullSafeClone(labelExpression);
		clone.itemHyperlink = JRCloneUtils.nullSafeClone(itemHyperlink);
		
		return clone;
	}
}

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

import net.sf.jasperreports6.charts.JRXyzSeries;
import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JRExpression;
import net.sf.jasperreports6.engine.JRHyperlink;
import net.sf.jasperreports6.engine.JRRuntimeException;
import net.sf.jasperreports6.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports6.engine.util.JRCloneUtils;

/**
 * @author Flavius Sana (flavius_sana@users.sourceforge.net)
 */
public class JRBaseXyzSeries implements JRXyzSeries, Serializable {

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	protected JRExpression seriesExpression;
	protected JRExpression xValueExpression;
	protected JRExpression yValueExpression;
	protected JRExpression zValueExpression;
	protected JRHyperlink itemHyperlink;
	
	public JRBaseXyzSeries(){
	}
	
	public JRBaseXyzSeries( JRXyzSeries xyzSeries, JRBaseObjectFactory factory ){
		factory.put( xyzSeries, this );
		
		seriesExpression = factory.getExpression( xyzSeries.getSeriesExpression() );
		xValueExpression = factory.getExpression( xyzSeries.getXValueExpression() );
		yValueExpression = factory.getExpression( xyzSeries.getYValueExpression() );
		zValueExpression = factory.getExpression( xyzSeries.getZValueExpression() );
		itemHyperlink = factory.getHyperlink(xyzSeries.getItemHyperlink());
	}
	
	
	@Override
	public JRExpression getSeriesExpression(){
		return seriesExpression;
	}
	
	@Override
	public JRExpression getXValueExpression(){
		return xValueExpression;
	}
	
	@Override
	public JRExpression getYValueExpression(){
		return yValueExpression;
	}
	
	@Override
	public JRExpression getZValueExpression(){
		return zValueExpression;
	}
	
	@Override
	public JRHyperlink getItemHyperlink()
	{
		return itemHyperlink;
	}
	
	@Override
	public Object clone() 
	{
		JRBaseXyzSeries clone = null;
		
		try
		{
			clone = (JRBaseXyzSeries)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}

		clone.seriesExpression = JRCloneUtils.nullSafeClone(seriesExpression);
		clone.xValueExpression = JRCloneUtils.nullSafeClone(xValueExpression);
		clone.yValueExpression = JRCloneUtils.nullSafeClone(yValueExpression);
		clone.zValueExpression = JRCloneUtils.nullSafeClone(zValueExpression);
		clone.itemHyperlink = JRCloneUtils.nullSafeClone(itemHyperlink);
		
		return clone;
	}
}

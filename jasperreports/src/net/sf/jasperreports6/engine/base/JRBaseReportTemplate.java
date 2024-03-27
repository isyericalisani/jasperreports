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

import java.io.Serializable;

import net.sf.jasperreports6.engine.JRAbstractObjectFactory;
import net.sf.jasperreports6.engine.JRCloneable;
import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JRExpression;
import net.sf.jasperreports6.engine.JRReportTemplate;
import net.sf.jasperreports6.engine.JRRuntimeException;
import net.sf.jasperreports6.engine.util.JRCloneUtils;


/**
 * Base read-only implementation of {@link JRReportTemplate JRReportTemplate}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class JRBaseReportTemplate implements JRReportTemplate, Serializable, JRCloneable
{
	
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected JRExpression sourceExpression;

	protected JRBaseReportTemplate()
	{
	}

	public JRBaseReportTemplate(JRReportTemplate reportTemplate, JRAbstractObjectFactory factory)
	{
		factory.put(reportTemplate, this);
		
		sourceExpression = factory.getExpression(reportTemplate.getSourceExpression());
	}
	
	@Override
	public JRExpression getSourceExpression()
	{
		return sourceExpression;
	}

	@Override
	public Object clone()
	{
		JRBaseReportTemplate clone = null;
		
		try
		{
			clone = (JRBaseReportTemplate)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}
		
		clone.sourceExpression = JRCloneUtils.nullSafeClone(sourceExpression);
		
		return clone;
	}
}

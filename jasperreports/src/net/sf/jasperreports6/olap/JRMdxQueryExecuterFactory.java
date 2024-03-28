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
package net.sf.jasperreports6.olap;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports6.engine.JRDataset;
import net.sf.jasperreports6.engine.JRException;
import net.sf.jasperreports6.engine.JRValueParameter;
import net.sf.jasperreports6.engine.JasperReportsContext;
import net.sf.jasperreports6.engine.query.AbstractQueryExecuterFactory;
import net.sf.jasperreports6.engine.query.JREmptyQueryExecuter;
import net.sf.jasperreports6.engine.query.JRQueryExecuter;
import net.sf.jasperreports6.engine.util.Designated;
import net.sf.jasperreports6.olap.xmla.JRXmlaQueryExecuter;
import net.sf.jasperreports6.olap.xmla.JRXmlaQueryExecuterFactory;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class JRMdxQueryExecuterFactory extends AbstractQueryExecuterFactory implements Designated
{
	
	public static final String QUERY_EXECUTER_NAME = "net.sf.jasperreports6.query.executer:MDX";
	
	private static final Log log = LogFactory.getLog(JRMdxQueryExecuterFactory.class);
	
	private final static Object[] MDX_BUILTIN_PARAMETERS;
	
	public static final String CANONICAL_LANGUAGE = "MDX";
	
	static
	{
		Object[] mondrianParams = new JRMondrianQueryExecuterFactory().getBuiltinParameters();
		Object[] xmlaParams = new JRXmlaQueryExecuterFactory().getBuiltinParameters();
		
		MDX_BUILTIN_PARAMETERS = new Object[mondrianParams.length + xmlaParams.length];
		System.arraycopy(mondrianParams, 0, MDX_BUILTIN_PARAMETERS, 0, mondrianParams.length);
		System.arraycopy(xmlaParams, 0, MDX_BUILTIN_PARAMETERS, mondrianParams.length, xmlaParams.length);
	}
	
	@Override
	public Object[] getBuiltinParameters()
	{
		return MDX_BUILTIN_PARAMETERS;
	}

	@Override
	public JRQueryExecuter createQueryExecuter(
		JasperReportsContext jasperReportsContext,
		JRDataset dataset, 
		Map<String,? extends JRValueParameter> parameters
		) throws JRException
	{
		JRQueryExecuter queryExecuter;
		if (getParameterValue(parameters, JRMondrianQueryExecuterFactory.PARAMETER_MONDRIAN_CONNECTION) != null)
		{
			queryExecuter = new JRMondrianQueryExecuter(jasperReportsContext, dataset, parameters);
		}
		else if (getParameterValue(parameters, JRXmlaQueryExecuterFactory.PARAMETER_XMLA_URL) != null)
		{
			queryExecuter = new JRXmlaQueryExecuter(jasperReportsContext, dataset, parameters);
		}
		else
		{
			log.warn("No Mondrian connection or XMLA URL set for MDX query");
			queryExecuter = new JREmptyQueryExecuter();
		}
		return queryExecuter;
	}

	protected final Object getParameterValue(Map<String,? extends JRValueParameter> valueParams, String name)
	{
		JRValueParameter valueParam = valueParams.get(name);
		return valueParam == null ? null : valueParam.getValue();
	}
	
	@Override
	public boolean supportsQueryParameterType(String className)
	{
		return true;
	}

	@Override
	public String getDesignation()
	{
		return QUERY_EXECUTER_NAME;
	}

}

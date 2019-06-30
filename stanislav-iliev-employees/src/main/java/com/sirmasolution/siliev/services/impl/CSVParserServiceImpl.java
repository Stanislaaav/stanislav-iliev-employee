package com.sirmasolution.siliev.services.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sirmasolution.siliev.model.Employee;
import com.sirmasolution.siliev.services.ParserService;


/**
 * Implementation of CSV parser
 * 
 * @author Stan
 *
 */
@Component("CSVParserServiceImpl")
public class CSVParserServiceImpl extends ParserServiceImpl implements ParserService, InitializingBean {
	
	private CsvSchema schema;
	private CsvMapper mapper;
	private ObjectReader oReader;

	public List<Employee> parseEmployeeData(File file) throws Exception {
		MappingIterator<Employee> mi = oReader.readValues(file);
		return mi.readAll();
	}

	public void afterPropertiesSet()  {
		schema = CsvSchema.builder().
				addColumn("empId").
				addColumn("projectId").
				addColumn("dateFrom").
				addColumn("dateTo")
				.build();
		mapper = new CsvMapper();
		mapper.enable(CsvParser.Feature.TRIM_SPACES);
		oReader = mapper.readerFor(Employee.class).
				with(schema.withNullValue("NULL"));
	}
}

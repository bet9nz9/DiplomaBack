package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

@ObjectType(id = 4)
public class Type extends BaseEntity {

	@Attribute(id = 11, valueType = ValueType.VALUE)
	protected String value;


}

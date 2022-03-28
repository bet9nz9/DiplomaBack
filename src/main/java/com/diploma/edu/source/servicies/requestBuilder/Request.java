package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.db.annotations.Attr;
import com.diploma.edu.source.db.annotations.Processor;
import com.diploma.edu.source.db.annotations.ValueType;

import java.util.List;

/**
 * Этот класс хранит начальный зарос, тут желательно ничего не менять
 */

public class Request {

    protected List<Attr> attributes;

    StringBuilder selectBlock = new StringBuilder("\nSELECT * FROM ( SELECT o.object_id id,\n o.name name,\n o.description description ");
    StringBuilder fromBlock = new StringBuilder("\nFROM OBJECTS o \n");
    StringBuilder whereBlock;
    StringBuilder filterBlock = new StringBuilder(") WHERE 1=1");

    public Request(Class clazz) {
        whereBlock = new StringBuilder("WHERE o.object_type_id = " + Processor.getObjtypeId(clazz) + "\n");
        attributes = Processor.getAttributes(clazz);

        for (Attr attr : attributes) {
//            if (attr.valueType.equals(ValueType.LIST_VALUE)){
//                selectBlock.append(",\n"+ attr.field.getName() + ".LIST_VALUE_ID ")
//                .append(attr.field.getName());
//            }

            if (!attr.valueType.equals(ValueType.BASE_VALUE)){
                selectBlock.append(",\n" + attr.field.getName() + ".")
                        .append(attr.valueType.getValueType() + " " + attr.field.getName());

                fromBlock.append("left join " + attr.valueType.getTable()+ " " + attr.field.getName() + "\n")
                        .append("on o.object_id = " + attr.field.getName() + ".object_id and ")
                        .append(attr.field.getName() + ".attr_id = " + attr.id + "\n");
            }
        }

        /*for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i).valueType == ValueType.BASE_VALUE
                    || attributes.get(i).valueType == ValueType.LIST_VALUE) {
                continue;
            }
            if (attributes.get(i).valueType == ValueType.DATE_VALUE) {
                selectBlock.append(",\n to_date(listagg(to_char(a" + i + "." + attributes.get(i).valueType.getValueType() +
                        ", 'yyyy-mm-dd hh24:mi:ss')), 'yyyy-mm-dd hh24:mi:ss')" +
                        " \"" + attributes.get(i).field.getName() + "\"");
            } else {
                selectBlock.append(",\n listagg(a" + i + ".")
                        .append(attributes.get(i).valueType.getValueType() + ") ")
                        .append("\"" + attributes.get(i).field.getName() + "\"");
            }

            fromBlock.append("left join " + attributes.get(i).valueType.getTable() + " a" + i + " ")
                    .append("on o.object_id = a" + i + ".object_id and a" + i + ".attr_id = " + attributes.get(i).id + " \n");
        }*/
    }

    public StringBuilder getSelectBlock() {
        return selectBlock;
    }

    public void setSelectBlock(StringBuilder selectBlock) {
        this.selectBlock = selectBlock;
    }

    public StringBuilder getFilterBlock() {
        return filterBlock;
    }

    public void setFilterBlock(StringBuilder filterBlock) {
        this.filterBlock = filterBlock;
    }

    /**
     * Возвращает готовую строку запроса
     */
    public String buildRequest() {
        return selectBlock.toString() + fromBlock.toString() + whereBlock.toString() + filterBlock.toString();
    }
}

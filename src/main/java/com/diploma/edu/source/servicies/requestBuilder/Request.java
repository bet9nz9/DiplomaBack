package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.db.annotations.Attr;
import com.diploma.edu.source.db.annotations.Processor;
import com.diploma.edu.source.db.annotations.ValueType;
import com.diploma.edu.source.model.BaseEntity;

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

    public Request(Class<? extends BaseEntity> clazz) {
        whereBlock = new StringBuilder("WHERE o.object_type_id = " + Processor.getObjtypeId(clazz) + "\n");
        attributes = Processor.getAttributes(clazz);

        for (Attr attr : attributes) {
            if (!attr.getValueType().equals(ValueType.BASE_VALUE)){
                selectBlock.append(",\n" + attr.getField().getName() + ".")
                        .append(attr.getValueType().getValueType() + " " + attr.getField().getName());

                fromBlock.append("left join " + attr.getValueType().getTable()+ " " + attr.getField().getName() + "\n")
                        .append("on o.object_id = " + attr.getField().getName() + ".object_id and ")
                        .append(attr.getField().getName() + ".attr_id = " + attr.getId() + "\n");
            }
        }
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

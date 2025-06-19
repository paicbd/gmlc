package org.mobicents.gmlc.slee.http.report;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author <a href="mailto:aferreiraguido@gmail.com"> Alejandro Ferreira Guido </a>
 */
public class ReportParameters extends HashMap<String, Object> implements Serializable {

    public ReportParameters() {
    }

    public String toJsonString(Boolean openAndCloseBrackets) {
        StringBuilder builder = new StringBuilder();

        if (openAndCloseBrackets)
            builder.append("{ ");

        for (HashMap.Entry<String, Object> element : this.entrySet()) {
            Object value = element.getValue();
            if (value instanceof Integer || value instanceof Long)
                builder.append(String.format("%s\"%s\": %d",
                    builder.length() > 2 ? ", " : "", element.getKey(), value));
            else
                builder.append(String.format("%s\"%s\": \"%s\"",
                    builder.length() > 2 ? ", " : "", element.getKey(), element.getValue().toString()));
        }

        if (openAndCloseBrackets)
            builder.append(" }");

        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[ ");

        for (HashMap.Entry<String, Object> element: this.entrySet()) {
            builder.append(String.format("%s%s = %s",
                    builder.length() > 2 ? ", " : "", element.getKey(), element.getValue().toString()));
        }

        builder.append(" ]");

        return builder.toString();
    }
}

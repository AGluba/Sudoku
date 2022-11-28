package pl.sudoku;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

abstract class SudokuFragment {
    private final List<SudokuField> fields;

    public SudokuFragment(List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {

            for (int j = i + 1; j < 9; j++) {

                if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue()) {

                    return false;
                }
            }
        }

        return true;
    }

    public List<SudokuField> getFields() {
        return fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        SudokuFragment that = (SudokuFragment) o;

        return new EqualsBuilder().append(fields, that.fields).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(fields).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("fields", fields)
                .toString();
    }
}

package pl.sudoku;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        return new EqualsBuilder().append(fields, ((SudokuFragment) o).fields).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(fields).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fields", fields)
                .toString();
    }
}

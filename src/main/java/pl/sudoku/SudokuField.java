package pl.sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuField {

    private int value;

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {

        return value;
    }

    public void setFieldValue(int value) {

        if (0 <= value && value <= 9) {

            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        return new EqualsBuilder().append(value, ((SudokuField) o).value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("value", value)
                .toString();
    }
}

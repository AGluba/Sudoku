package pl.sudoku;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {

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
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass())  {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
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

    @Override
    public int compareTo(SudokuField o) {

        if (o == null) {

            throw new NullPointerException();
        }

        if (this.getFieldValue() == o.getFieldValue()) {

            return 0;
        } else if (this.getFieldValue() > o.getFieldValue()) {

            return 1;
        }

        return -1;
    }

    @Override
    public SudokuField clone() {
        try {
            return (SudokuField) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

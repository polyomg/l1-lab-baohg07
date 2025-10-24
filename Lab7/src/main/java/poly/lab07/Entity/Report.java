package poly.lab07.Entity;

import java.io.Serializable;

public interface Report {
    Serializable getGroupName();
    Double getTotalSum();
    Long getTotalCount();
}
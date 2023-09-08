package lk.ijse.orm_coursework.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.orm_coursework.bo.SuperBo;
import lk.ijse.orm_coursework.projection.CustomProjection;

public interface NotPaidBo extends SuperBo {
     ObservableList<CustomProjection> getDetailsToTableView();

}

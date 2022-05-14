package kon.patel.export.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Excel {

  private List<Column> columnList;
  private List<Object> objList;
}


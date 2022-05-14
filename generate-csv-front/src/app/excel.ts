import {Column} from "./column";
import {Item} from "./item";

export interface Excel {
  columnList: Column[];
  objList: Item[];
}

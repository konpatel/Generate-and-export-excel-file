import {Item} from "../item";
import {DataSource} from "@angular/cdk/collections";
import {Observable, ReplaySubject} from "rxjs";

export class TableDatasource extends DataSource<Item> {
  private _dataStream = new ReplaySubject<Item[]>();

  constructor(initialData: Item[]) {
    super();
    this.setData(initialData);
  }

  connect(): Observable<Item[]> {
    return this._dataStream;
  }

  disconnect() {}

  setData(data: Item[]) {
    this._dataStream.next(data);
  }
}

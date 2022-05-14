import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Column} from "../column";
import {Item} from "../item";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {ItemDialogComponent} from "./item-dialog/item-dialog.component";
import {DialogData} from "../dialogData";
import {TableDatasource} from "./table-datasource";
import {ExportService} from "../service/export.service";
import {Excel} from "../excel";

@Component({
  selector: 'app-csv-generator',
  templateUrl: './csv-generator.component.html',
  styleUrls: ['./csv-generator.component.scss']
})
export class CsvGeneratorComponent implements OnInit {

  showTable: boolean = false;
  itemList: Item[] = [];

  displayedColumns: Column[] = [];
  displayedColumnNames: string[] = [];
  dataSource = new TableDatasource(this.itemList);

  columnForm: FormGroup = this.formBuilder.group({
    propertyList: this.formBuilder.array([])
  });

  constructor(private formBuilder: FormBuilder, public dialog: MatDialog, private exportService: ExportService) {
  }

  ngOnInit(): void {
    this.addProperty();
  }

  get propertyList(): FormArray {
    return this.columnForm.get('propertyList') as FormArray;
  }

  propertyForm(): FormGroup {
    return this.formBuilder.group({
      name: new FormControl('', Validators.required),
      type: new FormControl('', Validators.required)
    });
  }

  addProperty() {
    this.propertyList.push(this.propertyForm());
  }

  removeProperty(i: number) {
    this.propertyList.removeAt(i);
  }

  displayTable(): void {
    this.displayedColumns = [];
    this.displayedColumnNames = [];
    if (this.columnForm.get('propertyList')?.value) {
      this.displayedColumns = this.columnForm.get('propertyList')?.value;
      this.displayedColumns.forEach(val => {
        this.displayedColumnNames.push(val.name);
      });
      this.displayedColumnNames.push('action');
    }
    if (!this.displayedColumns.some(dis => dis.name === null || dis.name === '') && !this.displayedColumns.some(dis => dis.type === null || dis.type === '')) {
      this.showTable = true;
    }
  }

  addRow(element?: Item) {
    const dialogData: DialogData = {
      columnList: this.displayedColumns,
      obj: {columnType: ''}
    };
    let newRow: Item = {columnType: ''}
    if (element) {
      newRow = element;
    }
    this.displayedColumnNames.forEach(name => {
      newRow[name] = '';
    });
    dialogData.obj = newRow;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = false;
    dialogConfig.data = dialogData;

    const dialogRef = this.dialog.open(ItemDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(
      data => {
        this.itemList.push(data);
        this.dataSource.setData(this.itemList);
      }
    );
  }

  editElement(element: Item): void {
    this.addRow(element);
  }

  deleteElement(i: number): void {
    delete this.itemList[i];
    this.dataSource.setData(this.itemList);
  }

  export(): void {
    const excel: Excel = {
      columnList: this.displayedColumns,
      objList: this.itemList,
    };
    this.exportService.export(excel).subscribe(res => {
      if (res) {
        this.exportService.downLoadBlob(res);
      }
    });
  }

}

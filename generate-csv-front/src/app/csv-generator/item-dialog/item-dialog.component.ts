import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData} from "../../dialogData";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-item-dialog',
  templateUrl: './item-dialog.component.html',
  styleUrls: ['./item-dialog.component.scss']
})
export class ItemDialogComponent implements OnInit {

  rowForm: FormGroup = new FormGroup({});
  dialogData: DialogData;

  constructor(private dialogRef: MatDialogRef<ItemDialogComponent>,
              @Inject(MAT_DIALOG_DATA) data: DialogData) {
    this.dialogData = data;
  }

  ngOnInit(): void {
    this.dialogData.columnList.forEach(col => {
      if (col.name !== 'action' && col.name !== 'columnType') {
        this.rowForm.addControl(col.name, new FormControl());
      }
    });
  }

  save(): void {
    this.dialogRef.close(this.rowForm.value);
  }

}

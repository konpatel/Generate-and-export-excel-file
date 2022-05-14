import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ItemDialogRoutingModule } from './item-dialog-routing.module';
import { ItemDialogComponent } from './item-dialog.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatInputModule} from "@angular/material/input";


@NgModule({
  declarations: [
    ItemDialogComponent
  ],
  imports: [
    CommonModule,
    ItemDialogRoutingModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    FlexLayoutModule,
    MatInputModule
  ]
})
export class ItemDialogModule { }

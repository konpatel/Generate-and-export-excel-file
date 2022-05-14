import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CsvGeneratorRoutingModule } from './csv-generator-routing.module';
import { CsvGeneratorComponent } from './csv-generator.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatTableModule} from "@angular/material/table";
import {MatDialogModule} from "@angular/material/dialog";
import {MatSelectModule} from "@angular/material/select";
import {MatToolbarModule} from "@angular/material/toolbar";


@NgModule({
  declarations: [
    CsvGeneratorComponent
  ],
    imports: [
        CommonModule,
        CsvGeneratorRoutingModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatDialogModule,
        FlexLayoutModule,
        MatButtonModule,
        MatIconModule,
        MatTableModule,
        FormsModule,
        MatSelectModule,
        MatToolbarModule
    ]
})
export class CsvGeneratorModule { }

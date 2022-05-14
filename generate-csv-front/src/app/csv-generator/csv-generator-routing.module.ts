import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CsvGeneratorComponent } from './csv-generator.component';

const routes: Routes = [{ path: '', component: CsvGeneratorComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CsvGeneratorRoutingModule { }

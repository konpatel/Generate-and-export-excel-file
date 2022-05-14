import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/csv-generator', pathMatch: 'full' },
  { path: 'csv-generator', loadChildren: () => import('./csv-generator/csv-generator.module').then(m => m.CsvGeneratorModule) },
  { path: 'item-dialog', loadChildren: () => import('./csv-generator/item-dialog/item-dialog.module').then(m => m.ItemDialogModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

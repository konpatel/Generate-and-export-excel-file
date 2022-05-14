import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ItemDialogComponent } from './item-dialog.component';

const routes: Routes = [{ path: '', component: ItemDialogComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItemDialogRoutingModule { }

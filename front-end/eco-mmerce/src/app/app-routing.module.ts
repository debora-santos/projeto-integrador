import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CadastroComponent} from './cadastro/cadastro.component';


const routes: Routes = [
  { path: '', redirectTo: 'cadastros', pathMatch: 'full' },
  { path: 'cadastros', component: CadastroComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { CadastroComponent } from './cadastro/cadastro.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {ProdutosComponent} from './produtos/produtos.component';
import {SobreComponent} from './sobre/sobre.component';
import {ContatoComponent} from './contato/contato.component';
import {CarrinhoComponent} from './carrinho/carrinho.component';
import {ProdutoDetalhadoComponent} from './produto-detalhado/produto-detalhado.component';
import {ClienteComponent} from "./cliente/cliente.component";

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'cadastro', component: CadastroComponent },
  { path: 'produtos', component: ProdutosComponent },
  { path: 'sobre', component: SobreComponent },
  { path: 'contato', component: ContatoComponent },
  { path: 'carrinho', component: CarrinhoComponent },
  { path: 'produto-detalhado', component: ProdutoDetalhadoComponent },
  { path: 'cliente', component: ClienteComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DeckListComponent } from './components/deck-list/deck-list.component';
import { CreateDeckComponent } from './components/create-deck/create-deck.component';
import { ViewDeckComponent } from './components/view-deck/view-deck.component';
import { EditDeckComponent } from './components/edit-deck/edit-deck.component';

const routes: Routes = [
  //{ path: '', redirectTo: 'create-deck', pathMatch: 'full' },
  { path: '', component: DeckListComponent },
  { path: 'deck-list', component: DeckListComponent },
  { path: 'create-deck', component: CreateDeckComponent },
  { path: 'view-deck/:deckId', component: ViewDeckComponent },
  { path: 'edit-deck/:id', component: EditDeckComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
